package projectprototypes;

import project.annotations.NetworkAPIPrototype;
import projectapis.network.UserAPI;

public class UserAPIPrototype implements UserAPI{

	@NetworkAPIPrototype
	public static void prototype(UserAPI api) {
		// pretend client usage
		api.setInput("input.txt");
		api.setOutput("output.txt");

		// not useful to my project but optional
		api.setDelimiter(",");
		api.executeComputation();

	}

	@Override
	public void setInput(String input) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setOutput(String output) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setDelimiter(String delimiter) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long executeComputation() {
		// TODO Auto-generated method stub
		return 0;
	}
}
