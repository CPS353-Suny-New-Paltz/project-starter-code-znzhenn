package projectapis.process;

import java.io.File;
import java.util.stream.Collectors;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import projectapis.ComputationStatus;
import project.annotations.ProcessAPI;

@ProcessAPI
public class DataStorageAPIImplementation implements DataStorageAPI {

	private List<Integer> loadedNumbers = new ArrayList<>();
	private List<Long> savedResults = new ArrayList<>();
	private ComputationStatus status = ComputationStatus.NOT_EXISTS;
	private String savedData = "";

	public DataStorageAPIImplementation() {
	}

	@Override
	public List<Integer> loadIntegers(String inputSource, String delimiter) {

	    loadedNumbers.clear();

	    if (inputSource == null || inputSource.isBlank() ||
	        delimiter == null || delimiter.isBlank()) {
	        status = ComputationStatus.NOT_EXISTS;
	        return loadedNumbers;
	    }

	    File file = new File(inputSource);
	    if (!file.exists()) {
	        status = ComputationStatus.NOT_EXISTS;
	        return loadedNumbers;  // no printing
	    }

	    try (Scanner scanner = new Scanner(file)) {
	        while (scanner.hasNextLine()) {
	            String[] parts = scanner.nextLine().split(delimiter);
	            for (String part : parts) {
	                part = part.trim();
	                if (!part.isEmpty()) {
	                    try {
	                        loadedNumbers.add(Integer.parseInt(part));
	                    } catch (NumberFormatException ignored) {}
	                }
	            }
	        }
	    } catch (Exception ignored) {}

	    status = loadedNumbers.isEmpty() ?
	             ComputationStatus.NOT_EXISTS :
	             ComputationStatus.EXISTS;

	    if (!loadedNumbers.isEmpty()) {
	    	savedData = String.join(delimiter,loadedNumbers.stream().map(String::valueOf).collect(Collectors.toList()));
	    }
	        return loadedNumbers;
	   }
	 
	// store results
	@Override
	public void storeResults(String outputSource, List<Long> results) {
	    
		if (outputSource == null || outputSource.isBlank() || results == null) {
	        status = ComputationStatus.NOT_EXISTS;
	        return;
	    }
		
		savedResults.clear();
		savedResults.addAll(results);
		status = ComputationStatus.EXISTS;
		
		//write to file 
		if (outputSource != null && !outputSource.isEmpty()) {
			File file = new File(outputSource);
			try (PrintWriter writer = new PrintWriter(file)){
				for (Long result: results) {
					writer.println(result);
				}
		} catch (FileNotFoundException e) {
            System.out.println("Error writing results to file: " + outputSource);
            e.printStackTrace();
        	}
		}
	}

	@Override
	public long fetchComputation() {
		return savedResults.isEmpty() ? 0L : savedResults.get(savedResults.size()-1);
	}


	@Override
	public ComputationStatus getComputationStatus() {
		return status;
	}

	@Override
	public String loadData() {
		return savedData;
	}

	@Override
	public void saveComputation() {
		if (!savedResults.isEmpty()) {
			status = ComputationStatus.EXISTS;
		}
	}
}
