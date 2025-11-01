import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode Problem: 155. Min Stack
 * * Implements a stack that supports push, pop, top, and retrieving the minimum element 
 * in constant time O(1).
 */
class MinStack {

    // Main stack to store all elements.
    private Deque<Integer> stack;
    
    // Auxiliary stack to keep track of the minimum values.
    private Deque<Integer> minStack;

    /**
     * Initializes the data structure.
     */
    public MinStack() {
        stack = new ArrayDeque<>();
        minStack = new ArrayDeque<>();
    }
    
    /**
     * Pushes the element val onto the stack.
     * @param val The integer value to be pushed.
     */
    public void push(int val) {
        // Always push the new value onto the main stack.
        stack.push(val);
        
        // Push the value onto the minStack only if it's smaller than or equal to 
        // the current minimum. The "<=" is crucial for handling duplicate minimums.
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    /**
     * Removes the element on the top of the stack.
     */
    public void pop() {
        // First, check if the element being popped is the current minimum.
        // Use .equals() for safe comparison of Integer objects.
        if (stack.peek().equals(minStack.peek())) {
            // If it is, pop from the minStack to update the current minimum.
            minStack.pop();
        }
        // Always pop the element from the main stack.
        stack.pop();
    }
    
    /**
     * Gets the top element of the stack.
     * @return The integer at the top of the stack.
     */
    public int top() {
        return stack.peek();
    }
    
    /**
     * Retrieves the minimum element in the stack.
     * @return The minimum integer in the stack.
     */
    public int getMin() {
        // The current minimum is always at the top of the minStack.
        return minStack.peek();
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */