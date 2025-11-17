package smoketests;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.mockito.Mockito;

import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class FactorialAPITest {
	
	public static void main(String[] args) {
        FactorialAPI api = new FactorialAPIImplementation();
        long sum = api.computeDigitFactorialSum(247);
        System.out.println("Sum of factorials of 247 digits: " + sum);
    }

	@Test
	void smokeTestFactorialAPI() {

		/*
		 * mock objects FactorialAPI api = Mockito.mock(FactorialAPI.class);
		 * Mockito.when(api.computeFactorial(5)).thenReturn(120); long result =
		 * api.computeFactorial(5);
		 * 
		 * assertEquals(120, result);
		 * 
		 * FactorialAPI realAPI = new FactorialAPIImplementation(); assertNotNull(api);
		 */

		FactorialAPI api = new FactorialAPIImplementation();
		assertNotNull(api);

		long sum = api.computeDigitFactorialSum(247);
		assertEquals(5066L, sum, "should pass");
		
		//System.out.println("Sum of factorials of 247 digits: " + sum);

		/*
		 * //doing the actual computation long factorialOfSum();
		 * 
		 * //necessary for the implementation int computeFactorial(int n); long
		 * computeDigitFactorialSum(int number);
		 */

	}
}
