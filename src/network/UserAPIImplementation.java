package network;

import conceptual.FactorialAPIImplementation;
import project.annotations.NetworkAPI;

@NetworkAPI
public class UserAPIImplementation implements UserAPI {
	
	private String input;
	private String output;
	
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
