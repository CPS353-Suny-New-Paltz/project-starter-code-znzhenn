package project.datastore;

import io.grpc.stub.StreamObserver;
import project.datastore.DataStoreProto.*;
import project.datastore.DataStoreServiceGrpc;
import projectapis.process.DataStorageAPI;

import java.util.List;

public class DataStoreServiceImplementation extends DataStoreServiceGrpc.DataStoreServiceImplBase {

    private final DataStorageAPI dataStorage;

    public DataStoreServiceImplementation(DataStorageAPI dataStorage) {
        this.dataStorage = dataStorage;
    }

    @Override
    public void readData(ReadDataRequest request, StreamObserver<ReadDataResponse> responseObserver) {
        try {
            String delimiter = ","; // default
            List<Integer> numbers = dataStorage.loadIntegers(request.getInputFile(), delimiter);
            ReadDataResponse response = ReadDataResponse.newBuilder()
                    .addAllNumbers(numbers)
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void writeData(WriteDataRequest request, StreamObserver<WriteDataResponse> responseObserver) {
        try {
            List<Integer> numbers = request.getNumbersList();
            List<Long> longNumbers = numbers.stream().map(Integer::longValue).toList();
            dataStorage.storeResults(request.getOutputFile(), longNumbers);

            WriteDataResponse response = WriteDataResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Data stored successfully")
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } catch (Exception e) {
            WriteDataResponse response = WriteDataResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Error: " + e.getMessage())
                    .build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();
        }
    }
}
