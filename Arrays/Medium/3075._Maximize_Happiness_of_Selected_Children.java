/*
 * Problem: 3075. Maximize Happiness of Selected Children
 * * Strategy: Greedy Approach
 * 1. To maximize total happiness, we should always pick the children with the highest 
 * base happiness values first.
 * 2. Since picking a child decreases the happiness of all *remaining* unpicked children, 
 * the order matters.
 * 3. By picking the largest values first, we ensure that the largest numbers suffer 
 * the smallest penalties (0, then 1, then 2...).
 */

import java.util.Arrays;

class Solution {
    public long maximumHappinessSum(int[] happiness, int k) {
        // Step 1: Sort the array in ascending order.
        // This allows us to access the largest happiness values at the end of the array.
        // Time Complexity: O(N log N)
        Arrays.sort(happiness);

        long res = 0;   // Stores the total maximum happiness
        int done = 0;   // Tracks how many children have already been selected (the "decrement" amount)
        int n = happiness.length - 1; // Pointer to the end of the array (largest element)

        // Step 2: Iterate 'k' times to select the top k children
        for(int i = k; i > 0; i--) {
            // Calculate the current child's happiness value after the penalty.
            // The penalty corresponds to 'done' (number of children picked before this one).
            // We ensure the value does not drop below 0 using the check (happiness[n] - done) <= 0.
            long currentVal = happiness[n] - done;
            
            // If currentVal is positive, add it to result. If negative or zero, add 0.
            res += Math.max(0, currentVal);

            // Move pointer to the next largest child
            n--;
            
            // Increment the penalty counter for the next selection
            done++;
        }
        
        return res;
    }
}