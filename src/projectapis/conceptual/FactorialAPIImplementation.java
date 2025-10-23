package projectapis.conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class FactorialAPIImplementation implements FactorialAPI {

	public FactorialAPIImplementation() {
		
	}
	
    @Override
    public int computeFactorial(int n) {
    	int result = 1;
    	for (int i = 2; i <= n; i++) result *= i;
        return result;
    }
   
    @Override
    public long computeDigitFactorialSum(int number) {
        long sum = 0;
        while (number > 0) {
            int digit = number % 10;
            sum += computeFactorial(digit);
            number /= 10;
        }
        return sum;
    }
}
