// LeetCode Problem 1578: Minimum Time to Make Rope Colorful

class Solution {
    /**
     * Calculates the minimum time needed to make the rope colorful by ensuring
     * no two adjacent balloons have the same color.
     *
     * This method uses a greedy approach. It iterates through the balloons, and whenever
     * it finds a pair of adjacent balloons with the same color, it decides to
     * "remove" the one with the smaller removal time. The cost of that removal is
     * added to the total time. To handle groups of more than two identical balloons,
     * it cleverly carries forward the maximum time of the balloon we've decided to
     * keep, ensuring the next comparison is always against the most costly balloon
     * in the current consecutive sequence.
     *
     * @param colors A string representing the colors of the balloons.
     * @param neededTime An array where neededTime[i] is the time to remove balloon i.
     * @return The minimum total time required.
     */
    public int minCost(String colors, int[] neededTime) {
        // 'time' will accumulate the total minimum cost.
        int time = 0;
        char[] ch = colors.toCharArray();
        int n = ch.length - 1;

        // Iterate through the balloons, comparing each balloon with the next one.
        for(int i = 0; i < n; i++) {
            // Check if the current balloon and the next one have the same color.
            if(ch[i] == ch[i+1]) {
                // We have a conflict and must remove one. To minimize cost, we remove
                // the one with the smaller neededTime.

                // Case 1: The current balloon is cheaper or equal in cost to remove.
                if(neededTime[i] <= neededTime[i+1]) {
                    // Add the current balloon's removal time to the total cost.
                    // We conceptually "keep" the balloon at i+1 because it's more expensive.
                    time += neededTime[i];
                }
                // Case 2: The next balloon is cheaper to remove.
                else {
                    // Add the next balloon's removal time to the total cost.
                    time += neededTime[i+1];
                    
                    // This is a crucial step for handling groups of 3 or more.
                    // By updating the time of the next balloon to the current one's time,
                    // we ensure that we "carry forward" the maximum time seen so far
                    // in this group of identical colors. The next iteration will then
                    // correctly compare against this maximum value.
                    neededTime[i+1] = neededTime[i];
                }
            }
        }
        return time;
    }
}