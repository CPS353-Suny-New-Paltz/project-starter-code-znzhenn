//conceptual api
package projectapis.conceptual;
import project.annotations.ConceptualAPI;

@ConceptualAPI
public interface FactorialAPI{
	//doing the actual computation
<<<<<<< HEAD:src/projectapis/conceptual/FactorialAPI.java
	long factorialOfSum();

	//necessary for the implementation
	int computeFactorial(int n);
=======
	long computeDigitFactorialSum(int number);
>>>>>>> main:src/projectapis/FactorialAPI.java
}
