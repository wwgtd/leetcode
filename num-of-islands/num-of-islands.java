
import java.util.Arrays;

class Solution {

    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        int capacity = grid[0].length;
        boolean[][] checked = new boolean[grid.length][];
        for (int i = 0; i < checked.length; i++) {
            checked[i] = new boolean[capacity];
            Arrays.fill(checked[i], Boolean.FALSE);
        }

        int counter = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (checked[i][j]) {
                    continue;
                }

                checked[i][j] = true;
                if (grid[i][j] == '1') {
                    counter++;
                    markChecked(checked, grid, i, j, capacity);
                }
            }
        }

        return counter;
    }

    private void markChecked(boolean[][] checked, char[][] grid, int i, int j, int capacity) {
        if (i + 1 < checked.length && !checked[i + 1][j]) {
            checked[i + 1][j] = true;
            if (grid[i + 1][j] == '1') {
                markChecked(checked, grid, i + 1, j, capacity);
            }
        }

        if (j + 1 < capacity && !checked[i][j + 1]) {
            checked[i][j + 1] = true;
            if (grid[i][j + 1] == '1') {
                markChecked(checked, grid, i, j + 1, capacity);
            }
        }

        if (j - 1 >= 0 && !checked[i][j - 1]) {
            checked[i][j - 1] = true;
            if (grid[i][j - 1] == '1') {
                markChecked(checked, grid, i, j - 1, capacity);
            }
        }

        if (i - 1 >= 0 && !checked[i - 1][j]) {
            checked[i - 1][j] = true;
            if (grid[i - 1][j] == '1') {
                markChecked(checked, grid, i - 1, j, capacity);
            }
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int result = solution.numIslands(new char[][]{{'1', '1', '0', '0', '0'}, {'1', '1', '0', '0', '0'}, {'0', '0', '1', '0', '0'}, {'0', '0', '0', '1', '1'}});

        System.out.println(result);
    }
}
