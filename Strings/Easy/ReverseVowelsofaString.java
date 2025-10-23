package Strings.Easy;

/**
 * LeetCode Problem: 345. Reverse Vowels of a String
 *
 * Problem Description:
 * Given a string s, reverse only all the vowels in the string and return it.
 * The vowels are 'a', 'e', 'i', 'o', 'u', and they can appear in both lower and upper cases.
 *
 * Example 1:
 * Input: s = "hello"
 * Output: "holle"
 *
 * Example 2:
 * Input: s = "leetcode"
 * Output: "leotcede"
 */
class Solution {
    public String reverseVowels(String s) {
        // --- Step 1: Convert String to a Mutable Representation ---
        // In Java, Strings are immutable, meaning they cannot be changed after creation.
        // To modify characters (like swapping), we must first convert the string
        // into a mutable format. A character array (`char[]`) is a perfect and efficient choice for this.
        // This conversion is an O(N) operation, where N is the length of the string.
        char[] arr = s.toCharArray();

        // --- Step 2: Initialize Two Pointers ---
        // We will use the two-pointer technique to scan the array from both ends simultaneously.
        // 'left' starts at the beginning of the array (index 0).
        int left = 0;
        // 'right' starts at the very end of the array.
        int right = arr.length - 1;

        // --- Step 3: Loop Until Pointers Meet or Cross ---
        // The main loop continues as long as the left pointer is to the left of the right pointer.
        // If left becomes equal to or greater than right, it means we have scanned the entire array.
        while (left < right) {
            // Temporarily store the characters at the current pointer positions.
            // These variables will be updated if the inner loops move the pointers.
            char c1 = arr[left];
            char c2 = arr[right];

            // --- Step 4: Find the Next Vowel from the Left ---
            // This inner loop's job is to advance the 'left' pointer until it lands on a vowel.
            // It effectively skips over all consonants and non-vowel characters from the beginning.
            // The `left < right` check is crucial to ensure the pointers don't cross each other during this search.
            while (left < right && !(c1 == 'a' || c1 == 'e' || c1 == 'i' || c1 == 'o' || c1 == 'u' || c1 == 'A'
                    || c1 == 'E' || c1 == 'I' || c1 == 'O' || c1 == 'U')) {
                // If the character at 'left' is not a vowel, move the pointer one step to the right.
                left++;
                // IMPORTANT: We must update `c1` to reflect the character at the new 'left' position
                // for the next iteration of this inner loop.
                c1 = arr[left];
            }

            // --- Step 5: Find the Next Vowel from the Right ---
            // Symmetrically, this inner loop moves the 'right' pointer backwards until it lands on a vowel,
            // skipping all consonants from the end.
            while (left < right && !(c2 == 'a' || c2 == 'e' || c2 == 'i' || c2 == 'o' || c2 == 'u' || c2 == 'A'
                    || c2 == 'E' || c2 == 'I' || c2 == 'O' || c2 == 'U')) {
                // If the character at 'right' is not a vowel, move the pointer one step to the left.
                right--;
                // IMPORTANT: Update `c2` to the character at the new 'right' position.
                c2 = arr[right];
            }

            // --- Step 6: Swap the Vowels (if pointers haven't crossed) ---
            // At this point, `left` is pointing to a vowel and `right` is pointing to a vowel.
            // However, it's possible the pointers crossed paths during the search loops above.
            // This check ensures we only swap if `left` is still to the left of `right`.
            if (left < right) {
                // Perform the swap directly in the character array.
                // Place the vowel from the right side into the left side's position.
                arr[left] = c2;
                // Place the vowel from the left side into the right side's position.
                arr[right] = c1;

                // --- Step 7: Move Pointers Inward After Swap ---
                // Now that we've successfully swapped a pair of vowels, we are done with these two positions.
                // We move both pointers inward to continue the search for the next pair.
                left++;
                right--;
            }
        }

        // --- Step 8: Convert the Character Array Back to a String ---
        // The loop is finished, and all vowels in the `arr` have been reversed.
        // We now construct a new string from our modified character array.
        // This is also an O(N) operation.
        return new String(arr);
    }
}