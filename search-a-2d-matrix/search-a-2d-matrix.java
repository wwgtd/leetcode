class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int n = matrix.length, m = matrix[0].length;

        int p1 = 0, p2 = n;

        while (p1 < p2) {
            int pivot = p1 + (p2 - p1) / 2;

            int[] row = matrix[pivot];
            if (target >= row[0]) {
                if (target <= row[m - 1]) {
                    return isPresentInRow(row, target, 0, m);
                } else {
                    p1 = pivot + 1;
                }
            } else {
                p2 = pivot;
            }
        }

        return false;
    }

    private boolean isPresentInRow(int[] row, int target, int start, int end) {
        if (start >= end) return false;

        int pivot = start + (end - start) / 2;

        if (row[pivot] == target) {
            return true;
        }

        if (row[pivot] > target) {
            return isPresentInRow(row, target, start, pivot);
        }

        return isPresentInRow(row, target, pivot + 1, end);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean result = solution.searchMatrix(new int[][] {{1,3,5,7},{10,11,16,20},{23,30,34,60}}, 3);
        System.out.println(result);
    }
}