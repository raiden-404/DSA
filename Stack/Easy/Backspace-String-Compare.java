// 844. Backspace String Compare

class Solution {
    /**
     * This function compares two strings after processing backspace characters ('#').
     * It uses the "Two Pointer" (or "Two Finger") approach, which is a clever,
     * space-efficient method ($O(1)$ space).
     *
     * The strategy is to iterate through both strings *backwards*, from the end to
     * the beginning. This allows us to handle backspaces efficiently. When we see
     * a '#', we know we need to "skip" a certain number of upcoming characters
     * (as we move left).
     */
    public boolean backspaceCompare(String s, String t) {
        // Initialize pointers to the last character of each string.
        int i = s.length() - 1;
        int j = t.length() - 1;

        // Loop as long as there are characters to process in EITHER string.
        // This is crucial for handling cases where one string is effectively
        // shorter than the other (e.g., "a#c" vs "c").
        while(i >= 0 || j >= 0) {
            
            // --- Find the next valid, non-deleted character in String s ---
            int backSpace = 0;
            // This inner loop "processes" string s.
            while(i >= 0) {
                if(s.charAt(i) == '#') {
                    // Found a backspace. Increment the count of characters to skip.
                    backSpace++;
                    i--; // Move to the next character.
                } else if(backSpace > 0) {
                    // Found a normal character, but our backspace count is > 0.
                    // This character gets "deleted".
                    backSpace--; // Decrement the skip count.
                    i--; // Move to the next character.
                } else {
                    // Found a normal character and our backspace count is 0.
                    // This is a valid character. Stop this inner loop to compare.
                    break;
                }
            }

            // Reset the backspace counter for string t.
            backSpace = 0;

            // --- Find the next valid, non-deleted character in String t ---
            // This inner loop does the exact same logic as above, but for string t.
            while(j >= 0) {
                if(t.charAt(j) == '#') {
                    backSpace++;
                    j--;
                } else if(backSpace > 0) {
                    backSpace--;
                    j--;
                } else {
                    // Found a valid character for string t. Stop to compare.
                    break;
                }
            }

            // --- Compare the two valid characters ---

            // At this point, i and j are pointing to the first non-deleted
            // character from the end of each string (or are < 0 if the string is exhausted).

            // Case 1: Both strings have a valid character, but they don't match.
            // This is a clear mismatch.
            if(i >= 0 && j >= 0 && s.charAt(i) != t.charAt(j)) {
                return false;
            }

            // Case 2: One string has a valid character, but the other is exhausted.
            // (e.g., s = "a", t = "b#a").
            // If one pointer is valid (>= 0) and the other is not (< 0), the
            // effective lengths are different, so they can't be equal.
            // The (i >= 0) != (j >= 0) check is a concise way to check this.
            if((i >= 0) != (j >= 0)) {
                return false;
            }

            // If we are here, it means:
            // 1. Both i and j are valid (>= 0) AND their characters match.
            // 2. Both i and j are exhausted (< 0) - this will exit the main loop.
            
            // Move both pointers left to find the next pair of valid characters.
            i--; j--;
        }

        // If the loop completes without ever returning false,
        // it means all valid characters matched.
        return true;
    }

    /*
     * --- Alternative Stack-Based Approach (O(N+M) Space) ---
     *
     * A more straightforward, but less space-efficient, solution is to
     * use a Stack (or StringBuilder as a stack).
     *
     * 1. Create a helper function `build(String str)`:
     * - Iterate through `str` from left to right.
     * - If the character is not '#', push it onto a stack.
     * - If the character is '#' and the stack isn't empty, pop from the stack.
     * - After the loop, convert the stack to a string and return it.
     *
     * 2. In the main `backspaceCompare` function:
     * - `String finalS = build(s);`
     * - `String finalT = build(t);`
     * - `return finalS.equals(finalT);`
     *
     * This approach is very simple and easy to understand, but it requires
     * $O(N + M)$ extra space for the stacks/builders. The two-pointer
     * approach is more clever because it achieves the same $O(N + M)$
     * time complexity but uses only $O(1)$ constant space.
     */
}
