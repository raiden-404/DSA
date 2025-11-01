import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LeetCode Problem: 739. Daily Temperatures
 *
 * This solution uses a monotonic decreasing stack to find the next warmer day
 * for each day in the input array in a single pass.
 */
class Solution {
    /**
     * Given an array of integers temperatures representing the daily temperatures,
     * return an array answer such that answer[i] is the number of days you have
     * to wait after the ith day to get a warmer temperature. If there is no
     * future day for which this is possible, keep answer[i] == 0 instead.
     *
     * @param temperatures An array of daily temperatures.
     * @return An array containing the number of waiting days for a warmer temperature.
     */
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        
        // The result array is automatically initialized with zeros.
        int[] result = new int[n]; 
        
        // The stack will store the indices of the days. The temperatures corresponding
        // to these indices will be in a strictly decreasing order from bottom to top.
        Deque<Integer> stack = new ArrayDeque<>();

        // Iterate through each day's temperature.
        for (int i = 0; i < n; i++) {
            // Check if the current day's temperature is warmer than the temperature
            // of the day at the top of the stack.
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                // If it is, we have found the answer for the day on the stack.
                int prevDayIndex = stack.pop();
                
                // Calculate the number of days waited and store it in the result.
                result[prevDayIndex] = i - prevDayIndex;
            }
            
            // Push the current day's index onto the stack. It now waits for a
            // future warmer day.
            stack.push(i);
        }

        // Any indices left in the stack have no warmer day in the future.
        // Since the result array was initialized to zeros, we don't need to do anything else.
        return result;
    }
}