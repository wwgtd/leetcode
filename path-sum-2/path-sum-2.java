
import java.util.ArrayList;
import java.util.List;


class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<Integer> history = new ArrayList();
        history.add(root.val);
        List<List<Integer>> result = new ArrayList();

        search(root, targetSum, history, result);

        return result;
    }

    private void search(TreeNode root, int targetSum, List<Integer> history, List<List<Integer>> result) {
        if (root.right == null && root.left == null) {
            if (targetSum == root.val) {
                List<Integer> newResult = new ArrayList(history);
                newResult.add(root.val);
                result.add(newResult);
            }
        }
        if (root.left != null) search(root.left, targetSum - root.val, getNewHistory(history, root.val), result);
        if (root.right != null) search(root.right, targetSum - root.val, getNewHistory(history, root.val), result);
    }

    private List<Integer> getNewHistory(List<Integer> history, int val) {
        List<Integer> newHistory = new ArrayList(history);
        newHistory.add(val);
        return newHistory;
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
