// LeetCode Problem 2073: Time Needed to Buy Tickets

class Solution {
    /**
     * Calculates the total time required for a specific person (at index k) to finish buying their tickets.
     *
     * This unique approach works by calculating the number of "remaining" tickets each person
     * would have after the person at index `k` buys their last ticket. It does this in two main steps:
     * 1.  It simulates the full rotations of the line by subtracting `tickets[k] - 1` from everyone.
     * This represents the number of times everyone gets a chance to buy a ticket before `k`'s final turn.
     * 2.  It simulates the final, partial rotation by subtracting 1 from everyone up to and including `k`.
     *
     * The total time is then calculated by summing up the number of tickets each person actually bought,
     * which is their original ticket count minus their calculated "remaining" ticket count.
     *
     * Time Complexity: O(N) - The code iterates through the array three times, which simplifies to O(N).
     * Space Complexity: O(N) - An extra array `res` of the same size as `tickets` is created.
     */
    public int timeRequiredToBuy(int[] tickets, int k) {
        // Create a copy of the tickets array to modify. This will store the "remaining" tickets.
        int[] res = tickets.clone();
        
        // Step 1: Account for the full rotations.
        // Before person k buys their LAST ticket, the line will have completed (tickets[k] - 1) full rotations.
        // In each full rotation, every person buys one ticket.
        for(int i = 0; i < res.length; i++) {
            res[i] -= (tickets[k] - 1);
        }
        
        // Step 2: Account for the final, partial rotation.
        // In the last rotation, only people from index 0 up to k will buy one more ticket.
        for(int i = 0; i <= k; i++) {
            res[i]--;
        }

        // Step 3: Calculate the total time.
        // The `res` array now holds the number of tickets each person DID NOT buy.
        // The number of tickets they DID buy is `tickets[i] - res[i]`.
        // Summing these up gives the total time, as each purchase takes one second.
        int time = 0;
        for(int i = 0; i < res.length; i++) {
            // `n` is the number of tickets person `i` has left over.
            int n = res[i];
            // If `n` is negative, it means this person finished buying all their tickets long ago.
            // We treat their remaining tickets as 0.
            if(n < 0) {
                n = 0;
            }
            // Add the number of tickets person `i` actually bought to the total time.
            time += (tickets[i] - n);
        }
        
        return time;
    }
}