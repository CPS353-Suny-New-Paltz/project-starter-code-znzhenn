package projectapis.network;
import projectapis.conceptual.FactorialAPI;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;


public class SingleThreadedNetworkAPI implements UserAPI{

	public String outputPath;
	public String inputPath;
	public String delimiter = ",";
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
			
			Path inPath = Path.of(inputPath).toAbsolutePath();
	        Path outPath = Path.of(outputPath).toAbsolutePath();
	        
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
            
		} catch (IOException e) {
			throw new RuntimeException(e);
			}
		}
}
	