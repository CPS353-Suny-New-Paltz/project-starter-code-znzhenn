//conceptual api
package projectapis;
import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface FactorialAPI{
	//doing the actual computation
	long factorialOfSum();

	//necessary for the implementation
	int computeFactorial(int n);
	long computeDigitFactorialSum(int number);
}

