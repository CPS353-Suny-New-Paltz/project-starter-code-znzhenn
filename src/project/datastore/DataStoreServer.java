package project.datastore;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import project.datastore.DataStoreProto.ReadDataResponse;
import project.datastore.DataStoreProto.ReadDataRequest;
import project.datastore.DataStoreProto.WriteDataRequest;
import project.datastore.DataStoreProto.WriteDataResponse;
import project.datastore.DataStoreServiceGrpc.DataStoreServiceImplBase;
import projectapis.process.DataStorageAPIImplementation;

import java.io.IOException;
import java.util.List;

public class DataStoreServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder.forPort(50051)
                .addService(new DataStoreServiceImpl())
                .build();

        server.start();
        System.out.println("Data Store gRPC server started on port 50051");
        server.awaitTermination();
    }

    static class DataStoreServiceImpl extends DataStoreServiceImplBase {
        private final DataStorageAPIImplementation storage = new DataStorageAPIImplementation();

        @Override
        public void readData(ReadDataRequest request, StreamObserver<ReadDataResponse> responseObserver) {
            String inputFile = request.getInputFile();
            List<Integer> numbers = storage.loadIntegers(inputFile, ","); // default delimiter
            ReadDataResponse response = ReadDataResponse.newBuilder()
                    .addAllNumbers(numbers)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }

        @Override
        public void writeData(WriteDataRequest request, StreamObserver<WriteDataResponse> responseObserver) {
            List<Integer> numbers = request.getNumbersList();
            String outputFile = request.getOutputFile();
            storage.storeResults(outputFile, numbers.stream().map(Long::valueOf).toList());
            WriteDataResponse response = WriteDataResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Results stored successfully")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
