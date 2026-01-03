// 66. Plus One

class Solution {
    public int[] plusOne(int[] digits) {
        // We start with a carry of 1 because the problem asks us to add exactly one.
        int carry = 1;

        // Iterate backwards through the array (from the last index to the first).
        // This mimics how we do addition manually: starting from the least significant digit (rightmost).
        for(int i = digits.length - 1; i >= 0; i--) {
            // Add the current carry to the digit at the current position.
            int sum = carry + digits[i];
            
            // Update the digit at the current position.
            // Using modulo (%) ensures the digit stays between 0-9. 
            // Example: If sum is 10, digits[i] becomes 0. If sum is 5, digits[i] becomes 5.
            digits[i] = sum % 10;
            
            // Calculate the new carry for the next iteration.
            // If sum was 10, carry becomes 1. If sum was 9 or less, carry becomes 0.
            carry = sum / 10;
        }

        // Optimization: If there is no carry left after the loop, it means we didn't 
        // need to expand the number (e.g., 123 -> 124). We can simply return the modified array.
        if(carry == 0) return digits;

        // If we reach here, it means we still have a carry of 1.
        // This happens only if all digits were 9s (e.g., 99 + 1 = 100).
        // The resulting number has one more digit than the original.
        int[] res = new int[digits.length + 1];
        
        // Set the most significant digit (the front) to the remaining carry.
        res[0] = carry; 
        
        // Copy the modified digits into the new array.
        // Note: In the case where we reach here (e.g., 99 -> 100), the 'digits' array 
        // is actually filled with 0s now. So this loop copies those 0s behind the leading 1.
        for(int i = 1; i < res.length; i++) {
            res[i] = digits[i-1];
        }
        
        return res;
    }
}