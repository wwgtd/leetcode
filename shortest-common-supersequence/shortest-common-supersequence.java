
import java.util.Arrays;

class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        String first = 'x' + str1;
        String second = 'y' + str2;

        int[][] lcsMatrix = new int[first.length()][];
        for (int i = 0; i < lcsMatrix.length; i++) {
            lcsMatrix[i] = new int[second.length()];
            lcsMatrix[i][0] = 0;
        }

        for (int i = 0; i < second.length(); i++) {
            lcsMatrix[0][i] = 0;
        }

        System.out.println(Arrays.toString(lcsMatrix[0]));
        for (int x = 1; x < first.length(); x++) {
            for (int y = 1; y < second.length(); y++) {
                if (first.charAt(x) == second.charAt(y)) {
                    lcsMatrix[x][y] = lcsMatrix[x - 1][y - 1] + 1;
                } else {
                    lcsMatrix[x][y] = Math.max(lcsMatrix[x - 1][y], lcsMatrix[x][y - 1]);
                }
            }
            System.out.println(Arrays.toString(lcsMatrix[x]));
        }

        return buildSupersequence(first, second, lcsMatrix, first.length() - 1, second.length() - 1);
    }

    private String buildSupersequence(String str1, String str2, int[][] matrix, int i, int j) {
        if (i == 0 || j == 0) {
            if (i == 0 && j == 0) return "";
            if (i == 0) return buildSupersequence(str1, str2, matrix, i, j - 1) + str2.charAt(j);
            return buildSupersequence(str1, str2, matrix, i - 1, j) + str1.charAt(i);
        }

        if (str1.charAt(i) == str2.charAt(j)) {
            return buildSupersequence(str1, str2, matrix, i-1, j-1) + str1.charAt(i); 
        } else if (matrix[i-1][j] > matrix[i][j-1]) {
            return buildSupersequence(str1, str2, matrix, i-1, j) + str1.charAt(i);
        }

        return buildSupersequence(str1, str2, matrix, i, j-1) + str2.charAt(j);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.shortestCommonSupersequence("groot", "brute"));
    }
}