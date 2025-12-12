/*
 * LeetCode Problem 258: Add Digits
 * * Explanation:
 * This approach simulates the process described in the problem.
 * It uses a nested loop to repeatedly calculate the sum of digits.
 * The outer loop continues as long as 'num' has two or more digits (>= 10).
 * The inner loop extracts each digit using modulo 10 and adds it to 'sum'.
 * Once the sum of digits is calculated, 'num' is updated to this 'sum',
 * and the process repeats until a single digit remains.
 */

class Solution {
    public int addDigits(int num) {
        int sum;
        
        // Loop until num is reduced to a single digit (0-9)
        while (num >= 10) {
            sum = 0;
            
            // Extract and sum all digits of the current number
            while (num >= 10) {
                sum += num % 10; // Add the last digit
                num /= 10;       // Remove the last digit
            }
            
            // Add the remaining last digit (when num < 10) to sum
            sum += num;
            
            // Update num to the calculated sum for the next iteration
            num = sum;
        }
        
        return num;
    }
}