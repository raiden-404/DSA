// Problem: 149. Max Points on a Line
// Name: [Your Name Here]
// No: [Your ID/Number Here]

/**
 * This solution finds the maximum number of points on a line using a
 * brute-force, O(N^3) approach.
 *
 * --- The Main Idea ---
 * It iterates through every possible pair of points (i, j) to form a line.
 * Then, it iterates through every *third* point (k) and checks if point 'k'
 * also lies on the line formed by 'i' and 'j'.
 *
 * --- The Math Trick (Cross-Multiplication) ---
 * To check if 3 points (p1, p2, p3) are on the same line, we check if the
 * slope from p1 to p2 is equal to the slope from p1 to p3.
 *
 * Slope(p1, p2) == Slope(p1, p3)
 * (y2 - y1) / (x2 - x1) == (y3 - y1) / (x3 - x1)
 *
 * To avoid division by zero (for vertical lines) and floating-point
 * precision errors, we use cross-multiplication:
 *
 * (y2 - y1) * (x3 - x1) == (y3 - y1) * (x2 - x1)
 *
 * If this equation is true, the three points are co-linear.
 */
class Solution {
    public int maxPoints(int[][] points) {
        int n = points.length;

        // If there are 2 or fewer points, they must all be on the same line.
        if (n <= 2) {
            return n;
        }

        // Initialize the overall maximum. We know at least 2 points
        // can be on a line (if n > 2).
        int max = 2;

        // Outer loop: Iterate through the first point 'i' (our p1)
        for (int i = 0; i < n; i++) {
            
            // Second loop: Iterate through the second point 'j' (our p2)
            // Start from i+1 to avoid duplicate pairs and comparing a point to itself.
            for (int j = i + 1; j < n; j++) {
                
                // For any line formed by 'i' and 'j', it has at least 2 points.
                int res = 2;
                
                // Third loop: Iterate through the third point 'k' (our p3)
                // Start from j+1 to check a new, distinct point.
                for (int k = j + 1; k < n; k++) {
                    
                    // Let p1 = points[i], p2 = points[j], p3 = points[k]
                    // (y2 - y1) * (x3 - x1)
                    int x = (points[j][1] - points[i][1]) * (points[k][0] - points[i][0]);
                    
                    // (y3 - y1) * (x2 - x1)
                    int y = (points[k][1] - points[i][1]) * (points[j][0] - points[i][0]);

                    // If (x == y), the slopes are equal, and point 'k' is on the same line.
                    if (x == y) {
                        res++; // Increment the count for this specific line (i, j)
                    }
                    
                    // We must update the 'max' inside the 'k' loop.
                    // This handles cases like [[1,1],[2,2],[3,3],[4,5]]
                    // The line (1,1)-(2,2) will find (3,3) (res=3).
                    // If we only updated 'max' after the 'k' loop finished,
                    // 'max' would only be 3.
                    // But the line (1,1)-(4,5) or (2,2)-(4,5) might have more
                    // points later, so we must track the max 'res' found so far
                    // across *all* (i, j, k) combinations.
                    // Note: This check is slightly inefficient. It's better
                    // to move this *outside* the 'k' loop (after it finishes).
                    if (max < res) {
                        max = res;
                    }
                }
                
                /* // A slightly cleaner way to do the update:
                // After checking all 'k' points for the line (i, j):
                if (res > max) {
                   max = res;
                }
                // Your current code also works, it just updates 'max' more
                // frequently than necessary.
                */
            }
        }
        
        // Return the highest count found.
        return max;
    }
}