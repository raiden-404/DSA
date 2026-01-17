/*
 * Problem: 3047. Find the Largest Area of Square Inside Two Rectangles
 * * Approach: Brute Force / Pairwise Intersection
 * 1. Iterate through every unique pair of rectangles (i, j).
 * 2. Calculate the intersection rectangle for each pair.
 * 3. The intersection is defined by:
 * - Max of the left boundaries (bottomLeft x)
 * - Min of the right boundaries (topRight x)
 * - Max of the bottom boundaries (bottomLeft y)
 * - Min of the top boundaries (topRight y)
 * 4. Determine the width and height of this intersection.
 * 5. If a valid intersection exists (width > 0 and height > 0), the largest square
 * that can fit inside has a side length equal to min(width, height).
 * 6. Track and return the maximum square area found.
 */

class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long res = 0;

        // Iterate through all unique pairs of rectangles
        // O(N^2) complexity is acceptable for N <= 1000
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                
                // Calculate the area of the largest square within the intersection of rectangle i and j
                long interArea = computeArea(bottomLeft[i], topRight[i], bottomLeft[j], topRight[j]);
                
                // Update the maximum result found so far
                if (res < interArea) {
                    res = interArea;
                }
            }
        }
        return res;
    }

    // Helper method to compute the square area from the intersection of two rectangles
    public long computeArea(int[] bL1, int[] tR1, int[] bL2, int[] tR2) {
        // Find the intersection boundaries
        // The left edge is the rightmost of the two left edges
        long left = Math.max(bL1[0], bL2[0]);
        // The right edge is the leftmost of the two right edges
        long right = Math.min(tR1[0], tR2[0]);
        // The bottom edge is the topmost of the two bottom edges
        long bottom = Math.max(bL1[1], bL2[1]);
        // The top edge is the bottommost of the two top edges
        long top = Math.min(tR1[1], tR2[1]);

        // Calculate dimensions of the intersection rectangle
        long width = right - left;
        long height = top - bottom;

        // If width or height is <= 0, the rectangles do not intersect (or just touch)
        if (width <= 0 || height <= 0) {
            return 0L;
        }

        // The largest square that fits inside a rectangle is limited by the shorter side.
        // Side of square = min(width, height)
        // Area = side * side
        long side = Math.min(width, height);
        
        return side * side;
    }
} 