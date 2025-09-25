package projectapis;

import project.annotations.NetworkAPIPrototype;

public class UserAPIPrototype {

    @NetworkAPIPrototype
    
    public static UserAPI create() {
    	return new UserAPI() {
    		@Override
    		public long fetchFactorialOfSum() {
    			return 0L;
    		}
    		
    		@Override
    		public long fetchExistingResult() {
    			return 0L;
    		}
    	};
    	
    }
} //end of class
