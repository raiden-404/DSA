/**
 * Definition for a binary tree node.
 * public class TreeNode {
 * int val;
 * TreeNode left;
 * TreeNode right;
 * TreeNode() {}
 * TreeNode(int val) { this.val = val; }
 * TreeNode(int val, TreeNode left, TreeNode right) {
 * this.val = val;
 * this.left = left;
 * this.right = right;
 * }
 * }
 */
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        // Base Case 1: If the tree is empty or we hit a leaf without finding the target
        if (root == null) return null;
        
        // Base Case 2: Target value found, return the current node
        if (val == root.val) return root;
        
        // Recursive Step: Leverage BST Property
        // If target > current value, search Right. Otherwise, search Left.
        return val > root.val ? searchBST(root.right, val) : searchBST(root.left, val);
    }
}