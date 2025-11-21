/*
 * Question Name: Invert Binary Tree
 * Question No:   226
 * Platform:      LeetCode
 *
 * Complexity Analysis:
 * Time:  O(N) - We visit every node exactly once.
 * Space: O(h) - Space is used by the recursion stack, where h is the height of the tree.
 */

class Solution {
    public TreeNode invertTree(TreeNode root) {
        // Base Case: If node is null, return null
        if(root == null) return null;

        // Store the original left child because we are about to overwrite root.left
        TreeNode temp = root.left;

        // 1. Recursively invert the right subtree and assign it to the left pointer
        root.left = invertTree(root.right);

        // 2. Recursively invert the original left subtree (stored in temp) and assign it to the right pointer
        root.right = invertTree(temp);

        return root;
    }
}