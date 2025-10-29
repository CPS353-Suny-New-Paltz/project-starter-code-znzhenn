//conceptual api
package projectapis.conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface FactorialAPI {
	// doing the actual computation
	long factorialOfSum(int number);

	// computing factorial
	int computeFactorial(int n);

	//yoink each digit
	long computeDigitFactorialSum(int number);
}
