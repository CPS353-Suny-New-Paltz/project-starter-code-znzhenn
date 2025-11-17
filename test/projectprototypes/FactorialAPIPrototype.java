package projectprototypes;

import project.annotations.ConceptualAPIPrototype;
import project.annotations.ProcessAPIPrototype;
import projectapis.ComputationStatus;
import projectapis.process.DataStorageAPI;
import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;

public class FactorialAPIPrototype implements FactorialAPI{

	@ConceptualAPIPrototype
	public static void prototype(FactorialAPI api) {
		// pretend client usage
		long result = api.computeDigitFactorialSum(0);
	}

	@Override
	public long factorialOfSum(int number) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int computeFactorial(int n) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long computeDigitFactorialSum(int number) {
		// TODO Auto-generated method stub
		return 0;
	}
}
