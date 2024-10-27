
import java.util.Arrays;

class Solution {

    public int maximumScore(int a, int b, int c) {
        int[] array = new int[] {a, b, c};
        Arrays.sort(array);

        int result = 0;
        while ((array[0] > 0 && array[1] > 0) || (array[1] > 0 && array[2] > 0) || (array[0] > 0 && array[2] > 0)) {
            array[1]--;
            array[2]--;

            Arrays.sort(array);
            result++;
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.maximumScore(1, 8, 8));
    }

}
