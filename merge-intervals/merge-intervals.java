
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList();
        if (intervals.length < 2) {
            return intervals;
        }

        sortIntervals(intervals, 0, intervals.length);

        result.add(intervals[0]);
        for (int i = 1; i < intervals.length; i++) {
            int[] firstInterval = intervals[i];
            int[] lastInterval = result.getLast();

            int[] sorted = new int[4];
            
            int p1 = 0, p2 = 0;
            for (int j = 0; j < 4; j++) {
                if (p1 == 2) {
                    sorted[j] = lastInterval[p2++];
                } else if (p2 == 2) {
                    sorted[j] = firstInterval[p1++];
                } else if (firstInterval[p1] > lastInterval[p2]) {
                    sorted[j] = lastInterval[p2];
                    p2++;
                } else {
                    sorted[j] = firstInterval[p1];
                    p1++;
                }
            }

            if (lastInterval[1] == sorted[1] && sorted[1] != sorted[2]) {
                result.add(firstInterval);
            } else {
                result.removeLast();
                result.add(new int[]{ sorted[0], sorted[3] });
            }
        }

        return result.toArray(new int[result.size()][2]);
    }

    private void sortIntervals(int[][] intervals, int start, int end) {
        if (end - start < 2) return;

        int pivot = end - 1;
        int pivotValue = intervals[pivot][0];

        int p1 = start, p2 = start;
        while (p2 < pivot) {
            if (intervals[p2][0] < pivotValue) {
                swap(intervals, p1, p2);
                p1++;
                p2++;
            } else {
                p2++;
            }
        }

        swap(intervals, p1, pivot);

        sortIntervals(intervals, start, p1);
        sortIntervals(intervals, p1 + 1, end);
    }

    private void swap(int[][] intervals, int firstIdx, int secondIdx) {
        int[] temp = intervals[firstIdx];
        intervals[firstIdx] = intervals[secondIdx];
        intervals[secondIdx] = temp;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] result = solution.merge(new int[][]{{2,3},{5,5},{2,2},{3,4},{3,4}});
        for (int i = 0; i < result.length; i++) {
           System.out.println(Arrays.toString(result[i]));
        }
    }
}