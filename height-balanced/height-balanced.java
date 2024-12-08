
class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(findHeight(root.left) - findHeight(root.right)) < 2 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int findHeight(TreeNode treeNode) {
        if (treeNode == null) {
            return -1;
        }

        return 1 + Math.max(findHeight(treeNode.left), findHeight(treeNode.right));
    }

    class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
