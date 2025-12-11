package projectapis.network;
import projectapis.conceptual.FactorialAPI;
import projectapis.process.DataStorageAPI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SingleThreadedNetworkAPI implements UserAPI{

	public String outputPath;
	public String inputPath;
	public String delimiter = ",";
	private FactorialAPI factorialAPI;
	private final DataStorageAPI dataStorage;
	
	
	public SingleThreadedNetworkAPI(DataStorageAPI dataStorage, FactorialAPI factorialAPI) {
	    if (dataStorage == null || factorialAPI == null) {
	    	throw new IllegalArgumentException("dependencies can't be null");
	    }
	    this.dataStorage = dataStorage;
		this.factorialAPI = factorialAPI;
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
		this.delimiter = delimiter;
		
	}

	@Override
	public long executeComputation() {
		//validate data
		if (inputPath == null || inputPath.isBlank() || outputPath == null || outputPath.isBlank()) {
            return 0L;
        }
		
		//load inputs from dataStorageAPI
		List<Integer> numbers = dataStorage.loadIntegers(inputPath, delimiter);
        if (numbers == null || numbers.isEmpty()) {
            return 0L;
        }
        
        List<Long> results = new ArrayList<>();
        for (int number : numbers) {
            if (number < 0) {
                // skip negatives (DataStorage already filters them, but just in case)
                continue;
            }
            long value = factorialAPI.computeDigitFactorialSum(number);
            results.add(value);
        }
        // store results
        dataStorage.storeResults(outputPath, results);

        
        // return last result
        return results.isEmpty() ? 0L : results.get(results.size() - 1);
	}
        
        /*
	        
	        //ensure output file exists
	        Files.createDirectories(outPath.getParent());
	        
	        String content = Files.readString(inPath);
	        String[] tokens = content.split(",");

            int sum = 0;
            for (String token : tokens) {
                token = token.trim();
                if (!token.isEmpty()) {
                    sum += Integer.parseInt(token);
                }
            }
            // compute factorial of the sum
            long result = factorialAPI.factorialOfSum(sum);

            // write the result to the file
            Files.write(outPath, String.valueOf(result).getBytes());

            // return the result
            return result;
            
		} */
}
	