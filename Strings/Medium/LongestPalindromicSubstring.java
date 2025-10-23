package Strings.Medium;

/**
 * LeetCode Problem: 5. Longest Palindromic Substring
 *
 * Problem Description:
 * Given a string s, return the longest palindromic substring in s.
 *
 * Example 1:
 * Input: s = "babad"
 * Output: "bab" (Note: "aba" is also a valid answer)
 *
 * Example 2:
 * Input: s = "cbbd"
 * Output: "bb"
 */
class Solution {

    /**
     * The main method to find the longest palindromic substring.
     * The overall strategy is called "Expand from Center". We iterate through every
     * character in the string and treat it as a potential center of a palindrome.
     * Since a palindrome can be of odd or even length, we need to check both cases
     * for each center.
     */
    public String longestPalindrome(String s) {
        // --- Step 1: Handle Edge Cases ---
        // If the string is null or has fewer than 1 character, there's no palindrome.
        if (s == null || s.length() < 1) {
            return "";
        }

        // --- Step 2: Initialize Pointers for the Longest Substring Found So Far ---
        // 'start' and 'end' will store the boundaries of the longest palindrome.
        // We initialize them to the first character as a default.
        int start = 0;
        int end = 0;

        // --- Step 3: Iterate Through the String to Find Centers ---
        // We loop through each index 'i' of the string. Each 'i' will serve as the
        // midpoint for a potential palindrome.
        for (int i = 0; i < s.length(); i++) {
            // --- Step 3a: Check for ODD-length palindromes ---
            // For odd-length palindromes, the center is a single character.
            // Example: For "racecar", the center is 'e' at index i.
            // We expand outwards from this single character.
            int len1 = expandFromCenter(s, i, i);

            // --- Step 3b: Check for EVEN-length palindromes ---
            // For even-length palindromes, the center is between two characters.
            // Example: For "aabbaa", the center is between the two 'b's at indices i and i+1.
            // We expand outwards from this pair of characters.
            int len2 = expandFromCenter(s, i, i + 1);

            // --- Step 3c: Find the Maximum Length ---
            // We take the longer of the two palindromes found (odd vs. even).
            int len = Math.max(len1, len2);

            // --- Step 3d: Update the Longest Palindrome Found So Far ---
            // If the palindrome we just found is longer than our previous record...
            if (len > end - start) {
                // ...we update 'start' and 'end' to store its boundaries.
                // The formula to calculate the start index from the center 'i' and length 'len' is:
                start = i - (len - 1) / 2;
                // The formula to calculate the end index is:
                end = i + len / 2;
            }
        }

        // --- Step 4: Return the Result ---
        // Once the loop is finished, 'start' and 'end' hold the indices of the
        // longest palindromic substring. We use s.substring() to extract it.
        // The `end + 1` is because substring's second parameter is exclusive.
        return s.substring(start, end + 1);
    }

    /**
     * A helper function that takes a string and a center (defined by left and right pointers)
     * and expands outwards to find the longest palindrome at that center.
     * @param s The input string.
     * @param left The left pointer of the center.
     * @param right The right pointer of the center.
     * @return The length of the palindrome found.
     */
    private int expandFromCenter(String s, int left, int right) {
        // --- Expansion Logic ---
        // We expand as long as the pointers are within the string's bounds
        // AND the characters at the pointers match.
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            // Move the left pointer one step to the left.
            left--;
            // Move the right pointer one step to the right.
            right++;
        }

        // --- Calculate and Return Length ---
        // When the loop breaks, 'left' and 'right' are one position BEYOND
        // the boundaries of the actual palindrome.
        // For example, if the palindrome is "aba" (indices 1 to 3), the loop will stop when
        // left = 0 and right = 4.
        // The length of the palindrome is `right - left - 1`.
        // Example: 4 - 0 - 1 = 3, which is the correct length of "aba".
        return right - left - 1;
    }
}