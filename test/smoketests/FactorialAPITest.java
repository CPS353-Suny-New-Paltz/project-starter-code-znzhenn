package smoketests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import projectapis.conceptual.FactorialAPIImplementation;
import projectapis.FactorialAPI;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FactorialAPITest {

    @Test
    void smokeTestFactorialAPI() {
        FactorialAPI api = Mockito.mock(FactorialAPI.class);
        Mockito.when(api.computeFactorial(5)).thenReturn(120);
        long result = api.computeFactorial(5);

        assertEquals(120, result);
        
        FactorialAPI realAPI = new FactorialAPIImplementation();
        assertNotNull(api);
        
        
    
        
        /*
      //doing the actual computation
    	long factorialOfSum();

    	//necessary for the implementation
    	int computeFactorial(int n);
    	long computeDigitFactorialSum(int number); */
        
        
    }
}
