package integrationtests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.PrintWriter;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import project.usercompute.UserComputeClient;
import project.usercompute.UserComputeServiceImplementation;
import projectapis.network.MultithreadedNetworkAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.process.DataStorageAPIImplementation;

public class GrpcIntegrationTest {

    private static Server server;
    private static int port = 50053; //test port

    @BeforeAll
    public static void startServer() throws Exception {
        MultithreadedNetworkAPI userAPI = new MultithreadedNetworkAPI(new DataStorageAPIImplementation(), new FactorialAPIImplementation(), 4);

        server = ServerBuilder.forPort(port)
                .addService(new UserComputeServiceImplementation(userAPI))
                .build()
                .start();

        System.out.println("Test gRPC server started on port " + port);
    }

    @AfterAll
    public static void stopServer() throws Exception {
        if (server != null) {
            server.shutdown();
        }
    }

    @Test
    public void testInlineValuesOnly() {
       
    }

    @Test
    public void testFileInputOnly() throws Exception {
        
    }
        
    }
}
