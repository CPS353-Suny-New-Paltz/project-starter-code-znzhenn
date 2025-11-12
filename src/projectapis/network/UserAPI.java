//network api
package projectapis.network;

import project.annotations.NetworkAPI;

@NetworkAPI
public interface UserAPI {

	// set input and output sources
	void setInput(String input);

	void setOutput(String output);

	// not necessary in my computation
	void setDelimiter(String delimiter);

	//starts main computation
	long executeComputation();

	/*
	//fetches sum of digits
	long fetchFactorialOfSum();

	//fetches existing result
	long fetchExistingResult();*/
}
