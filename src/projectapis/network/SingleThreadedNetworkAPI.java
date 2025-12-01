package projectapis.network;


public class SingleThreadedNetworkAPI implements UserAPI{

	public String outputPath;
	public String inputPath;
	public String delimiter;
	
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
		// TODO Auto-generated method stub
		return 0;
	}
	
}