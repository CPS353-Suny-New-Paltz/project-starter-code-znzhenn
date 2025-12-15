package project.usercompute;

import java.util.Arrays;
import java.util.Scanner;

public class UserComputeClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter input file (or leave blank for inline numbers): ");
        String inputFile = scanner.nextLine().trim();

        System.out.print("Enter inline numbers separated by commas (optional): ");
        String inlineInput = scanner.nextLine().trim();
        int[] inlineValues = inlineInput.isEmpty()
                ? new int[0]
                : Arrays.stream(inlineInput.split(","))
                        .map(String::trim)
                        .mapToInt(Integer::parseInt)
                        .toArray();

        System.out.print("Enter output file: ");
        String outputFile = scanner.nextLine().trim();

        System.out.print("Enter delimiter (default ','): ");
        String delimiter = scanner.nextLine().trim();
        if (delimiter.isEmpty()) {
            delimiter = ",";
        }

        UserComputeClient client =
                new UserComputeClient("localhost", 50052);
        var response =
                client.submitJob(inputFile, outputFile, inlineValues, delimiter);

        System.out.println("Success: " + response.getSuccess());
        System.out.println("Message: " + response.getMessage());
    }

    private final UserComputeServiceGrpc.UserComputeServiceBlockingStub blockingStub;

    public UserComputeClient(String host, int port) {
        var channel = io.grpc.ManagedChannelBuilder
                .forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = UserComputeServiceGrpc.newBlockingStub(channel);
    }

    public project.usercompute.UserComputeProto.JobResponse submitJob(
            String inputFile,
            String outputFile,
            int[] inlineValues,
            String delimiter) {

        var builder =
                project.usercompute.UserComputeProto.JobRequest.newBuilder()
                        .setInputFile(inputFile)
                        .setOutputFile(outputFile)
                        .setDelimiter(delimiter);

        for (int i : inlineValues) {
            builder.addInlineValues(i);
        }

        try {
            return blockingStub.submitJob(builder.build());
        } catch (io.grpc.StatusRuntimeException e) {
            return project.usercompute.UserComputeProto.JobResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("RPC failed: " + e.getMessage())
                    .build();
        }
    }
}
