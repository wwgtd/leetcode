
import java.util.ArrayList;
import java.util.List;

public class threesum {
    public List<List<Integer>> threeSum(int[] nums) {
        quicksort(nums, 0, nums.length);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; nums[i] < 1 && i < nums.length - 2; i++) {
            int a = nums[i];
            if (i > 0 && nums[i - 1] == a) continue;
            if (a > 0) {
                break;
            }
            if (a == 0 && i + 2 < nums.length && nums[i + 1] == 0 && nums[i + 2] == 0) {
                result.add(List.of(0, 0, 0));
            }

            for (int j = nums.length - 1; nums[j] > 0 && j > 0; j--) {
                int b = nums[j];
                if (j != nums.length - 1 && nums[j + 1] == b) continue; 
                if (a + b >= 0) {
                    int diff = -a - b;
                    int k = i + 1;
                    while (nums[k] <= 0) {
                        int c = nums[k];
                        if (c == diff) {
                            result.add(List.of(a, c, b));
                            break;
                        } else if (c > diff) break;
                        k++;
                    }
                }
                if (a + b < 0) {
                    int diff = -a - b;
                    int k = j - 1;
                    while (nums[k] > 0) {
                        int c = nums[k];
                        if (c == diff) {
                            result.add(List.of(a, c, b));
                            break;
                        } else if (c < diff) break;
                        k--;
                    }
                }
            }
        }

        return result;
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
            } else {
                p2++;
            }
        }
        swap(nums, p1, m - 1);

        return p1;
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }

    public static void main(String[] args) {
        threesum solution = new threesum();
        System.out.print(solution.threeSum(new int[]{0,0,0}));
    }
}
