/*
 * Question Name: Unique Length-3 Palindromic Subsequence
 * Question No:   1930
 * Platform:      LeetCode
 * * Complexity Analysis:
 * Time:  O(N) - We scan the string once, then scan segments for 26 characters.
 * Space: O(1) - We use fixed-size arrays (size 26) which do not scale with N.
 */

import java.util.Arrays;

class Solution {
    public int countPalindromicSubsequence(String s) {
        // Store the first and last occurrence of each character
        int[] first = new int[26];
        int[] last = new int[26];
        
        // Initialize 'first' array to -1 because 0 is a valid index
        Arrays.fill(first, -1);
        
        // Pass 1: Populate first and last indices
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            
            if (first[idx] == -1) {
                first[idx] = i;
            }
            
            // Always update last to the current index
            last[idx] = i;
        }
        
        int result = 0;
        
        // Pass 2: Check between first and last occurrences for every letter
        for (int i = 0; i < 26; i++) {
            // We only care if the character exists and start < end
            // We need at least 1 character between them, so last > first + 1
            if (first[i] != -1 && last[i] > first[i] + 1) {
                
                // Use a boolean array as a Set to count unique chars between start and end
                // This is faster than HashSet
                boolean[] seen = new boolean[26];
                int uniqueCount = 0;
                
                for (int j = first[i] + 1; j < last[i]; j++) {
                    int charIdx = s.charAt(j) - 'a';
                    if (!seen[charIdx]) {
                        uniqueCount++;
                        seen[charIdx] = true;
                        
                        // Optimization: If we found all 26 chars, break early
                        if (uniqueCount == 26) break; 
                    }
                }
                
                result += uniqueCount;
            }
        }
        
        return result;
    }
}