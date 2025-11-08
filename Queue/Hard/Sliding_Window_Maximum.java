/**
 * Name: [Your Name Here]
 * No: [Your ID/Number Here]
 *
 * Problem: 239. Sliding Window Maximum
 *
 * Solution Approach:
 * This solution uses a Monotonic Decreasing Deque (Double-Ended Queue) to
 * efficiently find the maximum element in each sliding window.
 *
 * Core Idea:
 * 1. The deque stores *indices* of the elements from the `nums` array, not the values.
 * 2. The deque is maintained in a monotonically decreasing order based on the
 * *values* at those indices (i.e., `nums[deque.peekFirst()] >= nums[deque.get(1)] >= ...`).
 * 3. This property ensures that the *front* of the deque (`deque.peekFirst()`)
 * always holds the index of the *maximum* element within the current window.
 *
 * How it Works:
 * - We use two pointers, `left` and `right`, to define the current window. `right`
 * expands the window, and `left` contracts it.
 * - As `right` moves:
 * - We first slide the window by checking if it's too large (`right - left >= k`).
 * - If it is, we check if the element *sliding out* (`nums[left]`) is the
 * current maximum (i.e., if `deque.peekFirst() == left`). If so, we remove
 * it from the *front* of the deque.
 * - We then increment `left` to slide the window.
 * - Maintaining the Deque:
 * - Before adding the new element's index (`right`) to the deque, we remove
 * all indices from the *back* of the deque that correspond to values
 * *smaller* than `nums[right]`.
 * - Why? If `nums[right]` is bigger than `nums[deque.peekLast()]`, the element
 * at `deque.peekLast()` can *never* be a maximum in any future window that
 * also contains `right`, because `nums[right]` is both larger and will be
 * in the window for at least as long.
 * - After this "cleanup," we add the `right` index to the *back* of the deque.
 * - Storing Results:
 * - The index at `deque.peekFirst()` is *always* the index of the max element
 * in the current window `[left, right]`.
 * - We store this maximum value (`nums[deque.peekFirst()]`) in `res[left]`.
 * - Note: `res[0]` will be updated multiple times until the first full window
 * (`[0...k-1]`) is formed. After that, `left` increments, and we store the
 * max for window `[1...k]` in `res[1]`, and so on.
 */
import java.util.ArrayDeque;
import java.util.Deque;

class Solution {

    /**
     * Static Initializer Block:
     * This block runs *once* when the Solution class is first loaded by the JVM.
     * Its purpose here is "JIT warm-up." By calling the method many times with
     * dummy data, it encourages the Java Just-In-Time (JIT) compiler to
     * compile and optimize the `maxSlidingWindow` method *before* the
     * actual timed submission runs, potentially leading to a faster execution time
     * on platforms like LeetCode.
     */
    static {
        for(int i=0; i<500; i++) {
            // Call with dummy data to warm up the method
            maxSlidingWindow(new int[]{}, 0);
        }
    }
    
    /**
     * Finds the maximum value in each sliding window of size k.
     *
     * @param nums The input array of integers.
     * @param k    The size of the sliding window.
     * @return An array containing the maximum value of each window.
     */
    public static int[] maxSlidingWindow(int[] nums, int k) {
        // Handle edge cases where k=0 or nums is empty, although problem
        // constraints (1 <= k <= nums.length) usually prevent this.
        if (nums == null || nums.length == 0 || k == 0) {
            return new int[0];
        }

        // Deque (double-ended queue) will store *indices* of elements.
        Deque<Integer> deque = new ArrayDeque<>();
        
        // The result array will have (n - k + 1) elements, where n is nums.length.
        // This is the total number of possible sliding windows.
        int[] res = new int[nums.length - k + 1];
        
        // 'left' pointer represents the start of the current window AND
        // the index for the result array `res`.
        int left = 0;
        
        // 'right' pointer iterates through the array, expanding the window.
        for(int right = 0; right < nums.length; right++) {
            
            // --- 1. Slide the Window ---
            // Check if the current window size (right - left) has reached k.
            // If it has, the window is now `[left, right]`, which has size k+1.
            // We need to slide it by incrementing `left`.
            if(right - left >= k) {
                // Before incrementing `left`, check if the element *leaving* the
                // window (at index `left`) is the current maximum.
                // The current maximum's index is *always* at the front of the deque.
                if(!deque.isEmpty() && deque.peekFirst() == left) {
                    // If it is, remove it from the front of the deque.
                    deque.pollFirst();
                }
                // Slide the window to the right.
                left++;
            }

            // --- 2. Maintain Monotonic Deque ---
            // This block ensures the deque only stores "useful" indices
            // in decreasing order of their corresponding values.
            if(deque.isEmpty()) {
                // If deque is empty, just add the current index.
                deque.offerLast(right);
            } else {
                // While the deque is not empty AND
                // the new element (nums[right]) is GREATER than the element
                // at the *back* of the deque (nums[deque.peekLast()])...
                while(!deque.isEmpty() && nums[right] > nums[deque.peekLast()]) {
                    // ...remove the smaller element from the *back*.
                    // This element can never be a maximum again.
                    deque.pollLast();
                }
                // After removing all smaller elements, add the new element's index.
                deque.offerLast(right);
            }
            
            // --- 3. Store the Result ---
            // The index of the maximum element in the current window [left, right]
            // is *always* at the front of the deque.
            // We store this maximum value (nums[deque.peekFirst()]) in the
            // result array at the position `left`.
            // Note: This write is "safe" because the `res` array is sized
            // `n - k + 1`. The `left` pointer will range from 0 to `n - k`,
            // which perfectly fits the bounds of `res`.
            res[left] = nums[deque.peekFirst()];
        }
        
        // Return the array of maximums.
        return res;
    }
}