
class Solution {

    public boolean isSymmetric(TreeNode root) {
        return check(root.left, root.right);
    }

    private boolean check(TreeNode firstNode, TreeNode secondNode) {
        if (firstNode == null || secondNode == null) {
            return firstNode == secondNode;
        }

        return firstNode.val == secondNode.val && check(firstNode.left, secondNode.right) && check(firstNode.right, secondNode.left);
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
