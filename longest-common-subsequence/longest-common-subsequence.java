class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        String first = 'x' + text1;
        String second = 'y' + text2;

        int[][] lcsMatrix = new int[first.length()][];
        for (int i = 0; i < lcsMatrix.length; i++) {
            lcsMatrix[i] = new int[second.length()];
            lcsMatrix[i][0] = 0;
        }

        for (int i = 0; i < second.length(); i++) {
            lcsMatrix[0][i] = 0;
        }

        for (int x = 1; x < first.length(); x++) {
            for (int y = 1; y < second.length(); y++) {
                if (first.charAt(x) == second.charAt(y)) {
                    lcsMatrix[x][y] = lcsMatrix[x - 1][y - 1] + 1;
                } else {
                    lcsMatrix[x][y] = Math.max(lcsMatrix[x - 1][y], lcsMatrix[x][y - 1]);
                }
            }
        }

        return lcsMatrix[first.length() - 1][second.length() - 1];
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.longestCommonSubsequence("abcde", "ace"));
    }
}