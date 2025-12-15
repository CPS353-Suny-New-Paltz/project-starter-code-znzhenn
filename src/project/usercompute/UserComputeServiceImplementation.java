package project.usercompute;

import io.grpc.stub.StreamObserver;
import project.usercompute.UserComputeProto.JobRequest;
import project.usercompute.UserComputeProto.JobResponse;
import project.usercompute.UserComputeServiceGrpc;

import projectapis.network.UserAPI;

public class UserComputeServiceImplementation extends UserComputeServiceGrpc.UserComputeServiceImplBase {

    private final UserAPI userAPI;

    public UserComputeServiceImplementation(UserAPI userAPI) {
        this.userAPI = userAPI;
    }

    @Override
    public void submitJob(JobRequest request, StreamObserver<JobResponse> responseObserver) {
        try {
            // configure input, output, and delimiter
            if (request.hasInputFile()) {
                userAPI.setInput(request.getInputFile());
            }

            if (request.hasOutputFile()) {
                userAPI.setOutput(request.getOutputFile());
            }

            if (request.hasDelimiter()) {
                userAPI.setDelimiter(request.getDelimiter());
            }

            // execute the computation
            long lastResult = userAPI.executeComputation();

            // successful response
            JobResponse response = JobResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Last result: " + lastResult)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            // error response
            JobResponse response = JobResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Error: " + e.getMessage())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
