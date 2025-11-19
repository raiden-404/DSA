/**
 * LeetCode Problem 168: Excel Sheet Column Title
 * * Approach: Stack-based Base-26 Conversion
 * Since we process the number from right-to-left (Least Significant Digit),
 * but need to build the string left-to-right, a Stack is used to reverse the order.
 */
class Solution {
    public String convertToTitle(int columnNumber) {
        // Use a Stack (Deque) to store the digits. 
        // We calculate the last letter first, so we push it onto the stack to pop it later in correct order.
        Deque<Integer> stack = new ArrayDeque<>();

        // Continue reducing the number as long as it is larger than a single letter (26)
        while(columnNumber > 26) {
            int val = columnNumber % 26;

            // Special Case: If remainder is 0, it means the letter is 'Z' (26).
            // Because this is a 1-indexed system (1=A, 26=Z), not 0-indexed,
            // a remainder of 0 requires special handling.
            if(val == 0){
                stack.push(26);         // Push 26 to represent 'Z'
                columnNumber /= 26;     // Move to the next digit
                columnNumber--;         // Crucial: "Borrow" 1 from the next digit because we consumed a full 26.
            }
            else {
                // Standard Case: Just push the remainder
                stack.push(val);
                columnNumber /= 26;
            }
        }

        // Push the final remaining value (the most significant digit)
        stack.push(columnNumber);

        // Build the result string by popping from the stack
        StringBuilder res = new StringBuilder();
        while(!stack.isEmpty()) {
            int val = stack.pop();
            
            // Safety check (though logically unlikely to hit 0 with the logic above)
            if(val == 0) continue; 
            
            // Convert the integer (1-26) to character ('A'-'Z')
            // 'A' is ASCII 65. If val is 1, 'A' + 0 = 'A'.
            res.append((char) ('A' + (val - 1)));
        }
        
        return res.toString();
    }
}