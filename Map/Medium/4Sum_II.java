/*
 * Solution for LeetCode Problem: 454. 4Sum II
 * Optimal Approach: Meet-in-the-Middle with HashMap
 * Time Complexity: O(N^2)
 * Space Complexity: O(N^2)
 * * Comments added by: AI Assistant
 */
class Solution {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        
        // [Line 3] Get the length of the arrays. 
        // Note: 'n' here is actually 'length - 1', so loops go from 0 to 'n' (inclusive).
        // This is standard if 'n' represents the last index.
        int n = nums1.length - 1; 

        // [Line 5] Initialize a HashMap.
        // This map will store:
        // Key: A target sum (specifically, 0 - (nums1[i] + nums2[j]))
        // Value: The frequency (count) of how many (i, j) pairs produce this target sum.
        Map<Integer, Integer> map = new HashMap<>();
        
        // [Line 6] Initialize the final result.
        // This will count the total number of quadruplets (i, j, k, l)
        // such that nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0.
        int count = 0;

        // --- STAGE 1: Process the first two arrays (nums1 and nums2) ---
        // [Line 9] Loop 1: Iterate through every element in nums1.
        for (int i = 0; i <= n; i++) {
            // [Line 10] Loop 2: Iterate through every element in nums2.
            // This creates an O(N^2) pair-wise combination.
            for (int j = 0; j <= n; j++) {
                
                // [Line 11] Calculate the sum of the pair.
                int sum = nums1[i] + nums2[j];
                
                // [Line 12] Calculate the 'target' we need from nums3 and nums4.
                // If nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0,
                // then nums3[k] + nums4[l] == 0 - (nums1[i] + nums2[j]).
                // This 'target' is what we store as the key.
                int target = 0 - sum;

                // [Line 13] Update the frequency map.
                // map.getOrDefault(target, 0): Get the current count of this 'target',
                // or 0 if it hasn't been seen yet.
                // ... + 1: Increment that count by one.
                // map.put(...): Store the new, updated count back into the map.
                map.put(target, map.getOrDefault(target, 0) + 1);
            }
        }

        // --- STAGE 2: Process the last two arrays (nums3 and nums4) ---
        // [Line 18] Loop 3: Iterate through every element in nums3.
        for (int i = 0; i <= n; i++) {
            // [Line 19] Loop 4: Iterate through every element in nums4.
            // This is the second O(N^2) operation.
            for (int j = 0; j <= n; j++) {
                
                // [Line 21] Calculate the sum of the second pair.
                int sum = nums3[i] + nums4[j];

                // [Line 22] Check if this 'sum' exists as a 'target' key in our map.
                // If it does, it means we found one or more pairs from nums1 and nums2
                // that, when added to *this* current sum, will equal 0.
                if (map.containsKey(sum)) {
                    
                    // [Line 24] Add the frequency to our total count.
                    // map.get(sum) retrieves *how many* (i, j) pairs
                    // created the complement. We add all of them to our count.
                    // For example, if sum is 50, and map.get(50) is 3,
                    // it means 3 different (i, j) pairs created -50,
                    // so we add 3 to our total count.
                    count += map.get(sum);
                }
            }
        }
        
        // [Line 29] Return the total count of all valid quadruplets.
        return count;
    }
}