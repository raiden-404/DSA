/**
 * LeetCode Problem 447: Number of Boomerangs
 * * Problem Analysis:
 * We need to find tuples (i, j, k) such that the distance between i and j 
 * equals the distance between i and k. order matters.
 * This effectively means finding pairs of points that are equidistant from a center point 'i'.
 * * Complexity:
 * Time: O(N^2) - We iterate through each point as a 'center' and measure distance to all other points.
 * Space: O(N) - The HashMap stores distances relative to the current center point (max N entires).
 */
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        // Map to store <DistanceSquared, Count>
        // We use Integer for distance to avoid floating point precision issues with Double
        Map<Integer, Integer> map = new HashMap<>();
        
        int n = points.length;
        int res = 0;

        // Loop 1: Select 'i' as the potential "center" (pivot) of the boomerang
        for(int i = 0; i < n; i++) {
            
            int xi = points[i][0];
            int yi = points[i][1];

            // Loop 2: Measure distance from center 'i' to every other point 'j'
            for(int j = 0; j < n; j++) {
                if(i == j) continue; // A point cannot be distant from itself

                int xj = points[j][0];
                int yj = points[j][1];

                // Calculate Squared Euclidean Distance
                // We use squared distance (x^2 + y^2) to avoid Math.sqrt() for performance and precision
                int d = getDistSq(xi, yi, xj, yj);
                
                // Store the frequency of this distance in the map
                map.put(d, map.getOrDefault(d, 0) + 1);
            }

            // Calculate Permutations for this center 'i'
            // If 'count' points are at the same distance from 'i', we can pick any 2 of them.
            // Since order matters (j, k vs k, j), the number of ways is count * (count - 1).
            for(int count : map.values()) {
                res += count * (count - 1);
            }

            // Clear the map before processing the next center point 'i'
            // This recycles the map object, saving memory allocation overhead
            map.clear();
        }
        
        return res;
    }

    /**
     * Helper function to calculate Squared Euclidean Distance.
     * Using (dx*dx + dy*dy) avoids the costly Math.sqrt() operation.
     * If dist(A) == dist(B), then dist(A)^2 == dist(B)^2.
     */
    private int getDistSq(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        return dx * dx + dy * dy;
    }
}