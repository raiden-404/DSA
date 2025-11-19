/*
 * Question Name: Excel Sheet Column Number
 * Question No:   171
 * Platform:      LeetCode
 * Difficulty:    Easy
 */

class Solution {
    public int titleToNumber(String columnTitle) {
        // This variable will store the final accumulated result
        int res = 0;
        
        // 'mul' represents the place value in Base-26.
        // It starts at 1 (26^0), then 26 (26^1), then 676 (26^2), etc.
        int mul = 1;
        
        // We iterate backwards from the last character (Least Significant Character)
        // to the first character (Most Significant Character).
        for(int i = columnTitle.length() - 1; i >= 0; i--) {
            
            // 1. Get the current character: columnTitle.charAt(i)
            // 2. Convert char to int: 'A' becomes 1, 'B' becomes 2, etc.
            //    We do this by subtracting ASCII 'A' and adding 1.
            // 3. Multiply by the current place value (mul).
            // 4. Add to the result.
            res += mul * (columnTitle.charAt(i) - 'A' + 1);
            
            // Update the place value for the next character to the left.
            // Logic: Like Base-10 goes 1, 10, 100... Base-26 goes 1, 26, 676...
            mul *= 26;
        }
        
        return res;
    }
}