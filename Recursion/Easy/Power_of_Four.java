/*
 * LeetCode 342. Power of Four
 */
class Solution {
    public boolean isPowerOfFour(int n) {
        // Base case: Powers of four must be positive (4^0 = 1, 4^1 = 4...).
        // 0 and negative numbers are immediately false.
        if (n <= 0) return false;
        
        // Loop: Keep dividing n by 4 as long as it is perfectly divisible.
        // This effectively removes all factors of 4 from the number.
        while (n % 4 == 0) {
            n /= 4;
        }
        
        // Final check: If the original number was a power of four, 
        // we should be left with exactly 1 (e.g., 16 -> 4 -> 1).
        // If we remain with anything else (like 2 or 3), it was not a power of four.
        return n == 1;
    }
}