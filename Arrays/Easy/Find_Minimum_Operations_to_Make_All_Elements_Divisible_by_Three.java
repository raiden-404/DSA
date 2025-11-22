/**
 * Question Name: Find Minimum Operations to Make All Elements Divisible by Three
 * Question No: 3190
 * Difficulty: Easy
 */
class Solution {
    public int minimumOperations(int[] nums) {
        // Initialize a counter to track the total operations needed
        int res = 0;
        
        // Iterate through every integer in the array
        for (int n : nums) {
            // Check if the current number is NOT divisible by 3.
            // A number not divisible by 3 will have a remainder of either 1 or 2.
            // - If remainder is 1: We subtract 1 (1 operation).
            // - If remainder is 2: We add 1 (1 operation).
            // In both cases, exactly 1 operation is required.
            if (n % 3 != 0) {
                res++;
            }
        }
        
        // Return the total count of numbers that required an operation
        return res;
    }
}