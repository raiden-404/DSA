/**
 * Solution for LeetCode Problem 862: Shortest Subarray with Sum at Least K
 * Author: [Add Your Name Here]
 * Problem No: 862
 */
class Solution {
    /**
     * Finds the length of the shortest, non-empty, continuous subarray
     * of nums with a sum of at least k.
     *
     * @param nums The input array of integers (can be positive, negative, or zero).
     * @param k    The target sum threshold.
     * @return The length of the shortest subarray, or -1 if no such subarray exists.
     */
    public static int shortestSubarray(int[] nums, int k) {
        int n = nums.length;

        // Base case: If the array is empty or k is 0, no valid subarray exists.
        // (Note: LeetCode problem constraints state k > 0, but this is good defensive coding)
        if (n == 0) {
            return -1;
        }

        // Initialize minLen to a value larger than any possible array length.
        int minLen = Integer.MAX_VALUE;

        // Create a prefix sum array.
        // We use long to prevent integer overflow when calculating sums.
        // The array is of size n+1 to accommodate a 0 at the beginning (prefixSum[0]).
        // prefixSum[i] will store the sum of nums[0]...nums[i-1].
        long[] prefixSum = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + nums[i];
        }

        // Use a Deque (Double-Ended Queue) to store indices.
        // This deque will store indices 'j' in increasing order
        // such that their corresponding prefix sums (prefixSum[j]) are 
        // also in increasing (monotonic) order.
        Deque<Integer> deque = new ArrayDeque<>();

        // Iterate through all possible *end* indices of subarrays.
        // We loop from i = 0 to n (inclusive) to process all prefix sums,
        // including the initial prefixSum[0] = 0.
        for (int i = 0; i <= n; i++) {
            
            // --- Part 1: Check for valid subarrays ---
            // We want to find a 'j' (from the deque) such that prefixSum[i] - prefixSum[j] >= k.
            // This condition means the subarray nums[j...i-1] has a sum >= k.
            //
            // We check the front of the deque (deque.peekFirst()) because it gives us
            // the smallest 'j' (earliest index) that satisfies the monotonic property.
            // This smallest 'j' will give us the largest possible subarray length (i - j)
            // for the *current* set of candidates in the deque.
            while (!deque.isEmpty() && prefixSum[i] - prefixSum[deque.peekFirst()] >= k) {
                // If the condition is met, we found a valid subarray.
                // The subarray is from index j (deque.peekFirst()) to i-1.
                // Its length is i - j.
                // We update minLen with the smallest length found so far.
                minLen = Math.min(minLen, i - deque.pollFirst());
                
                // We pollFirst() because this index 'j' has been used to form the 
                // shortest possible subarray *ending* at 'i'. Any *other* valid
                // index 'x' still in the deque will be > j, resulting in a 
                // shorter (and thus better) subarray (i - x) which we check
                // in the next iteration of this while loop.
            }

            // --- Part 2: Maintain monotonic property ---
            // We need to ensure that the prefix sums of indices in the deque
            // are strictly increasing.
            //
            // If the current prefixSum[i] is less than or equal to the prefix sum
            // at the end of the deque (prefixSum[deque.peekLast()]),
            // it means the index 'i' is a *better* starting candidate
            // for future subarrays than the index at the end of the deque.
            //
            // Why? If we have j < i and prefixSum[j] >= prefixSum[i]:
            // 1. 'i' is a later index.
            // 2. 'prefixSum[i]' is a smaller (or equal) sum.
            // If a future index 'x' needs a starting sum, prefixSum[i] is
            // always better than prefixSum[j] because:
            //    - If (prefixSum[x] - prefixSum[i] >= k), the length (x - i) will be
            //      *shorter* than (x - j), which is what we want.
            //    - 'prefixSum[i]' makes it "easier" to satisfy the '>= k' condition
            //      compared to the larger 'prefixSum[j]'.
            //
            // Therefore, we remove all indices from the *end* of the deque
            // whose prefix sums are greater than or equal to prefixSum[i].
            while (!deque.isEmpty() && prefixSum[deque.peekLast()] >= prefixSum[i]) {
                deque.pollLast();
            }

            // --- Part 3: Add current index ---
            // Add the current index 'i' to the end of the deque.
            // It is now a candidate for being the *start* of a future subarray.
            deque.offerLast(i);
        }

        // After checking all 'i', if minLen is still its initial value,
        // it means no subarray with sum >= k was found.
        // Otherwise, return the minimum length we recorded.
        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }
}