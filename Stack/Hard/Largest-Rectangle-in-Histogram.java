// LeetCode Problem 84: Largest Rectangle in Histogram
// Solution by: (Your Name)
// Algorithm: Monotonic Stack with Sentinel Step
// Time Complexity: O(n)
// Space Complexity: O(n)

import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    /**
     * Calculates the largest rectangular area possible in a given histogram.
     * @param heights An array of integers representing the heights of the histogram bars.
     * @return The area of the largest rectangle.
     */
    public int largestRectangleArea(int[] heights) {
        // The monotonic stack stores indices of bars in strictly increasing height order.
        Deque<Integer> monoStack = new ArrayDeque<>();
        int maxArea = 0;
        int n = heights.length;

        // Loop from 0 to n (inclusive). The step at i = n is a "sentinel step"
        // to ensure any remaining bars in the stack are processed.
        for (int i = 0; i <= n; i++) {
            
            // Determine the height of the current bar.
            // For the sentinel step (i == n), the height is 0. This guarantees
            // that any bars still in the stack will be popped.
            int currentHeight = (i == n) ? 0 : heights[i];

            // This is the core logic. While the stack is not empty and the bar at the
            // top of the stack is TALLER than the current bar, we have found the
            // right boundary for the bar on top of the stack.
            while (!monoStack.isEmpty() && heights[monoStack.peek()] > currentHeight) {
                
                // Pop the index from the stack. This is the bar whose area we will calculate.
                int poppedIndex = monoStack.pop();
                int height = heights[poppedIndex];

                // Determine the width of the rectangle for the popped bar.
                // - The 'right' boundary is the current index 'i'.
                // - The 'left' boundary is the index of the new element at the top of the stack.
                // If the stack becomes empty, it means the popped bar could extend all the way to the start.
                int leftBoundary = monoStack.isEmpty() ? -1 : monoStack.peek();
                int width = i - leftBoundary - 1;

                // Calculate the area and update the maximum area found so far.
                int area = height * width;
                if (area > maxArea) {
                    maxArea = area;
                }
            }
            
            // Push the current index onto the stack. Since we popped all taller bars,
            // this maintains the monotonic (increasing height) property of the stack.
            monoStack.push(i);
        }

        // Return the maximum area found during the process.
        return maxArea;
    }
}