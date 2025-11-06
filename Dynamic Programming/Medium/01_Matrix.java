import java.util.Arrays; // Import statement for the Arrays class (used for your debug print)

/**
 * Solves LeetCode 542. 01 Matrix using a two-pass Dynamic Programming approach.
 * * The core idea is to find the minimum distance to a 0 for each cell.
 * A single pass is not enough because a cell's nearest 0 could be in any
 * direction (top, left, bottom, right).
 * * 1. First Pass (Top-Left to Bottom-Right):
 * - Calculates the distance to the nearest 0 *only considering* paths from
 * the top and left.
 * - `mat[i][j] = min(mat[i-1][j], mat[i][j-1]) + 1`
 *
 * 2. Second Pass (Bottom-Right to Top-Left):
 * - Calculates the distance to the nearest 0 *only considering* paths from
 * the bottom and right.
 * - `mat[i][j] = min(mat[i+1][j], mat[i][j+1]) + 1`
 * - It then takes the minimum of this new value and the value from the first
 * pass: `mat[i][j] = min(value_from_pass_1, value_from_pass_2)`
 *
 * This two-pass combination correctly finds the minimum distance from all 4 directions.
 */
class Solution {
    public int[][] updateMatrix(int[][] mat) {

        // --- 1. FIRST PASS: Top-Left to Bottom-Right ---
        // This pass checks for the nearest 0 from the TOP and LEFT.
        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[0].length; j++) {
                // We update the matrix in-place with the result from this pass
                mat[i][j] = calcMinTL(i, j, mat);
            }
        }
        
        // (Debug print, can be removed)
        // System.out.println(Arrays.deepToString(mat));

        // --- 2. SECOND PASS: Bottom-Right to Top-Left ---
        // This pass checks for the nearest 0 from the BOTTOM and RIGHT,
        // and takes the minimum of this pass and the first pass.
        for (int i = mat.length - 1; i >= 0; i--) {
            for (int j = mat[0].length - 1; j >= 0; j--) {
                // Update the matrix again with the final, correct minimum distance
                mat[i][j] = calcMinBR(i, j, mat);
            }
        }
        
        // (Debug print, can be removed)
        // System.out.println(Arrays.deepToString(mat));

        return mat;
    }

    /**
     * Helper function for the first pass (Top-Left to Bottom-Right).
     * Calculates the minimum distance by only looking at the Top (i-1) and Left (j-1) neighbors.
     */
    public int calcMinTL(int i, int j, int[][] mat) {
        // If the cell itself is 0, the distance is 0. This is the base case.
        if (mat[i][j] == 0) return 0;

        // --- Handle edge cases where top or left neighbors don't exist ---
        
        // For the very first cell (0, 0), if it's a 1, we set it to a large value
        // as a placeholder, since it has no top/left neighbors.
        if (i == 0 && j == 0) {
            return Integer.MAX_VALUE;
        } 
        // For the top row (i == 0), we can only check the neighbor to the left.
        else if (i == 0) {
            // Check for integer overflow. If left is MAX_VALUE, we can't add 1.
            return mat[i][j - 1] == Integer.MAX_VALUE ? Integer.MAX_VALUE : mat[i][j - 1] + 1;
        } 
        // For the leftmost column (j == 0), we can only check the neighbor above.
        else if (j == 0) {
            // Check for integer overflow.
            return mat[i - 1][j] == Integer.MAX_VALUE ? Integer.MAX_VALUE : mat[i - 1][j] + 1;
        } 
        // --- Normal case ---
        else {
            // For any other cell, the distance is 1 + the minimum of its top or left neighbor.
            int val = Math.min(mat[i - 1][j], mat[i][j - 1]);
            
            // Check for overflow: if the min neighbor is MAX_VALUE, we stay at MAX_VALUE.
            if (val == Integer.MAX_VALUE) return Integer.MAX_VALUE;
            
            return val + 1;
        }
    }

    /**
     * Helper function for the second pass (Bottom-Right to Top-Left).
     * Calculates the min distance by looking at Bottom (i+1) and Right (j+1) neighbors,
     * AND compares this result with the existing value in mat[i][j] (from the first pass).
     */
    public int calcMinBR(int i, int j, int[][] mat) {
        // If the cell is 0, it's always 0.
        if (mat[i][j] == 0) return 0;

        // --- Handle edge cases where bottom or right neighbors don't exist ---

        // For the bottom-right cell, it has no neighbors. We just return its
        // current value (which was set by the first pass).
        if (i == mat.length - 1 && j == mat[0].length - 1) {
            return mat[i][j];
        } 
        // For the bottom row (i == max), we can only check the neighbor to the right.
        else if (i == mat.length - 1) {
            int curr = mat[i][j];     // The value from the Top-Left pass
            int next = mat[i][j + 1]; // The value from the neighbor (already processed)
            
            // If the neighbor is MAX_VALUE, we can't add 1. Just keep the current value.
            if (next == Integer.MAX_VALUE) {
                return curr;
            }
            // Return the minimum of (the value from pass 1) and (neighbor + 1)
            return Math.min(next + 1, curr);
        } 
        // For the rightmost column (j == max), we can only check the neighbor below.
        else if (j == mat[0].length - 1) {
            int curr = mat[i][j];
            int next = mat[i + 1][j];
            
            if (next == Integer.MAX_VALUE) {
                return curr;
            }
            return Math.min(next + 1, curr);
        } 
        // --- Normal case ---
        else {
            // Get the minimum of the bottom and right neighbors.
            int val = Math.min(mat[i][j + 1], mat[i + 1][j]);
            
            // If both neighbors are MAX_VALUE, we just keep the current value (from pass 1).
            if (val == Integer.MAX_VALUE) return mat[i][j];
            
            // The final value is the minimum of:
            // 1. mat[i][j] (the distance calculated from the Top-Left pass)
            // 2. val + 1   (the distance calculated from the Bottom-Right pass)
            return Math.min(mat[i][j], val + 1);
        }
    }
}