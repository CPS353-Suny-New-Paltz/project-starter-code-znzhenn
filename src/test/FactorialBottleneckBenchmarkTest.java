package test;
import projectapis.conceptual.FactorialAPI;
import projectapis.conceptual.OptimizedFactorialAPIImplementation;

import projectapis.conceptual.FactorialAPIImplementation;

public class FactorialBottleneckBenchmarkTest {

    public static void main(String[] args) {
        //FactorialAPI api = new FactorialAPIImplementation();
        FactorialAPI api = new OptimizedFactorialAPIImplementation();


        // heavy numbers
        int[] testNumbers = {725102312, 91734610, 1238141088, 223414021};

     	// jvm warm-up
        for (int i = 0; i < 100000; i++) {
            for (int number : testNumbers) {
                api.computeDigitFactorialSum(number);
            }
        }
        
        long start = System.nanoTime();
        long last = 0L;

        int iterations = 1000000;
        for (int i = 0; i < iterations; i++) {
        	for (int number : testNumbers) {
        		last = api.computeDigitFactorialSum(number);     	
        	}
        }
        
        long end = System.nanoTime();
        System.out.println("Last result: " + last);
        System.out.println("Execution time (ms): " + ((end - start) / 1_000_000));
    }
    
    /* non-optimized
     * last result: 63
     * execution time (ms) 112
     * */
    
    /* optimized
     * last result:
     * execution time (ms) 59
     * */
}
