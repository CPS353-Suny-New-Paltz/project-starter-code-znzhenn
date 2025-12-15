package project.client;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import project.usercompute.UserComputeServiceImplementation;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.network.MultithreadedNetworkAPI;

public class GrpcServerMain {
    public static void main(String[] args) throws Exception {

        int maxThreads = 8;

        // Connect compute engine to datastore via gRPC
        DataStoreGrpcClient dataStoreClient = new DataStoreGrpcClient("localhost", 50053);

        MultithreadedNetworkAPI userAPI = new MultithreadedNetworkAPI(
                dataStoreClient,
                new FactorialAPIImplementation(),
                maxThreads
        );

        UserComputeServiceImplementation userComputeService = new UserComputeServiceImplementation(userAPI);

        Server server = ServerBuilder.forPort(50052)
                .addService(userComputeService)
                .build();

        System.out.println("Compute gRPC server started on port 50052 with " + maxThreads + " threads...");
        server.start();
        server.awaitTermination();
    }
}
