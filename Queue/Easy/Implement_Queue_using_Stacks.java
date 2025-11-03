// LeetCode Problem 232: Implement Queue using Stacks

/**
 * Implements a First-In-First-Out (FIFO) queue using two Last-In-First-Out (LIFO) stacks.
 *
 * The core idea is to use two stacks:
 * 1.  `stack1`: Acts as the "inbox". All new elements are pushed here.
 * 2.  `stack2`: Acts as the "outbox". Elements are popped/peeked from here.
 *
 * To maintain the FIFO order, elements are moved from `stack1` to `stack2` only when `stack2`
 * is empty. This transfer process reverses the order of elements, placing the oldest element
 * at the top of `stack2`, ready to be dequeued. This approach gives an amortized O(1) time
 * complexity for `pop` and `peek` operations.
 */

 import java.util.ArrayDeque;
 import java.util.Deque;

class MyQueue {

    // `stack1` is used to store newly pushed elements.
    Deque<Integer> stack1;
    // `stack2` is used to store elements in reversed order for popping and peeking.
    Deque<Integer> stack2;

    /**
     * Initializes the data structure.
     */
    public MyQueue() {
        stack1 = new ArrayDeque<>();
        stack2 = new ArrayDeque<>();
    }
    
    /**
     * Pushes element x to the back of the queue.
     * This operation is always O(1) because it simply adds an element to `stack1`.
     * @param x The integer to be added to the queue.
     */
    public void push(int x) {
        stack1.push(x);
    }
    
    /**
     * Removes the element from the front of the queue and returns it.
     * This operation is amortized O(1).
     * @return The element at the front of the queue.
     */
    public int pop() {
        // The peek() method ensures the front element is at the top of stack2.
        // We call it to move elements from stack1 if stack2 is empty.
        peek();
        // Pop the front element from stack2.
        return stack2.pop();
    }
    
    /**
     * Gets the front element of the queue without removing it.
     * This operation is amortized O(1).
     * @return The element at the front of the queue.
     */
    public int peek() {
        // If the "outbox" stack (stack2) is empty, transfer all elements
        // from the "inbox" stack (stack1) to reverse their order.
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                // Pop from stack1 and push to stack2.
                int n = stack1.pop();
                stack2.push(n);
            }
        }
        // The top of stack2 now correctly represents the front of the queue.
        return stack2.peek();
    }
    
    /**
     * Returns whether the queue is empty.
     * The queue is considered empty only if both stacks are empty.
     * This operation is O(1).
     * @return true if the queue is empty, false otherwise.
     */
    public boolean empty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */