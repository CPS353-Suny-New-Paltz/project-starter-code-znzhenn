package projectapis.network;

import project.annotations.NetworkAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.conceptual.FactorialAPI;
import projectapis.process.DataStorageAPIImplementation;
import projectapis.process.DataStorageAPI;

import java.util.List;
import java.util.ArrayList;

@NetworkAPI
public class UserAPIImplementation implements UserAPI {

    private String input;
    private String output;
    private String delimiter = ",";

    private final DataStorageAPI dataStorage;
    private final FactorialAPI factorialAPI;

    public UserAPIImplementation() {
        this.dataStorage = new DataStorageAPIImplementation();
        this.factorialAPI = new FactorialAPIImplementation();
    }

    @Override
    public void setInput(String input) {
        // Validation requirement: public method must validate parameters
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException("Input file path cannot be null or empty.");
        }
        this.input = input;
    }

    @Override
    public void setOutput(String output) {
        if (output == null || output.isBlank()) {
            throw new IllegalArgumentException("Output file path cannot be null or empty.");
        }
        this.output = output;
    }

    @Override
    public void setDelimiter(String delimiter) {
        if (delimiter == null || delimiter.isBlank()) {
            throw new IllegalArgumentException("Delimiter cannot be null or empty.");
        }
        this.delimiter = delimiter;
    }

    @Override
    public long executeComputation() {
        try {
            // Validate state (internal)
            if (input == null || output == null) {
                return 0L; // sentinel
            }

            List<Integer> numbers = dataStorage.loadIntegers(input, delimiter);

            if (numbers == null || numbers.isEmpty()) {
                return 0L; // sentinel
            }
            
           
            if (delimiter == null || delimiter.isBlank()) {
                return 0L;
            }


            List<Long> results = new ArrayList<>();

            for (int number : numbers) {
                if (number < 0) {
                    return 0L;  
                }
                long value = factorialAPI.computeDigitFactorialSum(number);
                results.add(value);
            }


            dataStorage.storeResults(output, results);

            // return last result
            return results.get(results.size() - 1);


        } catch (Exception e) {
            // Requirement: catch both expected + unexpected exceptions
            return 0; // Return sentinel instead of throwing
        }
    }
}
