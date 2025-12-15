package test;
import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.FactorialAPIImplementation;

public class BottleneckTest {

    public static void main(String[] args) {
        FactorialAPI api = new FactorialAPIImplementation();

        // heavy numbers
        int[] testNumbers = {725102312, 91734610, 1238141088, 223414021};

     	// jvm warm-up
        for (int i = 0; i < 10_000; i++) {
            for (int number : testNumbers) {
                api.computeDigitFactorialSum(number);
            }
        }
        
        long start = System.nanoTime();
        long last = 0L;

        int iterations = 100000;
        for (int i = 0; i < iterations; i++) {
        	for (int number : testNumbers) {
        		last = api.computeDigitFactorialSum(number);     	
        	}
        }
        
        long end = System.nanoTime();
        System.out.println("Last result: " + last);
        System.out.println("Execution time (ms): " + ((end - start) / 1_000_000));
    }
}
