// LeetCode Problem 933: Number of Recent Calls

/**
 * Implements a counter for recent requests within a specific time frame.
 *
 * This class uses a queue data structure to store the timestamps of incoming `ping` requests.
 * For each new ping at time `t`, it efficiently discards any timestamps from the front of the
 * queue that are older than `t - 3000`. The size of the remaining queue then represents the
 * number of recent calls in the range [t - 3000, t].
 *
 * The provided solution uses an explicit `count` variable, though returning `queue.size()`
 * at the end of the `ping` method would achieve the same result more directly.
 */

 import java.util.Deque;
 import java.util.ArrayDeque;

class RecentCounter {
    
    // A queue to store the timestamps of the ping requests in chronological order.
    Deque<Integer> queue;
    // An integer to keep track of the number of pings within the valid time window.
    int count;

    /**
     * Constructor to initialize the RecentCounter object.
     * It creates a new empty queue and sets the initial count to 0.
     */
    public RecentCounter() {
        queue = new ArrayDeque<>(); 
        count = 0;
    }
    
    /**
     * Registers a new ping at time `t` and returns the number of pings
     * that have occurred in the last 3000 milliseconds (inclusive).
     * @param t The current timestamp of the ping, guaranteed to be strictly increasing.
     * @return The number of pings in the time window [t - 3000, t].
     */
    public int ping(int t) {
        // Add the new timestamp to the end of the queue.
        queue.offer(t);
        // Increment the count for this new ping.
        count++;

        // Calculate the lower bound of the valid time window.
        int range = t - 3000;
        
        // Remove all timestamps from the front of the queue that are outside
        // the [t - 3000, t] window.
        while (queue.peek() < range) {
            // Remove the oldest timestamp.
            queue.poll();
            // Decrement the count as this ping is no longer "recent".
            count--;
        }
        
        // The remaining count is the number of pings within the desired time frame.
        return count;
    }
}

/**
 * Your RecentCounter object will be instantiated and called as such:
 * RecentCounter obj = new RecentCounter();
 * int param_1 = obj.ping(t);
 */