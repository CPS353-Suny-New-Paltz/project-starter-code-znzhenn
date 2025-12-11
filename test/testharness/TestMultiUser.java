package testharness;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.network.UserAPI;
import projectapis.network.MultithreadedNetworkAPI;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMultiUser {
	
	// TODO 1: change the type of this variable to the name you're using for your @NetworkAPI interface
	// complete
	private UserAPI coordinator;
	private MultithreadedNetworkAPI networkAPI;
	private final int numThreads = 4;
	
	@BeforeEach
	public void initializeComputeEngine() throws IOException {
		File testDir = new File("test");
        if (!testDir.exists()) testDir.mkdirs();

        // Create input file if it doesn't exist
        File inputFile = new File(testDir, "testInputFile.test");
        if (!inputFile.exists()) {
            Files.writeString(inputFile.toPath(), "1,15,10,5,2,3,8");
        }
    
		//TODO 2: create an instance of the implementation of your @NetworkAPI; this is the component that the user will make requests to
		// Store it in the 'coordinator' instance variable
		// complete
	}
	
	
	public void cleanup() {
        /*if (networkAPI != null) {
            networkAPI.shutdown();
        }*/
    }
	@Test
	public void compareMultiAndSingleThreaded() throws Exception {
        List<TestUser> testUsers = new ArrayList<>();
        for (int i = 0; i < numThreads; i++) {
            testUsers.add(new TestUser()); // each user has its own coordinator
        }

        // Single-threaded run
        String singleThreadPrefix = "test/singleThreadOut";
        for (int i = 0; i < numThreads; i++) {
            String path = singleThreadPrefix + i + ".tmp";
            new File(path).delete();
            testUsers.get(i).run(path);
        }

        // Multi-threaded run
        ExecutorService executor = Executors.newCachedThreadPool();
        List<Future<?>> futures = new ArrayList<>();
        String multiThreadPrefix = "test/multiThreadOut";

        for (int i = 0; i < numThreads; i++) {
            final int index = i;
            futures.add(executor.submit(() -> {
                try {
                    String path = multiThreadPrefix + index + ".tmp";
                    new File(path).delete();
                    testUsers.get(index).run(path);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }));
        }

        for (Future<?> f : futures) f.get();
        executor.shutdown();

        // Compare outputs
        List<String> singleThreaded = loadAllOutput(singleThreadPrefix, numThreads);
        List<String> multiThreaded = loadAllOutput(multiThreadPrefix, numThreads);

        Assertions.assertEquals(singleThreaded, multiThreaded,
                "Multi-threaded results should match single-threaded results");
    }

	private List<String> loadAllOutput(String prefix, int numFiles) throws IOException {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < numFiles; i++) {
            File file = new File(prefix + i + ".tmp");
            if (!file.exists()) continue;
            result.addAll(Files.readAllLines(file.toPath()));
        }
        return result;
    }

}
