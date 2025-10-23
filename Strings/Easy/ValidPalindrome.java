package Strings.Easy;

/**
 * LeetCode Problem: 125. Valid Palindrome
 * * Problem Description:
 * A phrase is a palindrome if, after converting all uppercase letters into 
 * lowercase letters and removing all non-alphanumeric characters, it reads 
 * the same forward and backward. Alphanumeric characters include letters and numbers.
 * Given a string s, return true if it is a palindrome, or false otherwise.
 *
 * Example: "A man, a plan, a canal: Panama" is a palindrome.
 * "race a car" is not a palindrome.
 */
class Solution {
    public boolean isPalindrome(String s) {
        // --- Step 1: Initialize Pointers ---
        // We use a two-pointer approach to check the string from both ends simultaneously.
        // 'left' starts at the beginning of the string.
        int left = 0;
        // 'right' starts at the very end of the string.
        int right = s.length() - 1;

        // --- Step 2: Loop Until Pointers Meet or Cross ---
        // The loop continues as long as the left pointer is to the left of the right pointer.
        // Once they meet or cross, we have checked the entire string.
        while(left < right) {
            // Get the characters at the current left and right positions.
            char c1 = s.charAt(left);
            char c2 = s.charAt(right);

            // --- Step 3: Filter for Alphanumeric Characters (Left Pointer) ---
            // This condition checks if the character at the 'left' pointer is a valid letter or number.
            // It's a three-part check: is it a lowercase letter? OR is it an uppercase letter? OR is it a digit?
            if((c1 >= 'a' && c1 <= 'z') || (c1 >= 'A' && c1 <= 'Z') || (c1 >= '0' && c1 <= '9')) {
                
                // --- Step 4: Filter for Alphanumeric Characters (Right Pointer) ---
                // If the left character was valid, we now check if the right character is also valid.
                if((c2 >= 'a' && c2 <= 'z') || (c2 >= 'A' && c2 <= 'Z') || (c2 >= '0' && c2 <= '9')) {
                    
                    // --- Step 5: Compare the Valid Characters ---
                    // If BOTH characters are alphanumeric, we can finally compare them.
                    // We must convert both to lowercase to ensure the comparison is case-insensitive.
                    // For example, 'A' should be considered equal to 'a'.
                    if(Character.toLowerCase(c1) != Character.toLowerCase(c2)) {
                        // If the characters do not match, it's not a palindrome. We can stop immediately.
                        return false;
                    }
                    
                    // --- Step 7: Move Both Pointers Inward ---
                    // If the characters matched, we are done with this pair.
                    // Move the left pointer one step to the right.
                    left++; 
                    // Move the right pointer one step to the left.
                    right--;

                } else {
                    // This 'else' block runs if the left char (c1) was alphanumeric, but the right char (c2) was NOT.
                    // We need to skip the invalid character at the right.
                    right--;
                    // The 'continue' statement jumps to the next iteration of the while loop,
                    // effectively re-evaluating with the new 'right' position but the same 'left' position.
                    continue;
                }
            } else {
                // This 'else' block runs if the left char (c1) was NOT alphanumeric.
                // We need to skip this invalid character.
                left++;
                // The 'continue' statement jumps to the next iteration, re-evaluating
                // with the new 'left' position but the same 'right' position.
                continue;
            }
            
            /* * A note on the final pointer movement:
             * This block of code can be simplified. The `left++` and `right--` at the end
             * are now redundant because they are handled inside the successful comparison block.
             * The `continue` statements also make this part of the code unreachable.
             * It's left here to match the original code provided.
             */
            // left++; right--; 
        }

        // --- Step 8: Return True ---
        // If the while loop finishes without finding any non-matching characters,
        // it means every valid character pair was identical. Therefore, the string is a palindrome.
        return true;
    }
}