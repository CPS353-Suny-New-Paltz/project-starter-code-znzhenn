package projectapis.network;

import projectapis.conceptual.FactorialAPI;
import projectapis.process.DataStorageAPI;
import projectapis.process.DataStorageAPIImplementation;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;


public class MultithreadedNetworkAPI implements UserAPI{

	private String inputPath;
	private String outputPath;
	private String delimiter = ",";
	private final DataStorageAPI dataStorage;
	private final FactorialAPI factorialAPI;
	private final ExecutorService executor;
	private final int maxThreads;

    public MultithreadedNetworkAPI(DataStorageAPI dataStorage, FactorialAPI factorialAPI) {
    	this(dataStorage, factorialAPI, 4);
    }
    
    //test usage only
    public MultithreadedNetworkAPI(FactorialAPI factorialAPI) {
        this(new DataStorageAPIImplementation(), factorialAPI, 4); 
    }


    // pool thread
    public MultithreadedNetworkAPI(DataStorageAPI dataStorage, FactorialAPI factorialAPI, int maxThreads) {
        if (dataStorage == null || factorialAPI == null) {
            throw new IllegalArgumentException("dependencies can't be null");
        }
        if (maxThreads <= 0) {
            throw new IllegalArgumentException("maxThreads must be > 0");
        }
        this.dataStorage = dataStorage;
        this.factorialAPI = factorialAPI;
        this.maxThreads = maxThreads;
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
		this.delimiter = delimiter;
		
	}

	@Override
	public long executeComputation() {
		//validate data
		if (inputPath == null || inputPath.isBlank() || outputPath == null || outputPath.isBlank()) {
            return 0L;
        }
		
		List<Integer> numbers = dataStorage.loadIntegers(inputPath, delimiter);
        if (numbers == null || numbers.isEmpty()) {
            return 0L;
        }

        List<Future<Long>> futures = new ArrayList<>();
        List<Long> results = new ArrayList<>();
		
        try {
            // one task per #
            for (int number : numbers) {
                final int n = number;
                if (n < 0) {
                    // skip negatives
                    continue;
                }
                Callable<Long> task = () -> factorialAPI.computeDigitFactorialSum(n);
                Future<Long> future = executor.submit(task);
                futures.add(future);
            }

            // collect results
            for (Future<Long> f : futures) {
                try {
                    Long r = f.get(); 
                    results.add(r == null ? 0L : r);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    results.add(0L);
                } catch (ExecutionException ee) {
                
                    results.add(0L);
                }
            }

            // write results
            dataStorage.storeResults(outputPath, results);

            return results.isEmpty() ? 0L : results.get(results.size() - 1);
        } catch (Exception e) {
        	throw new RuntimeException(e);	
        }

    }
		/*try {
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
    } */

    public void shutdown() {
        executor.shutdown();
    }
	
	
}
