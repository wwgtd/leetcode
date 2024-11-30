
class Solution {

    public int[] twoSum(int[] numbers, int target) {

        for (int i = 0; i < numbers.length - 1; i++) {
            int searchable = target - numbers[i];
            int result = findInSortedArray(numbers, searchable, i + 1, numbers.length);
            if (result != -1) {
                return new int[]{i + 1, result + 1};
            }
        }

        return null;
    }

    public int findInSortedArray(int[] numbers, int searchable, int start, int end) {
        if (start >= end) {
            return -1;
        }
        int pivot = (start + end) / 2;

        if (numbers[pivot] == searchable) {
            return pivot;
        }

        if (searchable > numbers[pivot]) {
            return findInSortedArray(numbers, searchable, pivot + 1, end);
        } else {
            return findInSortedArray(numbers, searchable, start, pivot);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.twoSum(new int[]{5, 25, 75}, 100));
    }
}
