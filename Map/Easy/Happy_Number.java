/*
 * Solution by: Gemini (Your AI Assistant)
 * Problem: 202. Happy Number
 *
 * This code implements the optimal O(1) space solution using
 * Floyd's Cycle-Finding Algorithm (also known as the "tortoise and hare" or
 * fast/slow pointer method).
 */
class Solution {

    /**
     * Main function to determine if a number is a "happy number".
     *
     * @param n The number to check.
     * @return true if the number is happy, false otherwise.
     */
    public boolean isHappy(int n) {
        // 'slow' is the "tortoise". It moves one step at a time.
        int slow = n;
        
        // 'fast' is the "hare". It moves two steps at a time.
        // We initialize it one step ahead.
        int fast = getNext(n);

        // We loop until one of two conditions is met:
        // 1. fast == 1: We've reached the happy number end state.
        // 2. slow == fast: The fast pointer has "lapped" the slow
        //    pointer, meaning we have detected a cycle.
        while (fast != 1 && slow != fast) {
            
            // The slow pointer advances by ONE step.
            slow = getNext(slow);
            
            // The fast pointer advances by TWO steps.
            fast = getNext(getNext(fast));
        }

        // If the loop stopped, we just need to check WHY it stopped.
        // If 'fast' is 1, it means we reached the happy state.
        // If 'fast' is not 1, it must be because slow == fast,
        // meaning we found a cycle and the number is unhappy.
        return fast == 1;
    }

    /**
     * Helper function to calculate the sum of the squares of the digits
     * of a given number. This computes the "next" number in the sequence.
     *
     * @param n The input number.
     * @return The sum of the squares of its digits.
     */
    public int getNext(int n) {
        int sum = 0;
        
        // Loop to extract each digit from the number.
        while (n > 0) {
            // Get the last digit (e.g., 123 -> 3)
            int digit = n % 10;
            
            // Add the square of that digit to the sum.
            sum += digit * digit;
            
            // Remove the last digit (e.g., 123 -> 12)
            n /= 10;
        }
        
        // Return the total calculated sum.
        return sum;
    }
}