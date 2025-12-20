/*
 * Problem: 944. Delete Columns to Make Sorted
 * Platform: LeetCode
 * * Approach: Greedy / Column-wise Traversal
 * We iterate through each column (character index) across all strings.
 * If we find any column where the characters are not in non-decreasing order 
 * (i.e., strs[j] > strs[j+1]), we must delete that column.
 */

class Solution {
    public int minDeletionSize(String[] strs) {
        // Variable to keep track of the number of columns to be deleted
        int res = 0;
        
        // Edge case check (though constraints usually say strs is non-empty)
        if (strs == null || strs.length == 0) return 0;

        // Get the length of the strings (number of columns). 
        // All strings are guaranteed to be the same length.
        int colLen = strs[0].length();
        int rowLen = strs.length;

        // OUTER LOOP: Iterate through each column index 'i'
        for (int i = 0; i < colLen; i++) {
            
            // Initialize 'prev' to the smallest possible char 'a'.
            // This acts as the comparison baseline for the first row.
            // Note: Since inputs are lowercase English letters, 'a' is the minimum.
            char prev = 'a';
            
            // INNER LOOP: Iterate through each string (row) 'j' for the current column 'i'
            for (int j = 0; j < rowLen; j++) {
                
                // Check if the sequence is broken.
                // If the previous character is strictly greater than the current one,
                // the column is NOT sorted alphabetically.
                if (prev > strs[j].charAt(i)) {
                    // We found a violation, so this column must be deleted.
                    res++;
                    
                    // Break the inner loop immediately to move to the next column.
                    // We don't need to check the rest of this column.
                    break;
                }
                
                // Update 'prev' to the current character for the next iteration's comparison.
                prev = strs[j].charAt(i);
            }
        }
        
        // Return the total count of columns that need deletion
        return res;
    }
}