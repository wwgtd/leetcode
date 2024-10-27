
import java.util.HashMap;
import java.util.Map;

class Solution {
    private Map<Integer, Integer> valuesMap = new HashMap();
    private int[] sortedArray;

    public String[] findRelativeRanks(int[] score) {
        if (score.length < 1) {
            return new String[] {"Gold Medal"};
        }

        int scoreLength = score.length;

        sortedArray = new int[scoreLength];
        System.arraycopy(score, 0, sortedArray, 0, scoreLength);
        mergeSort(sortedArray, 0, scoreLength);

        String[] result = new String[scoreLength];
        for (int i = 0; i < scoreLength; i++) {
            int place = valuesMap.get(score[i]);
            result[i] = scoreToString(place);
        }

        return result;
    }

    private String scoreToString(int n) {
        if (n < 3) {
            return switch(n) {
                case 2 -> "Bronze Medal";
                case 1 -> "Silver Medal";
                case 0 -> "Gold Medal";
                default -> throw new RuntimeException();
            };
        }

        return Integer.toString(n + 1);
    }

    private void mergeSort(int[] nums, int n, int m) {
        if (n + 1 >= m) {
            return;
        }

        int pivot = (n + m) / 2;
        mergeSort(nums, n, pivot);
        mergeSort(nums, pivot, m);
        partition(nums, n, m, pivot);
    }

    private void partition(int nums[], int n, int m, int pivot) {
        int firstArrayLength = pivot - n + 1;
        int secondArrayLength = m - pivot + 1;

        int[] firstArray = new int[firstArrayLength];
        int[] secondArray = new int[secondArrayLength];
        System.arraycopy(nums, n, firstArray, 0, firstArrayLength - 1);
        System.arraycopy(nums, pivot, secondArray, 0, secondArrayLength - 1);
        firstArray[firstArrayLength - 1] = Integer.MIN_VALUE;
        secondArray[secondArrayLength - 1] = Integer.MIN_VALUE;
        
        int j = 0;
        int k = 0;
        for (int i = n; i < m; i++) {
            if (firstArray[j] < secondArray[k]) {
                valuesMap.put(secondArray[k], i);
                nums[i] = secondArray[k++];
            } else {
                valuesMap.put(firstArray[j], i);
                nums[i] = firstArray[j++];
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findRelativeRanks(new int[] {1});
    }
}
