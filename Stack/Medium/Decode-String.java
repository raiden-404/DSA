import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode Problem: 394. Decode String
 *
 * This solution uses an iterative approach with two stacks to decode a
 * string with nested encoded parts. This is optimal as it avoids recursion
 * and potential stack overflow issues.
 */
class Solution {
    public String decodeString(String s) {
        // Stack to store the repetition counts (e.g., the '3' in "3[a]").
        Deque<Integer> count = new ArrayDeque<>();
        // Stack to store the string built so far before a new '[' is encountered.
        Deque<StringBuilder> word = new ArrayDeque<>();

        // Holds the string being built at the current level of nesting.
        StringBuilder currStr = new StringBuilder();
        // Holds the number being parsed (can be multi-digit like "100").
        int currNum = 0;

        // Iterate through every character in the input string.
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // Build the complete number. e.g., if we see '1' then '0', currNum becomes 10.
                currNum = currNum * 10 + (c - '0');
            } else if (c == '[') {
                // We are entering a new, nested part. Save the current state.
                // 1. Push the string we've built so far onto the word stack.
                word.push(currStr);
                // 2. Push the number we just parsed onto the count stack.
                count.push(currNum);
                
                // 3. Reset for the new, inner level.
                currNum = 0;
                // Create a completely new StringBuilder for the inner content.
                currStr = new StringBuilder();
            } else if (c == ']') {
                // We've reached the end of a nested part. Time to decode.
                // 1. Pop the string we were building *before* this nested part began.
                StringBuilder prevString = word.pop();
                // 2. Pop the repetition count associated with the part we just finished.
                int n = count.pop();
                
                // 3. Append the inner string (`currStr`) to the outer string (`prevString`) 'n' times.
                for (int i = 0; i < n; i++) { // Changed loop from 1<=n to 0<n for clarity
                    prevString.append(currStr);
                }
                // 4. Our new `currStr` is now this combined result.
                currStr = prevString;
            } else {
                // It's a letter, simply append it to the string we are currently building.
                currStr.append(c);
            }
        }
        
        // After the loop, currStr holds the final, fully decoded string.
        return currStr.toString();
    }
}