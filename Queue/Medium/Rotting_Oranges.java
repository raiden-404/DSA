/**
 * Student Name: Rohan Kumar
 * Roll No: 19872
 *
 * LeetCode 994: Rotting Oranges
 * This solution uses a Breadth-First Search (BFS) approach with two queues to determine
 * the minimum time required for all fresh oranges to become rotten.
 */
class Solution {
    
    /**
     * Calculates the minimum number of minutes required for all fresh oranges to rot.
     *
     * @param grid A 2D integer array where 0 is empty, 1 is a fresh orange, and 2 is a rotten orange.
     * @return The minimum minutes, or -1 if some oranges can never rot.
     */
    public int orangesRotting(int[][] grid) {
        // queue1 holds the rotten oranges for the current minute (level).
        Queue<int[]> queue1 = new ArrayDeque<>();
        // queue2 holds the oranges that will become rotten in the next minute.
        Queue<int[]> queue2 = new ArrayDeque<>();
        
        // Variable to keep track of the elapsed time.
        int minutes = 0;

        // --- Step 1: Find all initially rotten oranges ---
        // Scan the entire grid to find all cells with a '2' and add them as the
        // starting points for our multi-source BFS.
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 2) {
                    queue1.offer(new int[]{i, j});
                }
            }
        }

        // --- Step 2: Simulate the rotting process minute by minute ---
        // This loop continues as long as there are rotten oranges to spread from.
        while(true) {
            
            // If queue1 is empty, it means there were no rotten oranges in the last
            // minute to spread from, so the process is over. We can break the loop.
            if(queue1.isEmpty()) break;

            // Process all rotten oranges for the current minute.
            while(!queue1.isEmpty()) {

                // Get a rotten orange from the current minute's queue.
                int[] cord = queue1.poll();
                int x = cord[0]; // row
                int y = cord[1]; // col

                // --- Check all 4 adjacent neighbors ---

                // Check the neighbor to its LEFT.
                if(y - 1 >= 0 && grid[x][y-1] == 1) {
                    // If the neighbor is a fresh orange, it becomes rotten.
                    grid[x][y-1] = 2;
                    // Add it to queue2, as it will start rotting others in the *next* minute.
                    queue2.offer(new int[]{x, y-1});
                }

                // Check the neighbor to its RIGHT.
                if(y + 1 < grid[0].length && grid[x][y+1] == 1) {
                    grid[x][y+1] = 2;
                    queue2.offer(new int[]{x, y+1});
                }
                
                // Check the neighbor ABOVE (TOP).
                if(x - 1 >= 0 && grid[x-1][y] == 1) {
                    grid[x-1][y] = 2;
                    queue2.offer(new int[]{x-1, y});
                }
                
                // Check the neighbor BELOW (BOTTOM).
                if(x + 1 < grid.length && grid[x+1][y] == 1) {
                    grid[x+1][y] = 2;
                    queue2.offer(new int[]{x+1, y});
                }
            }

            // After processing a full minute, check if any new oranges became rotten.
            // If queue2 is not empty, it means the rotting process is still ongoing.
            if(queue2.peek() != null) {
                minutes++; // Increment the timer.
            }

            // Prepare for the next minute: move all newly rotten oranges from
            // the helper queue (queue2) back to the main queue (queue1).
            while(!queue2.isEmpty()) {
                int[] temp = queue2.poll();
                queue1.offer(temp);
            }
        }

        // --- Step 3: Final check for any remaining fresh oranges ---
        // After the simulation, scan the grid one last time. If any '1's are left,
        // it means they were unreachable and the task is impossible.
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(grid[i][j] == 1) return -1; // Found an unrotten orange.
            }
        }
        
        // If no fresh oranges are left, return the total minutes passed.
        return minutes;
    }
}