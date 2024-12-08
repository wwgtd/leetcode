
class Solution {
    // public int maxArea(int[] height) {
    //     if (height.length == 0) return 0;

    //     int maxArea = 0;
    //     for (int p1 = 0; p1 < height.length; p1++) {
    //         for (int p2 = 0; p2 < height.length; p2++) {
    //             int newMaxArea = (p2 - p1) * Math.min(height[p2], height[p1]);
    //             if (newMaxArea > maxArea) maxArea = newMaxArea;
    //         }
    //     }
    //     return maxArea;
    // }


    public int maxArea(int[] height) {
        if (height.length < 2) {
            return 0;
        }

        int maxArea = 0;

        int p1 = 0, p2 = height.length - 1;

        while (p1 < p2) {
            int newMaxArea = (p2-p1) * Math.min(height[p2], height[p1]);
            if (newMaxArea > maxArea) maxArea = newMaxArea;

            if (height[p2] > height[p1]) {
                p1++;
            } else {
                p2--;
            }
        }


        return maxArea;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int result = solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});

        System.out.println(result);
    }
}
