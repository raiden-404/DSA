/*
 * LeetCode Problem 263: Ugly Number
 * * An "Ugly Number" is a positive integer whose prime factors are limited to 2, 3, and 5.
 * The logic is to repeatedly divide the number by these allowable factors until 
 * they are completely removed. If the final result is 1, the number was "Ugly".
 */

class Solution {
    public boolean isUgly(int n) {
        // Edge Case: 0 is not an ugly number.
        // Without this check, the while loops would not execute (0 % k == 0 is true,
        // but 0/k remains 0), resulting in an infinite loop or incorrect logic depending on implementation.
        if (n == 0) return false;

        // Factor out all 2s
        // Example: If n = 8 (2*2*2), this loop runs 3 times, reducing n to 1.
        // Example: If n = 14 (2*7), this runs once, reducing n to 7.
        while (n % 2 == 0) {
            n /= 2;
        }

        // Factor out all 3s
        // Example: If n = 9 (3*3), this runs twice.
        while (n % 3 == 0) {
            n /= 3;
        }

        // Factor out all 5s
        // Example: If n = 50 (2*5*5), the first loop handles the 2 (n=25),
        // and this loop handles the 5s, reducing n to 1.
        while (n % 5 == 0) {
            n /= 5;
        }

        // Final Check:
        // If n is reduced to 1, it means the original number had NO prime factors
        // other than 2, 3, or 5.
        // If n > 1 (e.g., 7, 11, 13), it means there is a non-allowed prime factor remaining.
        return n == 1;
    }
}
