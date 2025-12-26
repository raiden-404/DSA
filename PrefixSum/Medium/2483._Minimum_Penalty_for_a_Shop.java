/**
 * LeetCode Problem 2483: Minimum Penalty for a Shop
 * * Approach: Prefix and Suffix Sums
 * Time Complexity: O(N) - We iterate through the customers string 3 times.
 * Space Complexity: O(N) - We use an auxiliary array of size N+1.
 */
class Solution {
    public int bestClosingTime(String customers) {
        int n = customers.length();
        
        // We need an array of size n + 1 because closing times can range from 
        // 0 (before the first hour) to n (after the last hour).
        // arr[i] will eventually store the total penalty if we close at the i-th hour.
        int[] arr = new int[n + 1];
        
        // ---------------------------------------------------------
        // PASS 1: Calculate Suffix Penalty (The cost of closing too early)
        // ---------------------------------------------------------
        // If we close at hour 'i', we miss all 'Y' customers from hour 'i' onwards.
        // We iterate backwards to accumulate the count of 'Y's.
        int suffixYCount = 0;
        for (int i = n - 1; i >= 0; i--) {
            char c = customers.charAt(i);

            // If there is a customer ('Y') at this hour, closing here or before adds to the penalty.
            if (c == 'Y') {
                suffixYCount++;
            }
            // Store the penalty for missing future customers if we close at 'i'
            arr[i] = suffixYCount; 
        }

        // Note: arr[n] remains 0 initially, because if we close at hour 'n' (after everyone),
        // we miss 0 customers.

        // ---------------------------------------------------------
        // PASS 2: Calculate Prefix Penalty (The cost of staying open when empty)
        // ---------------------------------------------------------
        // If we close at hour 'i', it means we were OPEN from hour 0 to i-1.
        // We incur a penalty for every 'N' encountered during those open hours.
        int prefixNCount = 0;
        for (int i = 1; i <= n; i++) {
            // Check the hour we just kept the shop open for (index i-1)
            char c = customers.charAt(i - 1);
            
            if (c == 'N') {
                prefixNCount++;
            }
            
            // Add this "cost to stay open" to the existing "cost to close early"
            // arr[i] now represents the Total Penalty for closing at hour i.
            arr[i] += prefixNCount;
        }

        // ---------------------------------------------------------
        // PASS 3: Find the Optimal Closing Time
        // ---------------------------------------------------------
        // We iterate through the calculated penalties to find the minimum.
        // If we find a tie, we keep the earliest hour (smallest index), as required by the problem.
        int bestHour = 0;
        int minPenalty = Integer.MAX_VALUE;

        for (int i = 0; i <= n; i++) {
            if (arr[i] < minPenalty) {
                minPenalty = arr[i];
                bestHour = i;
            }
        }
        
        return bestHour;
    }
}