
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        quicksort(nums, 0, nums.length);
        List<List<Integer>> listResult = new LinkedList();


        int positivePos = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                positivePos = i;
                break;
            }
        }

        if (positivePos == -1) {
            listResult.add(List.of(0, 0, 0));
        }

        System.out.println(Arrays.toString(nums));

        Set<String> result = new HashSet();

        int length = nums.length;

        for (int i = 0; i < positivePos; i++) {
            int negative = nums[i];
            int p = positivePos;

            while (p < length) {
                int q = p;

                System.out.println(String.format("i=%d, negative=%d, p=%d, q=%d", i, negative, p, q));


                while (q < length && Math.abs(negative) > nums[q]) {
                    System.out.println(String.format("testing %d against %d", negative, nums[q]));
                    q++;
                }


                if (q < length) {
                    int lastVal = -negative - nums[q];

                    System.out.println(String.format("negative: %d, nums[q]: %d lastVal: %d, j=%d, p=%d", negative, nums[q], lastVal, i, p));

                    for (int j = i + 1; j < p; j++) {
                        if (lastVal == nums[j]) {
                            System.out.println(String.format("found! %d,%d,%d", negative, nums[j], nums[q]));
                            result.add(String.format("%d,%d,%d", negative, nums[j], nums[q]));
                            p = q;
                            break;
                        }
                    }
                };
                p++;

                System.out.println('\n');
            }
        }

        for (String s : result) {
            String[] splitted = s.split(",");
            List<Integer> triplet = new LinkedList();
            for (String t : splitted) {
                triplet.add(Integer.parseInt(t));
            }
            listResult.add(triplet);
        }

        return listResult;
    }

    private void quicksort(int[] nums, int n, int m) {
        if (n + 1 >= m) {
            return;
        }

        int pivot = partition(nums, n, m);
        quicksort(nums, n, pivot);
        quicksort(nums, pivot + 1, m);
    }

    private int partition(int[] nums, int n, int m) {
        int pivot = nums[m - 1];
        int p1 = n;
        int p2 = n;

        while (p2 < m - 1) {
            if (nums[p2] < pivot) {
                swap(nums, p1, p2);
                p1++;
                p2++;
            } else
                p2++;
        }
        swap(nums, p1, m - 1);

        return p1;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}