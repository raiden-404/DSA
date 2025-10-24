package Strings.Medium;

/**
 * LeetCode Problem: 151. Reverse Words in a String
 *
 * Problem Description:
 * Given an input string `s`, reverse the order of the words.
 * A word is defined as a sequence of non-space characters. The words in `s` will be
 * separated by at least one space. The returned string should have a single space
 * separating the words and should not have any leading or trailing spaces.
 *
 * Example:
 * Input: s = "  the sky is blue  "
 * Output: "blue is sky the"
 */
class Solution {
    public String reverseWords(String s) {
        // --- Step 1: Split the String into Words ---
        // The `split("\\s+")` method splits the string by one or more whitespace characters (`\s+`).
        // This is a robust way to handle multiple spaces between words.
        // Note: If `s` has leading spaces, the first element of `words` will be an empty string.
        // e.g., "  hello world".split("\\s+") -> ["", "hello", "world"]
        String[] words = s.split("\\s+");

        // --- Step 2: Use a StringBuilder for Efficient String Building ---
        // A StringBuilder is used because repeatedly concatenating strings with `+` in a loop
        // is inefficient (creates many temporary string objects).
        StringBuilder res = new StringBuilder();

        // --- Step 3: Iterate Through the Words in Reverse Order ---
        // The loop starts from the last word in the array and moves backward.
        for(int i = words.length - 1; i >= 0; i--) {
            // Append the current word to our result.
            res.append(words[i]);
            // Append a single space after each word. This will add one extra space at the very end.
            res.append(" ");
        }

        // --- Step 4: Convert to String and Clean Up ---
        // `res.toString()` converts the StringBuilder back to a regular String.
        // `trim()` is then used to remove any leading or trailing whitespace. In this case,
        // it conveniently removes the single trailing space added in the last loop iteration
        // and also handles cases where the input string might have caused empty strings in the array.
        return res.toString().trim();
    }
}