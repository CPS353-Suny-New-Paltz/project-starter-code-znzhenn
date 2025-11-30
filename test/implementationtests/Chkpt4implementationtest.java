package implementationtests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;

public class Chkpt4implementationtest {

    private final FactorialAPI api = new FactorialAPIImplementation();

    @Test
    public void testFactorialBaseCases() {
        assertEquals(1L, api.computeFactorial(0)); // 0
        assertEquals(1L, api.computeFactorial(1)); // 1
        assertEquals(2L, api.computeFactorial(2)); // 2
    }

    @Test
    public void testFactorialRejectsNegative() {
        assertEquals(0L, api.computeFactorial(-5));
    }

    @Test
    public void testDigitFactorialHandlesZero() {
        assertEquals(1L, api.computeDigitFactorialSum(0));
    }
}
