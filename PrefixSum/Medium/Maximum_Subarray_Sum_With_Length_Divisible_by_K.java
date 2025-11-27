// 3381. Maximum Subarray Sum With Length Divisible by K

class Solution {
    public long maxSubarraySum(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n]; // FIX: Use long
        long sum = 0; // FIX: Use long

        // Calculate Prefix sum
        for(int i = 0; i < n; i++) {
            sum += nums[i];
            prefix[i] = sum;
        }
        
        long max = Long.MIN_VALUE; // FIX: Use Long.MIN_VALUE
        
        // Loop from 0 to k, for getting each offset
        for(int i = 0; i < k; i++) {
            long currentKadaneSum = 0; // Renamed for clarity
            
            // Loop over all subarrays divisible by k starting at offset i
            // FIX: Changed < to <= to include the last chunk
            for(int j = i; j <= n - k; j += k) {
                long prev = j > 0 ? prefix[j-1] : 0;
                long blockSum = prefix[j+k-1] - prev; // The sum of the current chunk of size k
                
                // Kadane's Logic: Should we start fresh with blockSum, or add it to previous?
                if (currentKadaneSum + blockSum < blockSum) {
                    currentKadaneSum = blockSum;
                } else {
                    currentKadaneSum += blockSum;
                }

                // FIX: Update max INSIDE the loop
                if (max < currentKadaneSum) {
                    max = currentKadaneSum;
                }
            }
        }
        return max;
    }
}