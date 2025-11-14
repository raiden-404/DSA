// Problem: 1160. Find Words That Can Be Formed by Characters
// Name: [Your Name Here]
// No: [Your ID/Number Here]

import java.util.Arrays; // Import Arrays class to use Arrays.copyOf()

class Solution {
    /**
     * Calculates the sum of lengths of all words that can be formed using the
     * characters in 'chars'.
     *
     * @param words An array of strings to check.
     * @param chars A string of available characters.
     * @return The total length of all "good" words.
     */
    public int countCharacters(String[] words, String chars) {
        
        // 1. Create a Frequency Map for 'chars'
        // We create an integer array 'c' of size 26 to store the count
        // of each character (a-z) available in the 'chars' string.
        int[] c = new int[26];
        int res = 0; // This will store the sum of lengths of "good" words.

        // Loop through the 'chars' string to populate the frequency map.
        // c[0] will be the count of 'a', c[1] the count of 'b', etc.
        // (ch - 'a') gives the correct 0-indexed position for the character.
        for (char ch : chars.toCharArray()) {
            c[ch - 'a']++;
        }

        // 2. Check Each Word
        // Now, iterate through every word in the 'words' array.
        for (String s : words) {
            
            // For each word, we call the 'contains' helper function.
            // CRITICAL STEP: We pass a COPY of the frequency map 'c'.
            // We use Arrays.copyOf(c, 26) to create a new, fresh map
            // for each word. This is so the 'contains' function can
            // "spend" (decrement) the counts without affecting the
            // original map needed for the next word.
            if (contains(s, Arrays.copyOf(c, 26))) {
                // If contains() returns true, the word is "good".
                // Add its length to our result.
                res += s.length();
            }
        }
        
        // Return the total length.
        return res;
    }

    /**
     * Helper function to check if a single word can be formed from the
     * available characters.
     *
     * @param word  The word to check.
     * @param chars A copy of the character frequency map.
     * @return True if the word can be formed, false otherwise.
     */
    public boolean contains(String word, int[] chars) {
        // Iterate through each character of the word.
        for (char c : word.toCharArray()) {
            
            // Check if we have any of this character left in our map.
            // (c - 'a') finds the index for this character.
            if (chars[c - 'a'] <= 0) {
                // If the count is 0 or less, we've run out.
                // This word cannot be formed.
                return false;
            }
            
            // If we *do* have the character, "use" one by decrementing
            // its count in the map.
            chars[c - 'a']--;
        }

        // If we get through the entire word without returning false,
        // it means we had enough of every character. The word is "good".
        return true;
    }
}