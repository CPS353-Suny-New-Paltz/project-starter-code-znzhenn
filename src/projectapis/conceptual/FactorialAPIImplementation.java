package projectapis.conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class FactorialAPIImplementation implements FactorialAPI {

	public FactorialAPIImplementation() {

	}
	
	//computing individual factorial of a number
	@Override
	public long computeFactorial(int n) {
		if (n < 0) {
			throw new IllegalArgumentException("n must be >= 0");
		}
		long result = 1L;
		for (int i = 2; i <= n; i++) {
			result = Math.multiplyExact(result, i);
		}
		return result;
	}

	// breaking down numbers into individual digits
	@Override
	public long computeDigitFactorialSum(int number) {
		
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
