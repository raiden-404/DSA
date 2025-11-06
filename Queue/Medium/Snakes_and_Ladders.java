import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

/**
 * Helper class to store 2D coordinates (row, col).
 * Made static because it doesn't need to access any
 * members of the outer 'Solution' class.
 */
class Cords {
    int i;
    int j;
    Cords(int val1, int val2) {
        i = val1;
        j = val2;
    }
}

/**
 * Solves the Snakes and Ladders problem using Breadth-First Search (BFS).
 * The problem asks for the *minimum* number of moves, which is a classic
 * use case for BFS.
 *
 * We treat the board as a graph where each square (1 to n*n) is a node.
 * An "edge" represents a die roll (1-6). Snakes and ladders are just
 * "teleports" that move you from one node to another instantly.
 *
 * The BFS explores the board level by level, where each level corresponds
 * to one move (one die roll).
 */
class Solution {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        int target = n * n; // The destination square

        // A queue to store the squares to visit in our BFS.
        Queue<Integer> queue = new ArrayDeque<>();
        
        // A set to store squares we've already processed.
        // This is crucial to prevent re-visiting squares and getting
        // stuck in infinite loops (e.g., a snake leading to a ladder
        // that leads back to the snake).
        Set<Integer> visited = new HashSet<>();

        // Start the game at square 1.
        queue.offer(1);
        visited.add(1);

        int count = 0; // This will track the number of moves.

        while (!queue.isEmpty()) {
            // 'size' is the number of squares we can reach at the
            // current move level.
            int size = queue.size();
            
            // We are starting a new move, so increment the count.
            count++;

            // Process all squares at the current level.
            for (int i = 0; i < size; i++) {
                int curr = queue.poll(); // The square we are "standing" on.

                // Simulate all 6 possible die rolls.
                for (int roll = 1; roll <= 6; roll++) {
                    int nextSquare = curr + roll; // The square we land on.

                    // Stop if we roll past the target.
                    if (nextSquare > target) break;

                    // Get the board value at the square we landed on.
                    // This will be -1 (normal), or a square number (snake/ladder).
                    int boardValue = getAt(nextSquare, board);

                    // Determine our final destination after this roll.
                    int destination;
                    if (boardValue == -1) {
                        // It's a normal square, our destination is where we landed.
                        destination = nextSquare;
                    } else {
                        // It's a snake or ladder, our destination is the teleport square.
                        destination = boardValue;
                    }

                    // --- Check for win ---
                    // If our final destination is the target, we are done!
                    if (destination == target) {
                        return count;
                    }

                    // --- Process the new square ---
                    // If we haven't visited this final destination square before,
                    // add it to the queue and mark it as visited.
                    if (!visited.contains(destination)) {
                        visited.add(destination);
                        queue.offer(destination);
                    }
                }
            }
        }

        // If the queue becomes empty and we never reached the target,
        // it's impossible to win.
        return -1;
    }


    /**
     * Converts a board square number (1 to n*n) into its
     * 2D (row, col) coordinates.
     * This handles the "Boustrophedon" (snake-like) numbering.
     *
     * @param val  The square number (1-based).
     * @param size The board dimension (n).
     * @return A Cords object with the 0-indexed (i, j) coordinates.
     */
    private Cords findCords(int val, int size) {
        // (val-1) / size gives the "row index" from the bottom (0-based).
        // (size - 1 - ...) converts it to the "row index" from the top (0-based).
        int i = size - 1 - (val - 1) / size;

        int j;
        
        // (size - 1 - i) is the row index from the bottom.
        // We check if this row index is even or odd to determine direction.
        if ((size - 1 - i) % 2 == 0) {
            // Even row (from bottom): e.g., row 0, 2, 4...
            // Numbering is LEFT-TO-RIGHT.
            j = (val - 1) % size;
        } else {
            // Odd row (from bottom): e.g., row 1, 3, 5...
            // Numbering is RIGHT-TO-LEFT.
            j = size - 1 - (val - 1) % size;
        }
        
        return new Cords(i, j);
    }

    /**
     * A helper function to get the value at a given square number.
     * @param val   The square number (1-based).
     * @param board The game board.
     * @return The value at that square (-1 or a teleport number).
     */
    private int getAt(int val, int[][] board) {
        Cords c = findCords(val, board.length);
        return board[c.i][c.j];
    }
}