package project.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import project.datastore.DataStoreProto.ReadDataRequest;
import project.datastore.DataStoreProto.ReadDataResponse;
import project.datastore.DataStoreProto.WriteDataRequest;
import project.datastore.DataStoreServiceGrpc;
import projectapis.process.DataStorageAPI;
import projectapis.ComputationStatus;

import java.util.List;

public class DataStoreGrpcClient implements DataStorageAPI {

    private final DataStoreServiceGrpc.DataStoreServiceBlockingStub stub;

    public DataStoreGrpcClient(String host, int port) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        stub = DataStoreServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public List<Integer> loadIntegers(String inputSource, String delimiter) {
        ReadDataRequest request = ReadDataRequest.newBuilder()
                .setInputFile(inputSource)
                .build();
        ReadDataResponse response = stub.readData(request);
        return response.getNumbersList();
    }

    @Override
    public void storeResults(String outputSource, List<Long> results) {
        WriteDataRequest request = WriteDataRequest.newBuilder()
                .setOutputFile(outputSource)
                .addAllNumbers(results.stream().map(Long::intValue).toList())
                .build();
        stub.writeData(request);
    }

    @Override
    public long fetchComputation() { 
    	return 0; }

    @Override
    public ComputationStatus getComputationStatus() { 
    	return ComputationStatus.EXISTS; }

    @Override
    public String loadData() { 
    	return ""; }

    @Override
    public void saveComputation() { 
    	//save
    }
}
