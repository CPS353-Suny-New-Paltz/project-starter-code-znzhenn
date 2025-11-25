package projectapis.conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class FactorialAPIImplementation implements FactorialAPI {

	public FactorialAPIImplementation() {

	}
	
	//computing individual factorial of a number
	@Override
	public long computeFactorial(long n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be >= 0");
		}
		long result = 1L;
		for (int i = 2; i <= n; i++) {
			result = result*i; //remove exception
		}
		return result;
	}

	// breaking down numbers into individual digits
	@Override
	public long computeDigitFactorialSum(int number) {
		//adding basic data validation. if 0, then it doesn't work
		if (number < 0) {
			return 0L;
		}
		
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
	}
	
	
	// takes in a number, adds up digits, calculates the sum for a factorial
	@Override
	public long factorialOfSum(int number) {
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
	}
}
