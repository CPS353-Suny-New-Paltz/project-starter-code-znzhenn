package projectapis.conceptual;

import project.annotations.ConceptualAPI;

@ConceptualAPI
public class FactorialAPIImplementation implements FactorialAPI {

    public FactorialAPIImplementation() {}

    @Override
    public int computeFactorial(int n) {
        if (n < 0) throw new IllegalArgumentException("n must be >= 0");
        int result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    @Override
    public long computeDigitFactorialSum(int number) {
        long sum = 0;
        int temp = number;
        while (temp > 0) {
            int digit = temp % 10;
            sum += computeFactorial(digit);
            temp /= 10;
        }
        return sum;
    }

    @Override
    public long factorialOfSum(int number) {
        int sumDigits = 0;
        int temp = number;
        while (temp > 0) {
            sumDigits += temp % 10;
            temp /= 10;
        }
        return computeFactorial(sumDigits);
    }
}
