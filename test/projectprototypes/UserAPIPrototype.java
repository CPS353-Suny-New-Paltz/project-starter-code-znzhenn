package projectprototypes;

import project.annotations.NetworkAPIPrototype;
import projectapis.network.UserAPI;
import projectapis.process.DataStorageAPI;
import projectapis.conceptual.FactorialAPI;
import java.util.List;

public class UserAPIPrototype implements UserAPI {

    private String input;
    private String output;
    private String delimiter = ",";
    private DataStorageAPI dataStorage;
    private FactorialAPI factorialAPI;

    public UserAPIPrototype(DataStorageAPI dataStorage, FactorialAPI factorialAPI) {
        this.dataStorage = dataStorage;
        this.factorialAPI = factorialAPI;
    }

    @NetworkAPIPrototype
    public static void prototype(UserAPI api) {
        api.setInput("input.txt");
        api.setOutput("output.txt");
        api.setDelimiter(",");

        long result = api.executeComputation();
        System.out.println("Prototype computation result: " + result);
    }

    @Override
    public void setInput(String input) {
        this.input = input;
    }

    @Override
    public void setOutput(String output) {
        this.output = output;
    }

    @Override
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Override
    public long executeComputation() {
        // 1. Load integers from DataStorage
        List<Integer> numbers = dataStorage.loadIntegers(input, delimiter);

        // 2. Compute factorial sum
        long result = 0;
        for (int n : numbers) {
            result += factorialAPI.computeDigitFactorialSum(n);
        }

        // 3. Store results back
        dataStorage.storeResults(output, List.of(result));

        return result;
    }

    // Optional helper if needed
    public long fetchExistingResult() {
        return dataStorage.fetchComputation();
    }
}
