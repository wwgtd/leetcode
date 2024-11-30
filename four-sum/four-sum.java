
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

class Solution {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) {
            return Collections.emptyList();
        }

        mergesort(nums, 0, nums.length);

        List<List<Integer>> result = new LinkedList<>();

        int p1 = 0;
        int p2 = nums.length - 1;

        calc(p1, p2, nums, target, result);

        return result;
    }

    private void calc(int p1, int p2, int[] nums, int target, List<List<Integer>> result) {


        int p3 = p1 + 1;
        int p4 = p2 - 1;

        int diff = 0;
        while (p4 > p3 && p3 >= 0 && p4 >= 0) {
            if (nums[p1] + nums[p2] + nums[p3] + nums[p4] == target) {
                result.add(List.of(nums[p1], nums[p3], nums[p4], nums[p2]));
                p4 = down(nums, p4);
                p3 = up(nums, p3);
                continue;
            }

            diff = nums[p1] + nums[p2] + nums[p3] + nums[p4] - target;
            if (diff > 0) {
                p4 = down(nums, p4);
            } else {
                p3 = up(nums, p3);
            }
        }

        if (diff == 0) {
            calc(up(nums, p1), down(nums, p2), nums, target, result);
        } else {
            calc(up(nums, p1), p2, nums, target, result);
            calc(p1, down(nums, p2), nums, target, result);
        }
    }

    private int up(int[] nums, int idx) {
        int curval = nums[idx];
        idx++;
        if (idx < nums.length) {
            if (curval != nums[idx]) {
                return idx;
            } else {
                return up(nums, idx);
            }
        }

        return -1;
    }

    private int down(int[] nums, int idx) {
        int curval = nums[idx];
        idx--;
        if (idx >= 0) {
            if (curval != nums[idx]) {
                return idx;
            } else {
                return down(nums, idx);
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] array = new int[]{1, 0, -1, 0, -2, 2};
        int[] array2 = new int[]{-2,-1,-1,1,1,2,2};
        // System.out.println(solution.fourSum(array, 0));
        System.out.println(solution.fourSum(array2, 0));
    }

    private void mergesort(int[] nums, int start, int end) {
        int pivot = (start + end) / 2;
        if (start >= pivot) {
            return;
        }

        mergesort(nums, start, pivot);
        mergesort(nums, pivot, end);

        partition(nums, start, end, pivot);
    }

    private void partition(int[] nums, int start, int end, int pivot) {
        int[] firstArray = new int[pivot - start + 1];
        int[] secondArray = new int[end - pivot + 1];

        for (int i = start; i < pivot; i++) {
            firstArray[i - start] = nums[i];
        }

        for (int i = pivot; i < end; i++) {
            secondArray[i - pivot] = nums[i];
        }

        firstArray[firstArray.length - 1] = Integer.MAX_VALUE;
        secondArray[secondArray.length - 1] = Integer.MAX_VALUE;

        int p1 = 0, p2 = 0;
        for (int i = start; i < end; i++) {
            if (firstArray[p1] > secondArray[p2]) {
                nums[i] = secondArray[p2++];
            } else {
                nums[i] = firstArray[p1++];
            }
        }
    }
}
