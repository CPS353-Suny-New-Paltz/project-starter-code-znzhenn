package projectapis.network;
import projectapis.conceptual.FactorialAPI;
import projectapis.network.SingleThreadedNetworkAPI;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class SingleThreadedNetworkAPI implements UserAPI{

	public String outputPath;
	public String inputPath;
	public String delimiter;
	private FactorialAPI factorialAPI;
	
	
	public SingleThreadedNetworkAPI(FactorialAPI factorialAPI) {
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
		try { //read numbers from a file
			
			String content = Files.readString(Path.of(inputPath));
            List<String> tokens = Arrays.asList(content.split(delimiter));

            int sum = 0;
            for (String token : tokens) {
                token = token.trim();
                if (!token.isEmpty()) {
                    sum += Integer.parseInt(token);
                }
            }

            //computes factorial of the sum
            long result = factorialAPI.factorialOfSum(sum);
            
            //write output to file
            Files.writeString(Path.of(outputPath), String.valueOf(result));

            return result;
		} catch (IOException e) {
			throw new RuntimeException(e);
			}
		}
}
	