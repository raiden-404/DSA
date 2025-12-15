/**
 * Problem: 2110. Number of Smooth Descent Periods of a Stock
 * Difficulty: Medium
 * * Approach: Linear Scan / Math
 * Time Complexity: O(N) - We iterate through the array exactly once.
 * Space Complexity: O(1) - We use only a few variables for storage.
 */
class Solution {
    public long getDescentPeriods(int[] prices) {
        // 1. Base Case: Every single day counts as a smooth descent period of length 1.
        //    So, we start 'res' with the total number of days.
        long res = 0;
        res += prices.length;

        // 'upto' tracks the number of valid consecutive descent 'steps' (differences of 1)
        // encountered in the current sequence.
        long upto = 0;

        for (int i = 1; i < prices.length; i++) {
            // 2. Check if the current price is exactly 1 less than the previous day
            if (prices[i - 1] - prices[i] == 1) {
                // We found a valid descent step, extend the current sequence
                upto++;
            } else {
                // 3. The descent sequence is broken.
                //    Calculate the number of sub-periods (length >= 2) for the sequence just ended.
                if (upto > 0) {
                    // Formula: Sum of integers from 1 to 'upto'.
                    // If we have 'upto' transitions, it contributes (upto * (upto + 1)) / 2 extra periods.
                    res += (upto * (upto + 1)) / 2;
                    
                    // Reset 'upto' for the next sequence
                    upto = 0;
                }
            }
        }

        // 4. Handle the Edge Case:
        //    If the array ends while we are still in a descent, the loop finishes before 
        //    adding the calculation for the final sequence. We add it here.
        res += (upto * (upto + 1)) / 2;

        return res;
    }
} 