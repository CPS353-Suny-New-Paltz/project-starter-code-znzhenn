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
        UserComputeClient client = new UserComputeClient("localhost", port);

        int[] inlineValues = {1, 2, 3, 4}; // sum of digits = 10 | factorial 3628800?
        String outputFile = "test_output_inline.txt";

        var response = client.submitJob(null, outputFile, inlineValues, ",");

        assertTrue(response.getSuccess());
        assertTrue(response.getMessage().contains("Last result:"));

        // clean up file
        new File(outputFile).delete();
    }

    @Test
    public void testFileInputOnly() throws Exception {
        // temp input file
        File tempInput = File.createTempFile("input_file_test_", ".txt");
        try (PrintWriter writer = new PrintWriter(tempInput)) {
            writer.println("1,2,3,4");
        }

        UserComputeClient client = new UserComputeClient("localhost", port);
        String outputFile = "test_output_file.txt";

        var response = client.submitJob(tempInput.getAbsolutePath(), outputFile, new int[]{}, ",");

        assertTrue(response.getSuccess());
        assertTrue(response.getMessage().contains("Last result:"));

        // clean up
        tempInput.delete();
        new File(outputFile).delete();
    }

    @Test
    public void testFileAndInlineCombined() throws Exception {
        // file input: 1,2
        File tempInput = File.createTempFile("input_file_test_", ".txt");
        try (PrintWriter writer = new PrintWriter(tempInput)) {
            writer.println("1,2");
        }

        // inline: 3,4
        int[] inlineValues = {3, 4};
        String outputFile = "test_output_combined.txt";

        UserComputeClient client = new UserComputeClient("localhost", port);
        var response = client.submitJob(tempInput.getAbsolutePath(), outputFile, inlineValues, ",");

        assertTrue(response.getSuccess());
        assertTrue(response.getMessage().contains("Last result:"));

        // clean up
        tempInput.delete();
        new File(outputFile).delete();
    }
}
