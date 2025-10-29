package projectapis.network;

import project.annotations.NetworkAPI;
import project.annotations.ProcessAPI;
import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.process.DataStorageAPIImplementation;

@NetworkAPI
@ProcessAPI
public class UserAPIImplementation implements UserAPI {
	
	private String input;
	private String output;
	private String delimiter = ",";
	private final DataStorageAPIImplementation dataStorage = new DataStorageAPIImplementation();
	private final FactorialAPIImplementation factorialAPI = new FactorialAPIImplementation();
	
	public UserAPIImplementation() {
		
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public long executeComputation() {
		if (input == null || input.isEmpty()) {
			return 0;
		}
        int number = Integer.parseInt(input);
        FactorialAPIImplementation factorialAPI = new FactorialAPIImplementation();
        return factorialAPI.computeDigitFactorialSum(number);
	}

	@Override
	public long fetchFactorialOfSum() {
		return executeComputation();
	}

	@Override
	public long fetchExistingResult() {
		return executeComputation();
	}

}
