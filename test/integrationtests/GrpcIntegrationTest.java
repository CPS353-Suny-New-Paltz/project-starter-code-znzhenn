package integrationtests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.PrintWriter;
import java.util.List;

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
    private static int port = 50053; // test port
    private static FactorialAPIImplementation factorialAPI = new FactorialAPIImplementation();

    @BeforeAll
    public static void startServer() throws Exception {
        MultithreadedNetworkAPI userAPI = new MultithreadedNetworkAPI(
                new DataStorageAPIImplementation(),
                factorialAPI,
                4 // test threads
        );

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

    // computes expected result of integers
    private long expectedFactorialOfDigitSum(List<Integer> numbers) {
        long lastResult = 0L;
        for (int n : numbers) {
            lastResult = factorialAPI.computeDigitFactorialSum(n);
        }
        return lastResult;
    }

    @Test
    public void testInlineValuesOnly() throws Exception {
        File tempInput = File.createTempFile("empty_input_", ".txt");
        String outputFile = "test_output_inline.txt";

        int[] inlineValues = {1, 2, 3, 4};

        UserComputeClient client = new UserComputeClient("localhost", port);
        var response = client.submitJob(tempInput.getAbsolutePath(), outputFile, inlineValues, ",");

        assertTrue(response.getSuccess());

        long expected = expectedFactorialOfDigitSum(List.of(1, 2, 3, 4));
        assertTrue(response.getMessage().contains(String.valueOf(expected)));

        tempInput.delete();
        new File(outputFile).delete();
    }

    @Test
    public void testFileInputOnly() throws Exception {
        File tempInput = File.createTempFile("input_file_test_", ".txt");
        try (PrintWriter writer = new PrintWriter(tempInput)) {
            writer.println("1,2,3,4");
        }

        String outputFile = "test_output_file.txt";
        UserComputeClient client = new UserComputeClient("localhost", port);
        var response = client.submitJob(tempInput.getAbsolutePath(), outputFile, new int[]{}, ",");

        assertTrue(response.getSuccess());

        long expected = expectedFactorialOfDigitSum(List.of(1, 2, 3, 4));
        assertTrue(response.getMessage().contains(String.valueOf(expected)));

        tempInput.delete();
        new File(outputFile).delete();
    }

    @Test
    public void testFileAndInlineCombined() throws Exception {
        File tempInput = File.createTempFile("input_file_test_", ".txt");
        try (PrintWriter writer = new PrintWriter(tempInput)) {
            writer.println("1,2");
        }

        int[] inlineValues = {3, 4};
        String outputFile = "test_output_combined.txt";

        UserComputeClient client = new UserComputeClient("localhost", port);
        var response = client.submitJob(tempInput.getAbsolutePath(), outputFile, inlineValues, ",");

        assertTrue(response.getSuccess());

        // all numbers combined
        List<Integer> combined = List.of(1, 2, 3, 4);
        long expected = expectedFactorialOfDigitSum(combined);
        assertTrue(response.getMessage().contains(String.valueOf(expected)));

        tempInput.delete();
        new File(outputFile).delete();
    }
}
