package project.usercompute;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import io.grpc.stub.StreamObserver;
import project.usercompute.UserComputeProto.JobRequest;
import project.usercompute.UserComputeProto.JobResponse;
import project.usercompute.UserComputeServiceGrpc;

import projectapis.network.UserAPI;
import projectapis.network.UserAPIImplementation;

public class UserComputeServiceImplementation extends UserComputeServiceGrpc.UserComputeServiceImplBase {

    private final UserAPI userAPI;

    public UserComputeServiceImplementation(UserAPI userAPI) {
        this.userAPI = userAPI;
    }

    @Override
    public void submitJob(JobRequest request, StreamObserver<JobResponse> responseObserver) {
        File tempFile = null;
        try {
            // configure output file and delimiter
            if (request.hasOutputFile()) {
                userAPI.setOutput(request.getOutputFile());
            }

            if (request.hasDelimiter()) {
                userAPI.setDelimiter(request.getDelimiter());
            }

            String delimiter = request.hasDelimiter() ? request.getDelimiter() : ",";

            // lad numbers from input file (if provided)
            List<Integer> allNumbers = new ArrayList<>();
            if (request.hasInputFile()) {
                File inputFile = new File(request.getInputFile());
                if (inputFile.exists() && inputFile.isFile()) {
                    try (Scanner scanner = new Scanner(inputFile)) {
                        while (scanner.hasNextLine()) {
                            String[] parts = scanner.nextLine().split(delimiter);
                            for (String part : parts) {
                                part = part.trim();
                                if (!part.isEmpty()) {
                                    try {
                                        int value = Integer.parseInt(part);
                                        if (value >= 0) { // skip negatives
                                            allNumbers.add(value);
                                        }
                                    } catch (NumberFormatException ignored) {}
                                }
                            }
                        }
                    }
                }
            }

            // add inline values
            allNumbers.addAll(request.getInlineValuesList());

            // write merged numbers to a temporary file
            tempFile = File.createTempFile("merged_input_", ".txt");
            try (PrintWriter writer = new PrintWriter(tempFile)) {
                writer.println(allNumbers.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(delimiter)));
            }

            // set temp file as input and execute computation
            userAPI.setInput(tempFile.getAbsolutePath());
            long lastResult = userAPI.executeComputation();

            // return success response
            JobResponse response = JobResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Last result: " + lastResult)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();

        } catch (Exception e) {
            JobResponse response = JobResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Error: " + e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } finally {
            // delete temp file
            if (tempFile != null && tempFile.exists()) {
                tempFile.delete();
            }
        }
    }


}
