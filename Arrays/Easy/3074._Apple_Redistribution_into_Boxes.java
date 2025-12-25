/*
 * Problem: 3074. Apple Redistribution into Boxes
 * Strategy: Greedy Approach
 * 1. Calculate the total number of apples we need to store.
 * 2. To minimize the box count, we should prioritize boxes with the largest capacity.
 * 3. Sort the capacities and iterate backwards (largest to smallest) until the total
 * apple count is covered (reduced to <= 0).
 */

import java.util.Arrays;

class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        // Step 1: Calculate the total number of apples across all packs.
        // Time Complexity: O(N)
        int totalApples = 0;
        for(int n : apple) {
            totalApples += n;
        }

        // Step 2: Sort the capacity array to access largest boxes easily.
        // Time Complexity: O(M log M)
        Arrays.sort(capacity);

        int i = capacity.length - 1; // Pointer to the largest available box
        
        // Step 3: Greedily subtract box capacities from totalApples
        // until we have stored all apples.
        while(totalApples > 0) {
            totalApples -= capacity[i];
            i--;
        }

        // Step 4: Calculate used boxes.
        // The pointer 'i' is now at the index BEFORE the last used box.
        // Formula: (Total Boxes) - (1 + i) 
        // Logic: If length is 5 and we used index 4, i becomes 3. 5 - 1 - 3 = 1 box used.
        return capacity.length - 1 - i;
    }
}