package Hard;
class Solution {

    /**
     * 41. First Missing Positive
     *
     * Finds the first positive integer that is not present in an unsorted array.
     *
     * This optimal solution uses the "Cyclic Sort" pattern, which aims to place each
     * number in its correct position within the array. The core idea is that the number '1'
     * should be at index 0, '2' should be at index 1, and so on. By rearranging the
     * array this way, we can then iterate through it and find the first index `i`
     * where the number `nums[i]` is not equal to `i + 1`.
     *
     * Time Complexity: O(n) - Although there are nested loops, the inner `while` loop
     * only performs a swap when a number is not in its correct place. Each swap moves at
     * least one number closer to its final destination, so the total number of swaps
     * is bounded by n.
     *
     * Space Complexity: O(1) - The sorting is done in-place, without using any extra
     * data structures proportional to the input size.
     *
     * @param nums The input array of integers.
     * @return The smallest missing positive integer.
     */
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;

        // ========================================================================
        // PHASE 1: Rearrange the array using Cyclic Sort
        // The goal is to place every number `x` (where 1 <= x <= n)
        // into its correct index, which is `x - 1`.
        // ========================================================================
        int i = 0;
        while (i < n) {
            // Get the value at the current index.
            int value = nums[i];

            // Determine the index where this value *should* be.
            // For example, the number 1 should be at index 0, 3 at index 2, etc.
            int correctIndex = value - 1;

            // We only care about numbers that are positive and can fit within the array's bounds (1 to n).
            // If the current value is within this range AND it's not already in its correct place,
            // we swap it.
            if (value > 0 && value <= n && value != nums[correctIndex]) {
                // The condition `value != nums[correctIndex]` is crucial.
                // It ensures that we don't get into an infinite loop if there are duplicates.
                // For example, in [1, 1], when at index 1, value is 1, correctIndex is 0.
                // Since nums[1] == nums[0], we don't swap and avoid an infinite loop.

                // Perform the swap to move `value` to its `correctIndex`.
                nums[i] = nums[correctIndex];
                nums[correctIndex] = value;
            } else {
                // If the number is not a candidate for swapping (e.g., it's negative, too large,
                // or already in the correct spot), we just move to the next index.
                i++;
            }
        }

        // After Phase 1, the array is partially sorted. For an input like [3, 4, -1, 1],
        // it might now look something like [1, -1, 3, 4].

        // ========================================================================
        // PHASE 2: Find the first missing positive number
        // Now, we iterate through the rearranged array to find the first
        // position where the number doesn't match its expected value.
        // ========================================================================
        for (i = 0; i < n; i++) {
            // Check if the number at index `j` is the one we expect.
            // We expect index 0 to have 1, index 1 to have 2, etc.
            if (nums[i] != i + 1) {
                // If we find a mismatch, `j + 1` is the smallest positive number
                // that was not present in the original array.
                return i + 1;
            }
        }

        // ========================================================================
        // PHASE 3: Handle the edge case
        // If the loop above completes without returning, it means every position
        // contains its expected number (i.e., the array contains all numbers from 1 to n).
        // ========================================================================
        // For example, if nums was [1, 2, 3], the loop will finish.
        // In this case, the first missing positive is the next number in sequence.
        return n + 1;
    }
}