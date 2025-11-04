/*
 * LeetCode Problem 463: Island Perimeter
 *
 * Problem Description:
 * You are given a grid of 1s (land) and 0s (water).
 * The grid contains exactly one island (one or more connected 1s).
 * The island doesn't have "lakes" (water inside that isn't connected to
 * the water outside the island).
 * Find the perimeter of the island.
 *
 * Approach:
 * The perimeter is the total number of edges of land cells that
 * border either water (0) or the edge of the grid.
 *
 * We can iterate through every single cell of the grid.
 * If a cell is land (1), we check its four adjacent sides (top, left, bottom, right).
 * For each side, if it borders water (0) OR the grid boundary,
 * we add 1 to our perimeter count.
 *
 * This approach counts every exposed edge exactly once.
 *
 * Time Complexity: O(R * C), where R is rows, C is columns. We visit every cell.
 * Space Complexity: O(1). We only use a counter variable.
 */
class Solution {

    /**
     * Calculates the perimeter of the single island in the grid.
     *
     * @param grid A 2D array of integers (0s and 1s) representing the map.
     * @return The total perimeter of the island.
     */
    public int islandPerimeter(int[][] grid) {
        // 'perimeterCount' will store the total perimeter.
        int perimeterCount = 0;

        // Iterate over every row.
        for (int i = 0; i < grid.length; i++) {
            // Iterate over every column in the current row.
            for (int j = 0; j < grid[0].length; j++) {

                // We only care about cells that are land (1).
                if (grid[i][j] == 1) {

                    // --- Check all 4 directions for this land cell ---

                    /*
                     * Check TOP
                     * The perimeter increases if:
                     * 1. We are in the topmost row (i == 0), OR
                     * 2. The cell directly above us is water (grid[i-1][j] == 0).
                     *
                     * (The code uses i <= 0, which works because i starts at 0,
                     * and grid[i-1][j] != 1 is equivalent to grid[i-1][j] == 0
                     * since the grid only contains 0s and 1s).
                     */
                    if (i <= 0 || grid[i - 1][j] != 1) {
                        perimeterCount++;
                    }

                    /*
                     * Check LEFT
                     * The perimeter increases if:
                     * 1. We are in the leftmost column (j == 0), OR
                     * 2. The cell directly to our left is water (grid[i][j-1] == 0).
                     */
                    if (j <= 0 || grid[i][j - 1] != 1) {
                        perimeterCount++;
                    }

                    /*
                     * Check BOTTOM
                     * The perimeter increases if:
                     * 1. We are in the bottommost row (i == grid.length - 1), OR
                     * 2. The cell directly below us is water (grid[i+1][j] == 0).
                     */
                    if (i >= grid.length - 1 || grid[i + 1][j] != 1) {
                        perimeterCount++;
                    }

                    /*
                     * Check RIGHT
                     * The perimeter increases if:
                     * 1. We are in the rightmost column (j == grid[0].length - 1), OR
                     * 2. The cell directly to our right is water (grid[i][j+1] == 0).
                     */
                    if (j >= grid[0].length - 1 || grid[i][j + 1] != 1) {
                        perimeterCount++;
                    }
                }
            }
        }

        // After checking every cell, return the total accumulated perimeter.
        return perimeterCount;
    }
}