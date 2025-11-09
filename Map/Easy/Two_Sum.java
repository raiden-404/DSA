// Problem: 1. Two Sum

import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Finds two numbers in an array that add up to a specific target.
     *
     * @param nums   The input array of integers.
     * @param target The target sum.
     * @return An array of two indices [index1, index2] such that nums[index1] + nums[index2] == target.
     */
    public int[] twoSum(int[] nums, int target) {
        
        // Create a HashMap to store complements and their indices.
        // Key: The complement needed (i.e., target - nums[i])
        // Value: The index (i) of the number that produced that complement
        Map<Integer, Integer> map = new HashMap<>();
        
        // Get the last index for the loop condition.
        int n = nums.length - 1;

        // Iterate through the array once.
        for (int i = 0; i <= n; i++) {
            
            // Check if the *current number* (nums[i]) already exists as a key in the map.
            // If it does, it means the current number is the *complement* to a number
            // we have already processed and stored in the map.
            if (map.containsKey(nums[i])) {
                
                // We found the solution.
                // 'i' is the index of the current number.
                // 'map.get(nums[i])' is the index of the other number (its complement) we stored earlier.
                return new int[]{i, map.get(nums[i])};
            }
            
            // If no match is found:
            // 1. Calculate the complement needed for the *current number*.
            // 2. Put this complement (target - nums[i]) into the map as the key.
            // 3. Store the *current index* (i) as the value.
            //
            // This prepares the map for future iterations. If we later find a number
            // that equals this complement, the 'if' block above will trigger.
            map.put(target - nums[i], i);
        }

        // If the loop finishes without finding a solution (which the problem
        // statement guarantees won't happen), return an empty array.
        return new int[]{};
    }
}