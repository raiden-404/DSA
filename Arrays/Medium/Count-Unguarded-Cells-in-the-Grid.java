class Solution {
    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {

        /**
            We can improve this code more futher, by first setting all the wall
            Then go one by one to each guard, and write four individual loop
            for guard to its right, to its left, to its top and to its bottom
            Terminate the loop when we counter and wall or other guard or end of grid
            This the reduce the unusual traversing on non guraded cells.
         */


        int[][] grid = new int[m][n];

        // Place guards, use 2 for guards
        for(int i = 0; i<guards.length; i++) {
            int x = guards[i][0];
            int y = guards[i][1];
            grid[x][y] = 2;
        }
        
        // Place walls, use 1 for Walls
        for(int i = 0; i<walls.length; i++) {
            int x = walls[i][0];
            int y = walls[i][1];
            grid[x][y] = 1;
        }

        // fill from left to right
        int fill = 0;
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 1) {
                    fill = 0;
                } else if (grid[i][j] == 2) {
                    fill = -1;
                } else if(grid[i][j] == 0) {
                    grid[i][j] = fill;
                }
            }
            fill = 0;
        }

        // Fill from right to left
        fill = 0;
        for(int i=m-1; i>=0; i--) {
            for(int j=n-1; j>=0; j--) {
                if(grid[i][j] == 1) {
                    fill = 0;
                } else if (grid[i][j] == 2) {
                    fill = -1;
                } else if(grid[i][j] == 0) {
                    grid[i][j] = fill;
                }
            }
            fill = 0;
        }

        //Fill from top to bottom
        fill = 0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(grid[j][i] == 1) {
                    fill = 0;
                } else if (grid[j][i] == 2) {
                    fill = -1;
                } else if(grid[j][i] == 0) {
                    grid[j][i] = fill;
                }
            }
            fill = 0;
        }

        // Fill from bottom to up
        //Fill from top to bottom
        fill = 0;
        for(int i=n-1; i>=0; i--) {
            for(int j=m-1; j>=0; j--) {
                if(grid[j][i] == 1) {
                    fill = 0;
                } else if (grid[j][i] == 2) {
                    fill = -1;
                } else if(grid[j][i] == 0) {
                    grid[j][i] = fill;
                }
            }
            fill = 0;
        }

        int count = 0;
        // Final loop for count
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j] == 0) count++;
            }
        }
        
        return count;
    }
}