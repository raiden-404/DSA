// 225. Implement Stack using Queues

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Implements a Last-In-First-Out (LIFO) stack using two First-In-First-Out (FIFO) queues.
 *
 * This implementation uses two queues, `q1` and `q2`.
 * - `q1` acts as the main storage for the stack elements.
 * - `q2` is a temporary helper queue used only during the `pop` operation.
 *
 * We also use a variable, `peek`, to cache the top of the stack.
 * - `push(x)`: This is an O(1) operation. We simply add the new element `x` to `q1`
 * and update `peek` to be `x`, since the newest element is always the top.
 * - `pop()`: This is an O(N) operation, where N is the number of elements in the stack.
 * To get the last element (the stack's top), we must move all elements *except*
 * the last one from `q1` to `q2`. We then get the last element, and finally
 * move all elements back from `q2` to `q1`.
 * - `top()`: This is an O(1) operation, thanks to our `peek` cache.
 * - `empty()`: This is an O(1) operation.
 */
class MyStack {
    
    // q1 is the primary queue that holds all the stack elements.
    public Queue<Integer> q1 = new ArrayDeque<>();
    // q2 is a temporary helper queue used during the pop operation.
    public Queue<Integer> q2 = new ArrayDeque<>();
    // Caches the top element of the stack for an O(1) top() operation.
    public int peek;

    /**
     * Initializes the data structure.
     */
    public MyStack() {
    
    }
    
    /**
     * Pushes element x onto the top of the stack.
     * This is O(1) because we just add to the queue and update our cache.
     */
    public void push(int x) {
        // Add the new element to the back of our main queue.
        q1.offer(x);
        // This new element is now the top of the stack, so we cache it.
        peek = x;
    }
    
    /**
     * Removes and returns the element from the top of the stack.
     * This is O(N) because we have to transfer N-1 elements twice.
     */
    public int pop() {
        // Get the current size of q1.
        int s = q1.size();
        // We must move all elements except the last one from q1 to q2.
        // We loop N-1 times (from 1 up to, but not including, s).
        for(int i=1; i<s; i++) {
            // poll() from q1 (front) and offer() to q2 (back).
            int temp = q1.poll();
            q2.offer(temp);
            // As we move elements, the *last* element we move
            // (the one at index s-2) will become the new top of the stack.
            peek = temp;
        }

        // The last element remaining in q1 is the one we want to pop.
        int res = q1.poll();
        
        // Now we must move all elements from q2 back to q1 to restore state.
        s = q2.size();
        for(int i=1; i<=s; i++) {
            q1.offer(q2.poll());
        }
        
        // Return the popped element.
        return res;
    }
    
    /**
     * Returns the element on the top of the stack without removing it.
     * This is O(1) because we stored the top element in a variable.
     */
    public int top() {
        return peek;
    }
    
    /**
     * Returns true if the stack is empty, false otherwise.
     * This is O(1).
     */
    public boolean empty() {
        // The stack is empty if and only if our main queue `q1` is empty.
        return q1.isEmpty();
    }
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */

