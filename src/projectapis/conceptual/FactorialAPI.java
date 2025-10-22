//conceptual api
package projectapis.conceptual;
import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface FactorialAPI{
	//doing the actual computation
	long factorialOfSum();

	//necessary for the implementation
	int computeFactorial(int n);
}
