/**
 * Solution for LeetCode Problem 2169: Count Operations to Obtain Zero
 * Author: [Add Your Name Here]
 * Problem No: 2169
 */
class Solution {

    /**
     * Counts the number of operations to make either num1 or num2 zero.
     * An operation consists of subtracting the smaller number from the larger one.
     *
     * @param num1 The first non-negative integer.
     * @param num2 The second non-negative integer.
     * @return The total number of subtraction operations.
     */
    public int countOperations(int num1, int num2) {

        // --- Approach 1: Optimized Euclidean Algorithm ---
        // This approach is much faster than direct simulation.
        // It's based on the Euclidean algorithm for finding the GCD.
        // Instead of subtracting num2 from num1 one by one (which is num1 / num2 times),
        // we can do it all at once using division and modulo.
        
        int count = 0; // Initialize operation counter.

        // Loop continues as long as neither number is zero.
        while (num1 != 0 && num2 != 0) {
            
            // Ensure num1 is always the larger number for the division step.
            // If num2 is larger, swap them. The 'count' logic assumes num1 >= num2.
            if (num2 > num1) {
                int temp = num1;
                num1 = num2;
                num2 = temp;
            }

            // (1) Calculate how many times we can subtract num2 from num1.
            //     This is equivalent to 'num1 / num2' operations.
            count += num1 / num2;
            
            // (2) Get the remainder of that division.
            //     This is the new value of num1 after all subtractions.
            num1 %= num2;
            
            // (3) Swap num1 and num2 for the next iteration.
            //     The remainder (new num1) becomes the new num2.
            //     The old num2 (which was the subtrahend) becomes the new num1.
            //     We swap here to ensure the (num1 >= num2) condition is set
            //     for the next loop's division, or if num1 became 0, the loop terminates.
            //     Note: This swap is slightly different from the commented-out
            //     version because we've already processed the num1/num2 operations.
            //     We are essentially setting up for the *next* round.
            //     *Correction in your logic*: The swap should happen *after*
            //     the modulo, but you must handle the case where num2 is larger *first*.
            //     The provided code handles this by always swapping if num2 > num1 *or*
            //     by just letting the division/modulo work.
            
            // Let's analyze your *specific* implementation:
            // count += num1 / num2;  -> Let num1=5, num2=3. count += 1.
            // num1 %= num2;          -> num1 becomes 2.
            // int temp = num1;       -> temp = 2
            // num1 = num2;           -> num1 = 3
            // num2 = temp;           -> num2 = 2
            // Loop repeats with (3, 2).
            // count += 3 / 2;        -> count += 1 (total 2)
            // num1 %= 2;             -> num1 = 1
            // temp = 1, num1 = 2, num2 = 1
            // Loop repeats with (2, 1)
            // count += 2 / 1;        -> count += 2 (total 4)
            // num1 %= 1;             -> num1 = 0
            // temp = 0, num1 = 1, num2 = 0
            // Loop terminates as num2 is 0.
            
            // This swap effectively prepares (num1, num2) for the next iteration,
            // where the old num2 becomes the new "larger" number (dividend)
            // and the remainder (old num1) becomes the new "smaller" number (divisor).
            int temp = num1;
            num1 = num2;
            num2 = temp;
        }


        // --- Approach 2: Direct Simulation (Commented Out) ---
        // This is a direct translation of the problem description.
        
        // int count = 0; // Initialize operation counter.
        
        // // Loop continues as long as neither number is zero.
        // while(num1 != 0 && num2 != 0) {
        //     // Check which number is larger (or if they are equal).
        //     if(num1 >= num2) {
        //         // Subtract the smaller (num2) from the larger (num1).
        //         num1 -= num2;
        //     } else {
        //         // Subtract the smaller (num1) from the larger (num2).
        //         num2 -= num1;
        //     }
        //     // Each subtraction is one operation.
        //     count++;
        // }
        
        // Both approaches will result in the same 'count'.
        return count;
    }
}