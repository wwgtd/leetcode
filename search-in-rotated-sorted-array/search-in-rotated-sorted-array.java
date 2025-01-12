class Solution {
    public boolean search(int[] nums, int target) {
        boolean isRotated = nums[0] > nums[nums.length - 1];
        boolean isMirrored = nums[0] == nums[nums.length - 1];

        if (isMirrored) {
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] == target) return true;
            }
            return false;
        }

        if (!isRotated) {
            return findIndexInRow(nums, target, 0, nums.length) != -1;
        }

        int reversionIndex = findReversionIndex(nums);

        if (nums[0] > target) {
            return findIndexInRow(nums, target, reversionIndex, nums.length) != -1;
        }

        return findIndexInRow(nums, target, 0, reversionIndex) != -1;
    }

    private int findReversionIndex(int[] nums) {
        int diffVal = nums[0];

        int p1 = 1, p2 = nums.length;

        while (p1 < p2) {
            int pivot = p1 + (p2 - p1) / 2;
            if (nums[pivot] >= diffVal) {
                p1 = pivot;
            } else if (nums[pivot] < diffVal) {
                if (nums[pivot - 1] > nums[pivot]) {
                    return pivot;
                } else {
                    p2 = pivot;
                }
            }
        }

        return 0;
    }


    private int findIndexInRow(int[] row, int target, int start, int end) {
        if (start >= end) return -1;

        int pivot = start + (end - start) / 2;

        if (row[pivot] == target) {
            return pivot;
        }

        if (row[pivot] > target) {
            return findIndexInRow(row, target, start, pivot);
        }

        return findIndexInRow(row, target, pivot + 1, end);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int result = solution.findReversionIndex(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,2,1,1,1,1,1});
        System.out.println(result);

    }
}