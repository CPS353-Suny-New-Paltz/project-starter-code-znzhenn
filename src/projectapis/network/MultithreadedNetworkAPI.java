package projectapis.network;

import projectapis.conceptual.FactorialAPI;
import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultithreadedNetworkAPI implements UserAPI {

    private String inputPath;
    private String outputPath;
    private String delimiter = ",";
    private final DataStorageAPI dataStorage;
    private final FactorialAPI factorialAPI;
    private final ExecutorService executor;

    public MultithreadedNetworkAPI(FactorialAPI factorialAPI) {
        this(new DataStorageAPIImplementation(), factorialAPI, 4);
    }

    public MultithreadedNetworkAPI(
            DataStorageAPI dataStorage,
            FactorialAPI factorialAPI,
            int maxThreads) {

        if (dataStorage == null || factorialAPI == null) {
            throw new IllegalArgumentException("dependencies can't be null");
        }
        if (maxThreads <= 0) {
            throw new IllegalArgumentException("maxThreads must be > 0");
        }

        this.dataStorage = dataStorage;
        this.factorialAPI = factorialAPI;
        this.executor = Executors.newFixedThreadPool(maxThreads);
    }

    @Override
    public void setInput(String input) {
        this.inputPath = input;
    }

    @Override
    public void setOutput(String output) {
        this.outputPath = output;
    }

    @Override
    public void setDelimiter(String delimiter) {
        if (delimiter != null) {
            this.delimiter = delimiter;
        }
    }

    @Override
    public long executeComputation() {
        if (inputPath == null || inputPath.isBlank()
                || outputPath == null || outputPath.isBlank()) {
            return 0L;
        }

        List<Integer> numbers =
                dataStorage.loadIntegers(inputPath, delimiter);
        if (numbers.isEmpty()) {
            return 0L;
        }

        List<Future<Long>> futures = new ArrayList<>();
        List<Long> results = new ArrayList<>();

        for (int n : numbers) {
            if (n < 0) {
                continue;
            }
            futures.add(
                    executor.submit(
                            () -> factorialAPI.computeDigitFactorialSum(n)));
        }

        for (Future<Long> f : futures) {
            try {
                results.add(f.get());
            } catch (Exception e) {
                results.add(0L);
            }
        }

        dataStorage.storeResults(outputPath, results);
        return results.get(results.size() - 1);
    }

    public void shutdown() {
        executor.shutdown();
    }
}
