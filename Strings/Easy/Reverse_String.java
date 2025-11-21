/*
 * Question Name: Reverse String
 * Question No:   344
 * Platform:      LeetCode
 *
 * Complexity Analysis:
 * Time:  O(N) - We iterate through N/2 elements. This simplifies to O(N).
 * Space: O(1) - We perform the swaps in-place using only one temporary variable.
 */

class Solution {
    public void reverseString(char[] s) {
        // n represents the last index of the array
        int n = s.length - 1;
        
        // Iterate only up to the middle point
        // If we went further, we would swap the characters back to their original spots
        for(int i = 0; i <= n / 2; i++) {
            
            // Swap the element at the front (i) with the element at the back (n-i)
            char temp = s[i];
            s[i] = s[n - i];
            s[n - i] = temp;
        }
    }
}