/*
 * 974. Subarray Sums Divisible by K
 *
 * LeetCode Problem: https://leetcode.com/problems/subarray-sums-divisible-by-k/
 *
 * This solution uses the prefix sum modulo K technique.
 * The core idea is that if two prefix sums have the same remainder when divided by K,
 * the sum of the subarray between them must be divisible by K.
 *
 * (prefix_sum[j] - prefix_sum[i]) % k == 0
 * ...is equivalent to...
 * prefix_sum[j] % k == prefix_sum[i] % k
 *
 * We use an array 'arr' to store the frequency of each remainder [0, k-1].
 */
class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        
        // 'arr' stores the frequency of each remainder.
        // We use an array of size k since remainders can only be from 0 to k-1.
        int[] arr = new int[k];

        // Initialize remainder 0 count to 1. This handles subarrays
        // that start from the beginning of the array (index 0).
        arr[0] = 1;

        int res = 0; // This will store the total count of valid subarrays
        int sum = 0; // This is the running prefix sum

        for(int n : nums) {
            // Update the running prefix sum
            sum += n;

            // Calculate the remainder
            int rem = sum % k;

            // IMPORTANT: Handle negative remainders.
            // In Java, % is a remainder operator, not a modulo operator.
            // e.g., -2 % 6 = -2. We need to convert this to the
            // positive equivalent, which is 4. (-2 + 6 = 4).
            if(rem < 0) {
                rem += k;
            }

            // If we have seen this remainder 'x' times before,
            // it means we can form 'x' new subarrays ending at the
            // current position that are divisible by k.
            res += arr[rem];

            // Increment the frequency count for the current remainder
            arr[rem]++;
        }

        return res;
    }
}