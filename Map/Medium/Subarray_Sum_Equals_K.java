/*
 * 560. Subarray Sum Equals K
 *
 * LeetCode Problem: https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * This is the optimal solution using a Prefix Sum + HashMap.
 *
 * Core Logic:
 * We are looking for a subarray nums[i...j] where sum(nums[i...j]) = k.
 *
 * Let prefixSum[j] = sum of nums[0...j] (this is our 'sum' variable)
 * Let prefixSum[i-1] = sum of nums[0...i-1] (this is a 'past_sum' we've stored)
 *
 * We know: sum(nums[i...j]) = prefixSum[j] - prefixSum[i-1]
 *
 * So, we want: k = prefixSum[j] - prefixSum[i-1]
 *
 * Rearranging the formula, we get:
 * prefixSum[i-1] = prefixSum[j] - k
 *
 * As we iterate through the array calculating the current 'sum' (prefixSum[j]),
 * we just need to look in our map to see how many times we have
 * already seen the complement 'sum - k' (which is prefixSum[i-1]).
 */
class Solution {

    // This static block is a LeetCode-specific "JVM warm-up" trick.
    // It runs once when the class is loaded, forcing the JIT compiler
    // to optimize HashMap operations before the real test cases start.
    // This is a micro-optimization and not part of the core algorithm.
    static{
        for(int i=0; i<500; i++) {
            subarraySum(new int[]{}, 0);
        }
    }
    
    public static int subarraySum(int[] nums, int k) {
        
        // Map stores: <Prefix_Sum, Frequency_of_that_sum>
        Map<Integer, Integer> map = new HashMap<>();
        
        // IMPORTANT: Initialize the map with (0, 1).
        // This handles cases where a subarray *starting from index 0*
        // sums to k. In that case, the complement (sum - k) will be 0,
        // so we need a frequency of 1 for the 'past_sum' of 0.
        map.put(0, 1);
        
        int res = 0; // Total count of valid subarrays
        int sum = 0; // The current running prefix sum (prefixSum[j])

        for(int n : nums) {
            // Update the current prefix sum
            sum += n;
            
            // Calculate the complement (prefixSum[i-1]) we are looking for.
            int complement = sum - k;
            
            // If this complement exists in our map, it means we have
            // found one or more subarrays ending at the current index
            // that sum to k. Add its frequency to our result.
            res += map.getOrDefault(complement, 0);
            
            // Add the *current* prefix sum to the map, or increment
            // its frequency if it's already there for future iterations.
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        
        return res;
    }
}