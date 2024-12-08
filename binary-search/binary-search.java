
class Solution {

    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length);
    }

    private int search(int[] nums, int target, int p, int q) {
        if (p >= q) {
            return -1;
        }

        int pivot = p + (q - p) / 2;

        if (nums[pivot] == target) return pivot;

        if (nums[pivot] > target) {
            return search(nums, target, p, pivot);
        }

        return search(nums, target, pivot + 1, q);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.search(new int[]{-1, 0, 3, 5, 9, 12}, 2);

        System.out.println(result);
    }
}
