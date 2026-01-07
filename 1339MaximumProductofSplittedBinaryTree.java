/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    private long totalSum = 0;
    private long maxProduct = 0;

    public int maxProduct(TreeNode root) {
        final long mod = 1000000007L;
        totalSum = calculateSum(root);
        calculateMaxProduct(root);
        return (int) (maxProduct % mod);
    }

    private long calculateSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + calculateSum(node.left) + calculateSum(node.right);
    }

    private long calculateMaxProduct(TreeNode node) {
        if (node == null) return 0;
        long subtreeSum = node.val + calculateMaxProduct(node.left) + calculateMaxProduct(node.right);
        if (subtreeSum < totalSum) {
            maxProduct = Math.max(maxProduct, subtreeSum * (totalSum - subtreeSum));
        }
        return subtreeSum;
    }
}