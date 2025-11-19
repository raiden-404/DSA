/**
 * LeetCode Problem: 2154. Keep Multiplying Found Values by Two
 * Difficulty: Easy
 *
 * approach: Using a HashSet for O(1) lookups.
 */
class Solution {
    public int findFinalValue(int[] nums, int original) {
        // Initialize the result with the starting 'original' value.
        // We use a separate variable 'res' (or simply reuse 'original') to track the current value we are looking for.
        int res = original;

        // Use a HashSet to store the elements of the array.
        // This optimizes the search process from O(N) (linear scan) to O(1) (constant time) on average.
        Set<Integer> set = new HashSet<>();

        // Iterate through the input array and add all numbers to the set.
        // Time Complexity for this loop: O(N), where N is the length of nums.
        for(int n : nums) {
            set.add(n);
        }

        // Continuously check if the current 'res' exists in our set of numbers.
        // Since the maximum value in the constraints is 1000, this loop runs a very limited number of times
        // (approx log2(1000) times), which is negligible compared to the array iteration.
        while(set.contains(res)) {
            // If found, multiply the target value by 2.
            res *= 2;
        }

        // Return the final value that was NOT found in the array.
        return res;
    }
}