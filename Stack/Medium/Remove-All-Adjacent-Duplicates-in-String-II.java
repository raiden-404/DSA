// LeetCode Problem 1209: Remove All Adjacent Duplicates in String II
// Solution by: (Your Name)
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    /**
     * Removes k consecutive duplicates from a string.
     * This solution uses two stacks: one for characters and one for counts of
     * consecutive character groups.
     * @param s The input string.
     * @param k The number of adjacent duplicates to remove.
     * @return The string after all k-length adjacent duplicates have been removed.
     */
    public String removeDuplicates(String s, int k) {
        // 'stack' stores the characters of the processed string.
        Deque<Character> stack = new ArrayDeque<>();
        // 'count' stores the length of consecutive character groups that appeared
        // *before* the current group.
        Deque<Integer> count = new ArrayDeque<>();

        // 'currCount' tracks the count of the current consecutive character at the top of the stack.
        int currCount = 0;

        // Iterate through each character of the input string.
        for(char c : s.toCharArray()) {
            
            // --- Step 1: Process the current character ---

            // If the stack is empty, this is the first character we've seen.
            if(stack.isEmpty()) {
                stack.push(c);
                currCount = 1;
            } else {
                // Peek at the top character without removing it to compare.
                char top = stack.peek();
                // Push the current character onto the stack.
                stack.push(c);
                
                // If the current character is the same as the previous one...
                if(c == top) {
                    // ...increment the count of the current consecutive group.
                    currCount++;
                }
                // If the character is different, a new sequence has started.
                else {
                    // Save the count of the *previous* character group onto the 'count' stack.
                    count.push(currCount);
                    // Reset the current count to 1 for the new character.
                    currCount = 1;
                }
            }
            
            // --- Step 2: Check for and remove k duplicates ---

            // If the count of the current character group has reached k...
            if(currCount == k) {
                // ...it's time to remove them. Pop k characters from the stack.
                for(int j=1; j<=k; j++) {
                    stack.pop();
                }
                
                // After removal, we must restore 'currCount' to the count of the
                // character group that is now at the top of the stack.
                if(count.isEmpty()) {
                    // If the 'count' stack is empty, it means we've removed everything
                    // or the group before it was the very first group. So, no current group exists.
                    currCount = 0;
                } else {
                    // Otherwise, pop the last saved count from the 'count' stack.
                    // This count corresponds to the new group at the top of the 'stack'.
                    currCount = count.pop();
                }
            }
        }
        
        // --- Step 3: Build the final result string ---

        // Create a StringBuilder to efficiently construct the string.
        StringBuilder res = new StringBuilder();
        
        // Iterating through a Deque (used as a stack) goes from bottom to top.
        // This means characters are appended in the reverse order of how they should appear.
        for(char c : stack) {
            res.append(c);
        }
        
        // Because the characters were appended from bottom-to-top of the stack,
        // we must reverse the StringBuilder to get the correct final order.
        return res.reverse().toString();
    }
}