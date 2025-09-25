package projectapis;

import project.annotations.ConceptualAPIPrototype;

public class FactorialAPIPrototype {

    @ConceptualAPIPrototype
    public static FactorialAPI create() {
    	return new FactorialAPI() {
    		@Override
    		public long factorialOfSum() {
    			return 0L;
    		}
    	};
    }
}

//insert default values (no magic numbers, and make it work)