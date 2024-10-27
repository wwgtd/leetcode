
import java.util.Arrays;

class Solution {

    public int[] kWeakestRows(int[][] mat, int k) {
        int length = mat[0].length;
        int arrLength = mat.length;
        int[] result = new int[k];
        int left = arrLength;

        int[][] copy = new int[arrLength][length + 1];

        for (int i = 0; i < arrLength; i++) {
            System.arraycopy(mat[i], 0, copy[i], 1, length);
            copy[i][0] = 1;
        }

        for (int i = length - 1; i >= 0; i--) {
            if (left == 0) {
                break;
            }
            for (int j = arrLength - 1; j >= 0; j--) {
                if (copy[j] != null && copy[j][i] == 1) {
                    copy[j] = null;
                    if (left-- <= k) {
                        result[left] = j;
                        copy[j] = null;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[][] mat = {{1, 0}, {0, 0}, {1, 0}};
        System.err.println(Arrays.toString(sol.kWeakestRows(mat, 2)));
    }
}
