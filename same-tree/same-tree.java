
class Solution {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p != null && q != null && p.val == q.val) {
            if (p.left == null && q.left == null && p.right == null && q.right == null) {
                return true;
            }

            return compare(p.left, q.left) && compare(p.right, q.right);
        }

        return p == null && q == null;
    }

    public boolean compare(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p != null && q != null) return isSameTree(p, q);
        return false;
    }

    public class TreeNode {

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
