package project.client;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import project.usercompute.UserComputeServiceImplementation;
//import project.datastore.DataStoreServiceImplementation;
import projectapis.network.MultithreadedNetworkAPI;

//import projectapis.network.UserAPI;
//import projectapis.network.UserAPIImplementation;
import projectapis.process.DataStorageAPIImplementation;
import projectapis.conceptual.FactorialAPIImplementation;

public class GrpcServerMain {
    public static void main(String[] args) throws Exception {
    	
    	int maxThreads = 8;
    	MultithreadedNetworkAPI userAPI = new MultithreadedNetworkAPI(new DataStorageAPIImplementation(), new FactorialAPIImplementation(), maxThreads);

    	UserComputeServiceImplementation userComputeService = new UserComputeServiceImplementation(userAPI);
        
    	//UserAPIImplementation userAPI = new UserAPIImplementation();
    	DataStorageAPIImplementation dataStorage = new DataStorageAPIImplementation();
        project.datastore.DataStoreServiceImplementation dataStoreService = new project.datastore.DataStoreServiceImplementation(dataStorage);

        Server server = ServerBuilder.forPort(50051)
                .addService(userComputeService)
                .addService(dataStoreService)
                .build();


        System.out.println("gRPC server started on port 50051 with " + maxThreads + " threads for user computations...");
        server.start();
        server.awaitTermination();
    }
}
