package project.datastore;

import io.grpc.stub.StreamObserver;
import project.datastore.DataStoreServiceGrpc;
import project.datastore.DataStoreProto;

public class DataStoreServiceImplementation extends DataStoreServiceGrpc.DataStoreServiceImplBase {

    @Override
    public void readData(DataStoreProto.ReadDataRequest request, 
                         StreamObserver<DataStoreProto.ReadDataResponse> responseObserver) {
        // Example: Create a response
        DataStoreProto.ReadDataResponse response = DataStoreProto.ReadDataResponse.newBuilder()
                .addNumbers(1)   // example value
                .addNumbers(2)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void writeData(DataStoreProto.WriteDataRequest request, 
                          StreamObserver<DataStoreProto.WriteDataResponse> responseObserver) {
        // Example: pretend we wrote the data successfully
        DataStoreProto.WriteDataResponse response = DataStoreProto.WriteDataResponse.newBuilder()
                .setSuccess(true)
                .setMessage("Data written successfully")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
