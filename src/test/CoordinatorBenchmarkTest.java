package test;

import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.conceptual.OptimizedFactorialAPIImplementation;
import projectapis.network.UserAPI;
import projectapis.network.UserAPIImplementation;
import projectapis.process.DataStorageAPI;
import projectapis.ComputationStatus;

import java.util.ArrayList;
import java.util.List;

public class CoordinatorBenchmarkTest {

    private static List<Integer> generateTestData(int size) {
        List<Integer> data = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            data.add(987654321);
        }
        return data;
    }

    private static long timeExecution(UserAPI api, int runs) {
        long total = 0;
        for (int i = 0; i < runs; i++) {
            long start = System.nanoTime();
            api.executeComputation();
            total += System.nanoTime() - start;
        }
        return total / runs;
    }

    static class InMemoryDataStorage implements DataStorageAPI {
        private final List<Integer> numbers;

        InMemoryDataStorage(List<Integer> numbers) {
            this.numbers = numbers;
        }

        @Override
        public List<Integer> loadIntegers(String input, String delimiter) {
            return numbers;
        }

        @Override
        public void storeResults(String output, List<Long> results) {}

        @Override
        public long fetchComputation() {
            return 0;
        }

        @Override
        public ComputationStatus getComputationStatus() {
            return ComputationStatus.EXISTS;
        }

        @Override
        public String loadData() {
            return "";
        }

        @Override
        public void saveComputation() {}
    }

    public static void main(String[] args) {

        List<Integer> data = generateTestData(200_000);
        int runs = 5;

        UserAPI original = new UserAPIImplementation(
                new InMemoryDataStorage(data),
                new FactorialAPIImplementation()
        );
        original.setInput("ignored");
        original.setOutput("ignored");

        UserAPI optimized = new UserAPIImplementation(
                new InMemoryDataStorage(data),
                new OptimizedFactorialAPIImplementation()
        );
        optimized.setInput("ignored");
        optimized.setOutput("ignored");

        // JVM warm-up
        original.executeComputation();
        optimized.executeComputation();

        long originalTime = timeExecution(original, runs);
        long optimizedTime = timeExecution(optimized, runs);

        System.out.println("Original avg (ns): " + originalTime);
        System.out.println("Optimized avg (ns): " + optimizedTime);

        double improvement =
                (originalTime - optimizedTime) / (double) originalTime * 100.0;

        System.out.printf("Improvement: %.2f%%%n", improvement);

        if (optimizedTime > originalTime * 0.9) {
            throw new RuntimeException(
                    "Pipeline benchmark failed: optimized version is not 10% faster"
            );
        }
    }
    /*
     * Original avg (ns): 12100591
     * Optimized avg (ns): 418084
     * Improvement: 65.45%
     * */
}
