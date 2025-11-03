/**
 * Student Name: Priya Sharma
 * Roll No: 25043
 *
 * LeetCode 200: Number of Islands
 * This solution uses Breadth-First Search (BFS) with a queue to count the number of islands in a 2D grid.
 */

 import java.util.Queue;
 import java.util.ArrayDeque;

class Solution {
    
    /**
     * Calculates the number of islands in a grid where '1' represents land and '0' represents water.
     * An island is a group of connected '1's (horizontally or vertically).
     *
     * @param grid A 2D character array representing the map of land and water.
     * @return The total number of distinct islands.
     */
    public int numIslands(char[][] grid) {
        // Handle edge cases: if the grid is null or empty, there are no islands.
        if (grid == null || grid.length == 0) {
            return 0;
        }

        // A queue to store the coordinates of land cells to visit during BFS.
        // We store coordinates as an array [row, col].
        Queue<int[]> queue = new ArrayDeque<>();
        
        // Counter for the number of islands found.
        int isLand = 0;

        // Iterate through each cell of the grid to find the start of an island.
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[i].length; j++) {
                
                // If we find a '1', it means we've discovered a new, unvisited island.
                if(grid[i][j] == '1') {
                    // Increment the island count.
                    isLand++;
                    
                    // Mark the cell as visited immediately to prevent recounting it.
                    // We use '2' to represent visited land, but '0' would also work.
                    grid[i][j] = '2'; 
                    // Add the starting cell of the new island to the queue to begin BFS.
                    queue.offer(new int[]{i, j});

                    // Start the BFS traversal to find and mark all parts of this single island.
                    while(!queue.isEmpty()) {
                        // Get the current land cell's coordinates from the front of the queue.
                        int[] cord = queue.poll();
                        
                        // --- Check all 4 adjacent neighbors (Up, Down, Left, Right) ---

                        // Check the cell to the LEFT.
                        // It must be within the grid bounds (col-1 >= 0) and be unvisited land ('1').
                        if(cord[1] - 1 >= 0 && grid[cord[0]][cord[1] - 1] == '1') {
                            grid[cord[0]][cord[1] - 1] = '2'; // Mark it as visited.
                            queue.offer(new int[]{cord[0], cord[1] - 1}); // Add it to the queue to visit its neighbors later.
                        }
                        
                        // Check the cell to the RIGHT.
                        // It must be within the grid bounds (col+1 < grid[0].length) and be unvisited land ('1').
                        if(cord[1] + 1 < grid[0].length && grid[cord[0]][cord[1] + 1] == '1') {
                            grid[cord[0]][cord[1] + 1] = '2'; // Mark it as visited.
                            queue.offer(new int[]{cord[0], cord[1] + 1}); // Add it to the queue.
                        }
                        
                        // Check the cell ABOVE (TOP).
                        // It must be within the grid bounds (row-1 >= 0) and be unvisited land ('1').
                        if(cord[0] - 1 >= 0 && grid[cord[0] - 1][cord[1]] == '1') {
                            grid[cord[0] - 1][cord[1]] = '2'; // Mark it as visited.
                            queue.offer(new int[]{cord[0] - 1, cord[1]}); // Add it to the queue.
                        }
                        
                        // Check the cell BELOW (BOTTOM).
                        // It must be within the grid bounds (row+1 < grid.length) and be unvisited land ('1').
                        if(cord[0] + 1 < grid.length && grid[cord[0] + 1][cord[1]] == '1') {
                            grid[cord[0] + 1][cord[1]] = '2'; // Mark it as visited.
                            queue.offer(new int[]{cord[0] + 1, cord[1]}); // Add it to the queue.
                        }
                    } // End of BFS for the current island.
                }
            }
        }
        // After scanning the entire grid, return the total count of islands found.
        return isLand;
    }
}