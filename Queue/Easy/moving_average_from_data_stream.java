// LeetCode Problem 346: Moving Average from Data Stream

import java.util.Deque;
import java.util.ArrayDeque;

/**
 * Implements a class to calculate the moving average of a stream of integers
 * over a fixed-size window.
 *
 * This implementation uses a queue to efficiently manage the "sliding window" of numbers.
 * A running `sum` and `count` are maintained to allow for an O(1) time complexity
 * calculation for each new number added to the stream.
 */
class MovingAverage {

    // A queue to store the numbers currently within the sliding window.
    private Deque<Integer> queue;
    // The current number of elements in the queue/window.
    private int count;
    // The running sum of all numbers currently in the queue.
    private double sum;
    // The maximum capacity of the window.
    private int size;

    /**
     * Initializes the MovingAverage object with a specific window size.
     * @param size The maximum number of elements to include in the average calculation.
     */
    public MovingAverage(int size) {
        this.queue = new ArrayDeque<>();
        this.count = 0;
        this.sum = 0;
        this.size = size;
    }
    
    /**
     * Adds a new value to the stream and calculates the new moving average.
     * @param val The next integer from the data stream.
     * @return The moving average of the last `size` elements.
     */
    public double next(int val) {
        // Add the new value to the running sum.
        sum += val;
        // Add the new value to the end of our window (queue).
        queue.offer(val);
        // Increment the count of elements in the window.
        count++;
        
        // If the window has grown larger than its maximum allowed size...
        if (count > size) {
            // ...remove the oldest element from the front of the queue.
            int prev = queue.poll();
            // ...subtract its value from the running sum.
            sum -= prev;
            // ...and decrement the count to reflect the removal.
            count--;
        }
        
        // The moving average is the current sum divided by the number of elements in the window.
        return sum / count;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */