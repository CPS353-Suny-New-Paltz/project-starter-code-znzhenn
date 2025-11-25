package projectapis.conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class FactorialAPIImplementation implements FactorialAPI {

	public FactorialAPIImplementation() {

	}
	
	//computing individual factorial of a number
	@Override
	public long computeFactorial(long n) {
	    try { //adding data validation
	        if (n < 0) {
	        	return 0L;
	        }

	        long result = 1L;
	        for (int i = 2; i <= n; i++) {
	            result = result * i; // safe multiplication
	        }
	        return result;
	    } catch (Exception e) {
	        return 0L;
	    }
	}


	// breaking down numbers into individual digits
	@Override
	public long computeDigitFactorialSum(int number) {
		//adding basic data validation. if 0, then it doesn't work
		try{
			if (number < 0) {
		}
			return 0L;
		
			long sum = 0;
			int temp = number;
			if (temp == 0) {
				return computeFactorial(0);
			}
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
			
			//same data validation
			if (number < 0) {
				return 0L;
			}
	
			int sumDigits = 0;
			int temp = number;
			if (temp == 0) {
				sumDigits =0;
			}
			while (temp > 0) {
				sumDigits += temp % 10;
				temp /= 10;
			}
			return computeFactorial(sumDigits);
		} catch (Exception e) {
			return 0L;
		}
	}
}
