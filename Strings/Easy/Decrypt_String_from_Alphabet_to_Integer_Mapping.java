/**
 * LeetCode Problem 1309: Decrypt String from Alphabet to Integer Mapping
 * * Problem Description:
 * Given a string s formed by digits and '#', we want to map it to English lowercase characters:
 * - Characters ('a' to 'i') are represented by ("1" to "9").
 * - Characters ('j' to 'z') are represented by ("10#" to "26#").
 */
class Solution {
    public String freqAlphabets(String s) {
        // Use StringBuilder for efficient string manipulation (mutable sequence)
        StringBuilder res = new StringBuilder();
        
        int num = 0;
        
        // Iterate backwards through the string.
        // This is optimal because the '#' suffix tells us immediately if we need
        // to consume 3 characters (e.g., "10#") or just 1 character (e.g., "1").
        for (int i = s.length() - 1; i >= 0; i--) {
            
            // Case 1: We encounter a '#', indicating a two-digit number (10-26)
            // affecting the characters 'j' through 'z'.
            if (s.charAt(i) == '#') {
                // Parse the two digits occurring before the '#'.
                // substring(i-2, i) extracts characters at index i-2 and i-1.
                num = Integer.parseInt(s.substring(i - 2, i));
                
                // Skip the next two indices in our loop because we just processed them
                // as part of this two-digit number.
                i -= 2; 
            } 
            // Case 2: We encounter a regular digit, indicating a single-digit number (1-9)
            // affecting the characters 'a' through 'i'.
            else {
                // Parse the single digit at the current index.
                // Note: s.substring(i, i+1) is equivalent to just taking the char at i.
                num = Integer.parseInt(s.substring(i, i + 1));
            }
            
            // Convert the integer to its corresponding ASCII character.
            // 'a' corresponds to 1.
            // Formula: (num - 1) gives the 0-based offset. 
            // Adding 'a' shifts it to the correct ASCII value.
            // Example: If num = 1 -> 1 + 97 - 1 = 97 ('a').
            res.append((char) (num + 'a' - 1));
        }
        
        // Since we iterated backwards, the characters were appended in reverse order.
        // We must reverse the StringBuilder to get the final correct string.
        return res.reverse().toString();
    }
}