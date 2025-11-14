// Name: [Increment Submatrices by One]
// No: [2536]

/**
 * Solves the "Increment Submatrices by One" problem using a 2D Difference Array.
 *
 * --- The Main Idea ---
 * Instead of incrementing every cell in the N*N matrix for every query (which
 * would be slow, O(Q * N^2)), we use a "difference" matrix (here, 'res').
 *
 * 1.  **Marking (O(Q)):** For each query, we only mark the 4 "corners" of the
 * rectangle in the 'res' matrix. This takes constant time per query.
 * 2.  **Processing (O(N^2)):** After marking all query changes, we run two
 * "prefix sum" passes (one horizontal, one vertical) over the 'res'
 * matrix. These passes "unroll" or "propagate" the corner marks,
 * calculating the final accumulated value for every cell.
 *
 * This brings the total time complexity down to O(Q + N^2).
 */
class Solution {
    public int[][] rangeAddQueries(int n, int[][] Q) {
        
        // 'res' will act as our 2D difference array.
        // It's initialized to all zeros.
        int[][] res = new int[n][n];

        // === 1. MARKING PHASE ===
        // Loop through each query to mark the changes in the 'res' matrix.
        for (var r : Q) {
            // Get query coordinates
            int r0 = r[0]; // top-left row
            int c0 = r[1]; // top-left col
            // Get exclusive end bounds (one cell *past* the query rectangle)
            int r1 = r[2] + 1; // row *after* the bottom edge
            int c1 = r[3] + 1; // col *after* the right edge

            // --- Apply 4 marks (Inclusion-Exclusion) ---

            // 1. Add +1 at the top-left corner [r0][c0].
            // This says: "Start adding 1 to everything from here
            // downwards and to the right."
            res[r0][c0]++;

            // 2. Add -1 just *below* the rectangle's bottom edge [r1][c0].
            // This says: "Stop the vertical +1 propagation that started
            // at [r0][c0] when you reach this row."
            // (We only do this if r1 is within the matrix bounds)
            if (r1 < n) {
                res[r1][c0]--;
            }

            // 3. Add -1 just *past* the rectangle's right edge [r0][c1].
            // This says: "Stop the horizontal +1 propagation that started
            // at [r0][c0] when you reach this column."
            // (We only do this if c1 is within the matrix bounds)
            if (c1 < n) {
                res[r0][c1]--;

                // 4. Add +1 at the corner *below and past* the rectangle [r1][c1].
                // This is the inclusion-exclusion part.
                // The -1 at [r1][c0] and the -1 at [r0][c1] will both
                // incorrectly subtract from the entire bottom-right quadrant
                // starting at [r1][c1]. This +1 "adds back" one of those
                // subtractions to correct the count.
                // (This check must be nested, as it only applies if BOTH
                // r1 and c1 are within bounds).
                if (r1 < n) {
                    res[r1][c1]++;
                }
            }
        }

        // === 2. PROCESSING PHASE (2D Prefix Sum) ===

        // Pass 1: Horizontal Prefix Sum
        // This "unrolls" the horizontal marks. For each row, the +1 at [r0][c0]
        // propagates to the right, and the -1 at [r0][c1] stops it.
        // The result is a row that looks like: [0, ..., 1, 1, 1, ..., 0, 0]
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                res[i][j] += res[i][j - 1];
            }
        }

        // Pass 2: Vertical Prefix Sum
        // This "unrolls" the vertical marks. The line of 1s from the previous
        // pass (from [r0][c0] to [r0][c1-1]) is now added to the row below it
        // ([r0+1]), and that row is added to the one below it, and so on.
        // This "fills" the rectangle with 1s.
        // The line of -1s at row [r1] will cancel out the propagation,
        // stopping the "fill" at the bottom edge.
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < n; j++) {
                res[i][j] += res[i - 1][j];
            }
        }

        // The 'res' matrix, which started as a difference array,
        // has now been transformed into the final sum array.
        return res;
    }
}