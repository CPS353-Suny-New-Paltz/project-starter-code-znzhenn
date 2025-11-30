package projectapis.conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class FactorialAPIImplementation implements FactorialAPI {

	public FactorialAPIImplementation() {

	}
	
	//computing individual factorial of a number
	@Override
	public long computeFactorial(long n) {
	    try { 
	        if (n < 0) {
	        	return 0L;
	        }
	        
	        if (n == 0 || n == 1) { // base case
                return 1L;
            }

	        long result = 1L;
	        for (int i = 2; i <= n; i++) {
	        	result = Math.multiplyExact(result, i); //safer
	        }
	        return result;
	    } catch (Exception e) {
	        return 0L;
	    }
	}


	// breaking down numbers into individual digits
	@Override
	public long computeDigitFactorialSum(int number) {
		try{
			if (number < 0) {
			return 0L;
			}
			if (number == 0) {      // handles 0
                return computeFactorial(0);
			}
		
			long sum = 0;
			int temp = number;
			
			while (temp > 0) {
				int digit = temp % 10;
				sum += computeFactorial(digit);
				temp /= 10;
			}
			return sum;
		} catch (Exception e) {
			return 0L;
		}
	}
	
	
	// takes in a number, adds up digits, calculates the sum for a factorial
	@Override
	public long factorialOfSum(int number) {
		try {
			
			if (number < 0) {
				return 0L;
			}
	
			int sumDigits = 0;
			int temp = number;
			
			while (temp > 0) {
				sumDigits += temp % 10;
				temp /= 10;
			}
			// if sumDigits =0, compute Factorial will handle it
			return computeFactorial(sumDigits);
		} catch (Exception e) {
			return 0L;
		}
	}
}
