package projectapis.network;

import projectapis.conceptual.FactorialAPI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class MultithreadedNetworkAPI implements UserAPI{

	private String inputPath;
	private String outputPath;
	private String delimiter;
	private final ExecutorService executor;
    private final SingleThreadedNetworkAPI single;

    public MultithreadedNetworkAPI(FactorialAPI factorialAPI) {
        this.executor = Executors.newFixedThreadPool(4);
        this.single = new SingleThreadedNetworkAPI(dataStorage, factorialAPI);
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
		try {
			return executor.submit(() -> {
            // Run only conceptual API + compute on thread pool
			single.setInput(inputPath);
			single.setOutput(outputPath);
			single.setDelimiter(delimiter);
			
			//execute computation and return result
			return single.executeComputation();

		}).get();
	} catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void shutdown() {
        executor.shutdown();
    }
	
	
}
