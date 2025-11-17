package projectprototypes;

import project.annotations.ConceptualAPIPrototype;
import projectapis.conceptual.FactorialAPI;

public class FactorialAPIPrototype {

	@ConceptualAPIPrototype
	public static void prototype(FactorialAPI api) {
		// pretend client usage
		long result = api.computeDigitFactorialSum(0);
	}
}
