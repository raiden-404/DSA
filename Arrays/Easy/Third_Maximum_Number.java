/*
 * Problem: LeetCode 414. Third Maximum Number
 * Goal: return the third distinct maximum number in this array. 
 * If the third maximum does not exist, return the maximum number.
 */
class Solution {
    public int thirdMax(int[] nums) {
        // We use 'long' instead of 'int' for the tracking variables.
        // Reason: If the input array contains Integer.MIN_VALUE, using int variables initialized 
        // to Integer.MIN_VALUE would make it impossible to distinguish between an "empty" spot 
        // and an actual value of Integer.MIN_VALUE found in the array.
        long max = Long.MIN_VALUE;
        long secMax = Long.MIN_VALUE;
        long thirdMax = Long.MIN_VALUE;

        for (int n : nums) {
            // Case 1: Found a new global maximum
            // We must shift all current values down by one rank before updating max.
            if (n > max) {
                thirdMax = secMax; // The old 2nd becomes 3rd
                secMax = max;      // The old 1st becomes 2nd
                max = n;           // Update 1st
            } 
            // Case 2: Found a new 2nd maximum
            // It must be smaller than 'max' but distinct (n != max).
            else if (n > secMax && n != max) {
                thirdMax = secMax; // The old 2nd becomes 3rd
                secMax = n;        // Update 2nd
            } 
            // Case 3: Found a new 3rd maximum
            // It must be smaller than 'secMax' but distinct from both larger values.
            else if (n > thirdMax && n != secMax && n != max) {
                thirdMax = n;      // Update 3rd
            }
            
            // Note: If n equals max, secMax, or thirdMax, we ignore it 
            // because we are looking for DISTINCT maximums.
        }

        // Logic check for the return value:
        // If thirdMax is still the initial Long.MIN_VALUE, it means we didn't find 
        // 3 distinct numbers in the array. In this case, the problem requires returning the global max.
        if (thirdMax == Long.MIN_VALUE) {
            return (int) max;
        }

        // Otherwise, return the 3rd distinct maximum found.
        return (int) thirdMax;
    }
}