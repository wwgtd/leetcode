class Solution {
    public int findMin(int[] nums) {
        boolean isRotated = nums[0] > nums[nums.length - 1];

        if (!isRotated) return nums[0];

        
        int diffVal = nums[0];

        int p1 = 1, p2 = nums.length;

        while (p1 < p2) {
            int pivot = p1 + (p2 - p1) / 2;
            if (nums[pivot] > diffVal) {
                p1 = pivot;
            } else if (nums[pivot] < diffVal) {
                if (nums[pivot - 1] > nums[pivot]) {
                    return nums[pivot];
                } else {
                    p2 = pivot;
                }
            }
        }

        return 0;
    }
}