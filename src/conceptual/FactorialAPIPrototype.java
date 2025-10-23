package conceptual;

import project.annotations.ConceptualAPIPrototype;
import projectapis.FactorialAPI;

public class FactorialAPIPrototype {

    @ConceptualAPIPrototype
    public static void prototype(FactorialAPI api) {
        // pretend client usage
        long result = api.computeDigitFactorialSum(0);
    }
}
