package project.datastore;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import projectapis.process.DataStorageAPIImplementation;

import java.io.IOException;

public class DataStoreServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(50051)
                .addService(new DataStoreServiceImplementation(new DataStorageAPIImplementation()))
                .build();

        server.start();
        System.out.println("DataStoreServer started on port 50051");
        server.awaitTermination();
    }
}
