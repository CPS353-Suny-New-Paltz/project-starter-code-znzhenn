package project.client;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import project.usercompute.UserComputeServiceImplementation;
import project.datastore.DataStoreServiceImplementation;
import projectapis.network.UserAPIImplementation;
import projectapis.process.DataStorageAPIImplementation;

public class GrpcServerMain {
    public static void main(String[] args) throws Exception {
        UserAPIImplementation userAPI = new UserAPIImplementation();
        DataStorageAPIImplementation dataStorage = new DataStorageAPIImplementation();

        Server server = ServerBuilder.forPort(50051)
                .addService(new UserComputeServiceImplementation(userAPI))
                .addService(new DataStoreServiceImplementation(dataStorage))
                .build();

        System.out.println("gRPC server started on port 50051...");
        server.start();
        server.awaitTermination();
    }
}
