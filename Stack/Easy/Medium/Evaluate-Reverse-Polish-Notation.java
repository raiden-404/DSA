import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode Problem: 150. Evaluate Reverse Polish Notation
 */
class Solution {
    /**
     * Evaluates the value of an arithmetic expression in Reverse Polish Notation.
     *
     * @param tokens An array of strings representing the RPN expression.
     * Valid operators are "+", "-", "*", and "/".
     * Each operand may be an integer or another expression.
     * @return The integer result of the expression.
     */
    public int evalRPN(String[] tokens) {
        // Use a Deque as a stack to store operands.
        // ArrayDeque is generally more efficient than the legacy Stack class.
        Deque<Integer> stack = new ArrayDeque<>();

        // Iterate through each token in the expression.
        for (int i = 0; i < tokens.length; i++) {
            String s = tokens[i];
            
            // Declare variables to hold the two operands for an operation.
            int a;
            int b;

            // Use a switch statement to check if the current token is an operator.
            switch (s) {
                case "+":
                    // If it's an operator, pop the last two operands from the stack.
                    b = stack.pop(); // The second operand is popped first.
                    a = stack.pop(); // The first operand is popped second.
                    // Perform the operation and push the result back onto the stack.
                    stack.push(a + b);
                    break;
                case "-":
                    b = stack.pop();
                    a = stack.pop();
                    // The order is important: a - b.
                    stack.push(a - b);
                    break;
                case "*":
                    b = stack.pop();
                    a = stack.pop();
                    stack.push(a * b);
                    break;
                case "/":
                    b = stack.pop();
                    a = stack.pop();
                    // The division truncates toward zero as per the problem's requirements.
                    stack.push(a / b);
                    break;
                default:
                    // If the token is not an operator, it must be a number.
                    // Parse the string to an integer and push it onto the stack.
                    stack.push(Integer.parseInt(s)); // Using s is slightly cleaner than tokens[i]
            }
        }

        // After iterating through all tokens, the final result is the only element
        // left in the stack. Pop and return it.
        return stack.pop();
    }
}