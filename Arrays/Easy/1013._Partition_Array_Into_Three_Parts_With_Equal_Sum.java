/*
 * Problem: 1013. Partition Array Into Three Parts With Equal Sum
 * Difficulty: Easy
 * Time Complexity: O(N) - We traverse the array twice.
 * Space Complexity: O(1) - We modify the input array in-place.
 */
class Solution {
    public boolean canThreePartsEqualSum(int[] arr) {
        // 1. Calculate the total sum of the array.
        // We accumulate the sum directly in the array (Prefix Sum) to save space.
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            arr[i] = sum; // Storing prefix sum at index i
        }

        // 2. Check if the total sum can be split into 3 equal integers.
        // If the sum is not divisible by 3, it's impossible to have 3 equal parts.
        if (sum % 3 != 0) {
            return false;
        }

        // 3. Define the target sum for each part.
        int target = sum / 3;
        int times = 0; // Counter for how many parts we have found

        // 4. Iterate through the array to find the split points.
        // We stop at length - 1 because the 3rd part must contain at least one element.
        for (int i = 0; i < arr.length - 1; i++) {
            
            // Check if the current prefix sum equals the target we are looking for.
            // First we look for 'target' (1/3), then 'target * 2' (2/3).
            if (arr[i] == target * (times + 1)) {
                times++;
                
                // If we found the first two parts (1/3 and 2/3), 
                // the remainder of the array automatically forms the 3rd part.
                if (times == 2) {
                    return true;
                }
            }
        }

        // If we finish the loop without finding 2 split points, return false.
        return false;
    }
}