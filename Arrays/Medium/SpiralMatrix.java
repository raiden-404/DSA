package Medium;
import java.util.List;
import java.util.ArrayList;
/* 54. Spiral Matrix

Task: Given a 2D matrix, return all of its elements in spiral order. This tests your ability to carefully manage boundaries and state while traversing an array.
*/
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        // List to return
        List<Integer> result = new ArrayList<>();
        // Return empty list for null matrix
        if(matrix == null || matrix.length == 0) {
            return result;
        }
        // Use Four pointer technique
        // Top, Bottom, Left and Right for setting the boundary
        int top = 0;
        int bottom = matrix.length-1;
        int left = 0;
        int right = matrix[0].length-1;

        // Run till any boundary overlap
        while(top <= bottom && left <= right) {

            // Traverse from left to right
            for(int i=left; i <= right; i++) {
                result.add(matrix[top][i]);
            }
            // We don't need top column anymore, so move to next column
            top++;

            // Traverse from top to down
            for(int i=top; i <= bottom; i++) {
                result.add(matrix[i][right]);
            }
            // We don't need right row
            right--;

            // Check if still valid/ untraversed column left 
            if(top <= bottom) {
                // Traverse form right to left
                for(int i=right; i >= left; i--) {
                    result.add(matrix[bottom][i]);
                }
                bottom--;
            }

            // Check if still valid column left
            if(left <= right) {
                for(int i=bottom; i >= top; i--) {
                    result.add(matrix[i][left]);
                }
                left++;
            }
        }
        return result;
    }
}