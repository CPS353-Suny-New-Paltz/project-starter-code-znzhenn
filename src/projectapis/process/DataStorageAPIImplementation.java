package projectapis.process;

import java.io.File;
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
		 File file = new File(inputSource);
		 if (!file.exists()) {
			 System.out.println("Input file not found: " + inputSource);
			 status = ComputationStatus.NOT_EXISTS;
			 return loadedNumbers;
		 }
		 try (Scanner scanner = new Scanner(file)) {
			 while (scanner.hasNextLine()) {
				 String line = scanner.nextLine();
				 String[] parts = line.split(delimiter);
				 for (String part : parts) {
					 part = part.trim();
					 if (!part.isEmpty()) {
						 try {
							 loadedNumbers.add(Integer.parseInt(part));
						 } catch (NumberFormatException e) {
							 System.out.println("Warning: invalid number '" + part + "' skipped.");
	                       }
	                    }
	                }
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	        status = loadedNumbers.isEmpty() ? ComputationStatus.NOT_EXISTS : ComputationStatus.EXISTS;
	        savedData = String.join(delimiter, loadedNumbers.stream().map(String: valueOf).toList());
	        return loadedNumbers;
	   }
	 
	// store results
	@Override
	public void storeResults(String outputSource, List<Long> results) {
		
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

	//returns the first stored computation result
	@Override
	public long fetchComputation() {
		return 0;
		//return savedResults.isEmpty() ? 0L : savedResults.get(0);
	}

	@Override
	public ComputationStatus getComputationStatus() {
		return ComputationStatus.NOT_EXISTS;
	}

	@Override
	public String loadData() {
		//return savedData;
		return "";
	}

	@Override
	public void saveComputation() {
		//status = ComputationStatus.EXISTS;
	}
}
