/**
 * LeetCode 1351. Count Negative Numbers in a Sorted Matrix
 * Approach: Linear Traversal (Staircase Method)
 */
class Solution {
    public int countNegatives(int[][] grid) {
        int count = 0;
        int rows = grid.length;
        int cols = grid[0].length;
        
        // Start at the bottom-left corner of the matrix
        int row = rows - 1;
        int col = 0;

        // Loop as long as we are inside the matrix boundaries
        while (row >= 0 && col < cols) {
            
            // Check current element
            if (grid[row][col] < 0) {
                // If the current element is negative, then all elements 
                // to its right in this row are also negative (due to sorting).
                // Example: [5, 1, -2, -3] -> if we are at -2, -3 is also neg.
                // We add (cols - col) to the result.
                count += (cols - col);
                
                // Since we handled this row, move up to the previous row
                row--; 
            } else {
                // If the current element is positive, we need to move right
                // to find smaller (potentially negative) numbers.
                col++;
            }
        }
        
        return count;
    }
}