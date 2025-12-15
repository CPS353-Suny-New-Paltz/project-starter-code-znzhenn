package project.datastore;

import io.grpc.stub.StreamObserver;
import project.datastore.DataStoreProto.ReadDataRequest;
import project.datastore.DataStoreProto.ReadDataResponse;
import project.datastore.DataStoreProto.WriteDataRequest;
import project.datastore.DataStoreProto.WriteDataResponse;
import project.datastore.DataStoreServiceGrpc.DataStoreServiceImplBase;
import projectapis.process.DataStorageAPI;

import java.util.List;

public class DataStoreServiceImplementation extends DataStoreServiceImplBase {

    private final DataStorageAPI dataStorage;

    public DataStoreServiceImplementation(DataStorageAPI dataStorage) {
        if (dataStorage == null) {
            throw new IllegalArgumentException("dataStorage cannot be null");
        }
        this.dataStorage = dataStorage;
    }

    @Override
    public void readData(ReadDataRequest request, StreamObserver<ReadDataResponse> responseObserver) {
        try {
            List<Integer> numbers = dataStorage.loadIntegers(request.getInputFile(), ",");
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
            dataStorage.storeResults(request.getOutputFile(), numbers.stream().map(Long::valueOf).toList());
            WriteDataResponse response = WriteDataResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Results stored successfully")
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
