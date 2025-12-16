package projectapis.conceptual;

// primary cpu bottleneck: computation of factorial values in tight loops

public class OptimizedFactorialAPIImplementation implements FactorialAPI {

    // compute factorials beforehand
    private static final long[] DIGIT_FACTORIALS = {
            1L,        // 0!
            1L,        // 1!
            2L,        // 2!
            6L,        // 3!
            24L,       // 4!
            120L,      // 5!
            720L,      // 6!
            5040L,     // 7!
            40320L,    // 8!
            362880L    // 9!
    };

    @Override
    public long computeDigitFactorialSum(int number) {
        if (number < 0) {
            return 0L;
        }

        if (number == 0) {
            return DIGIT_FACTORIALS[0];
        }

        long sum = 0;
        int temp = number;

        while (temp > 0) {
            int digit = temp % 10;
            sum += DIGIT_FACTORIALS[digit];
            temp /= 10;
        }

        return sum;
    }

    // not yet optimized
    @Override
    public long computeFactorial(long n) {
        if (n < 0 || n > 9) {
            return 0L;
        }
        return DIGIT_FACTORIALS[(int) n];
    }

    @Override
    public long factorialOfSum(int number) {
        if (number < 0) {
            return 0L;
        }

        int sumDigits = 0;
        int temp = number;
        while (temp > 0) {
            sumDigits += temp % 10;
            temp /= 10;
        }

        return computeFactorial(sumDigits);
    }
}
