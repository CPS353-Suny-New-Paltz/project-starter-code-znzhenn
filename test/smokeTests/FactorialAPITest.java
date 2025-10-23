package smokeTests;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.assertEquals;

import projectapis.conceptual.FactorialAPI;

public class FactorialAPITest {

    @Test
    void smokeTestFactorialAPI() {
        FactorialAPI api = Mockito.mock(FactorialAPI.class);
        Mockito.when(api.computeFactorial(5)).thenReturn(120);

        long result = api.computeFactorial(5);

        assertEquals(25, result);
    }
}
