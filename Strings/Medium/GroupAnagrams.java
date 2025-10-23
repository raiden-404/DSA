package Strings.Medium;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Arrays;

/**
 * LeetCode Problem: 49. Group Anagrams
 *
 * Problem Description:
 * Given an array of strings `strs`, group the anagrams together. You can return the answer in any order.
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Example:
 * Input: strs = ["eat","tea","tan","ate","nat","bat"]
 * Output: [["bat"],["nat","tan"],["ate","eat","tea"]]
 */
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        /*
         * --- HINT: Alternative Sorting Approach ---
         * Another popular way to solve this is by sorting each string. The core idea is that
         * all anagrams become identical when their characters are sorted alphabetically.
         * For example, "eat", "tea", and "ate" all become "aet" after sorting.
         * This sorted string ("aet") can be used as the key in the HashMap.
         *
         * The time complexity for the sorting approach is O(N * K log K), where N is the
         * number of strings and K is the maximum length of a string. It's often simpler to write.
         *
         * Snippet for the sorting key:
         * char[] charArr = str.toCharArray();
         * Arrays.sort(charArr);
         * String key = new String(charArr);
         *
         * The code below implements the more optimal frequency count approach (O(N * K)).
         */

        // --- Step 1: Initialize the Data Structure ---
        // We use a HashMap to store the groups.
        // The KEY will be a unique signature representing an anagram group (e.g., "a1e1t1" for "eat").
        // The VALUE will be a list of all strings that match that signature.
        Map<String, List<String>> map = new HashMap<>();

        // --- Step 2: Iterate Through Each String in the Input Array ---
        for(String s : strs) {
            // --- Step 3: Create a Frequency Map for the Current String ---
            // An array of size 26 can act as a frequency map for the lowercase English alphabet.
            // Each index from 0 to 25 corresponds to a letter 'a' through 'z'.
            int[] arr = new int[26];
            for(char c : s.toCharArray()) {
                // Increment the count for the character. 'c' - 'a' gives the correct index.
                // For example, 'a' - 'a' = 0, 'b' - 'a' = 1, etc.
                arr[c - 'a']++;
            }

            // --- Step 4: Build a Unique Key from the Frequency Map ---
            // This key uniquely identifies the anagram group. For example, both "eat" and "tea"
            // will produce the same key "a1e1t1". We use a StringBuilder for efficient string construction.
            StringBuilder keyBuilder = new StringBuilder();
            for(int i = 0; i < 26; i++) {
                // We only append characters that actually appear in the string.
                if(arr[i] > 0) {
                    // Append the character itself. (char)('a' + i) converts index back to a character.
                    keyBuilder.append((char)('a' + i));
                    // Append its frequency.
                    keyBuilder.append(arr[i]);
                }
            }
            String k = keyBuilder.toString(); // Convert the StringBuilder to an immutable String key.

            // --- Step 5: Add the String to the Correct Group in the Map ---
            // Check if a group for this key already exists.
            if(map.containsKey(k)) {
                // If it exists, get the list and add the current string `s` to it.
                map.get(k).add(s);
            } else {
                // If this is the first time we've seen this key, we need to create a new group.
                // `new ArrayList<>(Arrays.asList(s))` is a concise one-liner to create a new,
                // MUTABLE ArrayList that is initialized with the current string `s`.
                map.put(k, new ArrayList<>(Arrays.asList(s)));
            }
        }

        // --- Step 6: Return the Grouped Anagrams ---
        // The map's values are all the lists of grouped anagrams.
        // We collect them and return them as a new ArrayList.
        return new ArrayList<>(map.values());
    }
}