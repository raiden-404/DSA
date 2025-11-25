// LeetCode 1015: Smallest Integer Divisible by K
//
// Problem Summary:
// We must find the length of the smallest positive integer that consists
// only of digit '1' (like 1, 11, 111, 1111, ...) which is divisible by K.
// If no such number exists, return -1.
//
// Key Insight:
// Any such number can be represented by repeatedly appending '1'.
// But these numbers grow extremely fast (millions of digits),
// so we never actually build them. Instead, we only track the remainder.
//
// Why modulo trick works:
// If num is the current remainder of the huge number,
// the next number (append '1') becomes:
//      newNum = (num * 10 + 1) % K
//
// This ensures no overflow and O(K) time.
//
// Important Rule:
// A number made only of digit '1' can NEVER be divisible by 2 or 5.
// So for any K divisible by 2 or 5 → immediately return -1.
//
// Maximum Loop Count:
// At most K iterations because there are only K possible remainders.
// If we don’t reach remainder 0 within K steps, it will never happen.

class Solution {
    public int smallestRepunitDivByK(int k) {

        // If K is divisible by 2 or 5, no repunit (1111...) can be divisible by K.
        // Reason: all repunits end with digit '1', so cannot be divisible by 2 or 5.
        if (k % 2 == 0 || k % 5 == 0) return -1;

        int num = 1;  // This holds the current remainder of the repunit
        int length = 1;  // Length of the repunit we have formed (number of '1's)

        // We loop at most k times → guaranteed by remainder theory.
        while (length <= k) {

            // If the current remainder is 0, we found the smallest repunit divisible by K.
            if (num % k == 0) return length;

            // Build the next remainder when appending '1':
            // Example:
            // 1 → 11 → 111 → ...
            num = (num * 10 + 1) % k;

            length++;
        }

        // Theoretically unreachable unless K is divisible by 2 or 5,
        // but added for completeness.
        return -1;
    }
}
