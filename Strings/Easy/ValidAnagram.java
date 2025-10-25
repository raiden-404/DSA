package Strings.Easy;

/**
 * LeetCode Problem: 242. Valid Anagram
 *
 * Problem Description:
 * Given two strings `s` and `t`, return `true` if `t` is an anagram of `s`, and `false` otherwise.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example:
 * Input: s = "anagram", t = "nagaram"
 * Output: true
 */
class Solution {
    public boolean isAnagram(String s, String t) {
        // --- Step 1: Initial Length Check ---
        // Anagrams must contain the exact same letters, so they must have the same length.
        // This is a quick and efficient way to fail early if they don't match.
        if (s.length() != t.length()) {
            return false;
        }

        // --- Step 2: Create a Frequency Map ---
        // We use an integer array of size 26 to act as a frequency map for the English alphabet ('a' through 'z').
        // Each index (0-25) corresponds to a letter.
        int[] arr = new int[26];
        int len = s.length();

        // --- Step 3: Populate the Frequency Map in a Single Pass ---
        // This is the core of the algorithm. We iterate through both strings simultaneously.
        // For each character in string `s`, we INCREMENT the count for that letter.
        // For each character in string `t`, we DECREMENT the count.
        for (int i = 0; i < len; i++) {
            // `s.charAt(i) - 'a'` calculates the correct index (0-25) for the character.
            arr[s.charAt(i) - 'a']++;
            arr[t.charAt(i) - 'a']--;
        }

        // --- Step 4: Validate the Frequency Map ---
        // If `s` and `t` are anagrams, every increment for a character in `s` will have been
        // canceled out by a corresponding decrement for the same character in `t`.
        // Therefore, every count in our frequency array should be exactly zero.
        for (int n : arr) {
            // If we find any non-zero count, it means the character frequencies did not match.
            if (n != 0) {
                return false;
            }
        }

        // --- Step 5: Return True ---
        // If the loop finishes without finding any non-zero counts, the strings are anagrams.
        return true;
    }
}