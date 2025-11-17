package projectprototypes;

import project.annotations.ConceptualAPIPrototype;
import projectapis.conceptual.FactorialAPI;

public class FactorialAPIPrototype implements FactorialAPI {

    @ConceptualAPIPrototype
    public static void prototype(FactorialAPI api) {
        // pretend client usage
        long result = api.computeDigitFactorialSum(145); // 145 = 1!+4!+5!
        System.out.println("Prototype result: " + result);
    }

    @Override
    public long factorialOfSum(int number) {
        // compute factorial of sum of digits
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return computeFactorial(sum);
    }

    @Override
    public int computeFactorial(int n) {
        int fact = 1;
        for (int i = 2; i <= n; i++) fact *= i;
        return fact;
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
