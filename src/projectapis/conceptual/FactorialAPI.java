//conceptual api
package projectapis.conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface FactorialAPI {
	// doing the actual computation
	long factorialOfSum(int number);

	// necessary for the implementation
	int computeFactorial(int n);

	long computeDigitFactorialSum(int number);
}
