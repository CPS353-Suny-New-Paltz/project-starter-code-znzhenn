package project.usercompute;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import project.usercompute.UserComputeProto.JobRequest;
import project.usercompute.UserComputeProto.JobResponse;

public class UserComputeClient {

    private final UserComputeServiceGrpc.UserComputeServiceBlockingStub blockingStub;

    public UserComputeClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext() // disable TLS for simplicity
                .build();
        blockingStub = UserComputeServiceGrpc.newBlockingStub(channel);
    }

    public JobResponse submitJob(String inputFile, String outputFile, int[] inlineValues, String delimiter) {
        // build the JobRequest
        JobRequest.Builder requestBuilder = JobRequest.newBuilder()
                .setInputFile(inputFile)
                .setOutputFile(outputFile)
                .setDelimiter(delimiter);

        for (int value : inlineValues) {
            requestBuilder.addInlineValues(value);
        }

        JobRequest request = requestBuilder.build();

        // make the gRPC call
        JobResponse response;
        try {
            response = blockingStub.submitJob(request);
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed: " + e.getStatus());
            return JobResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("RPC failed: " + e.getMessage())
                    .build();
        }

        return response;
    }

    public static void main(String[] args) {
        UserComputeClient client = new UserComputeClient("localhost", 50052); // use your server port

        int[] inlineValues = {1, 2, 3, 4};
        JobResponse response = client.submitJob("input.txt", "output.txt", inlineValues, ",");

        System.out.println("Success: " + response.getSuccess());
        System.out.println("Message: " + response.getMessage());
    }
}
