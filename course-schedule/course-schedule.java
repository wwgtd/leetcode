
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> coursesConnections = new HashMap<>();

        int[] inDigree = new int[numCourses];

        for (int i = 0; i < prerequisites.length; i++) {
            int[] prerequest = prerequisites[i];
            inDigree[prerequest[0]]++;
            
            if (!coursesConnections.containsKey(prerequest[1])) {
                coursesConnections.put(prerequest[1], new LinkedList<>());
            }
            coursesConnections.get(prerequest[1]).add(prerequest[0]);
        }        

        List<Integer> nextIndexes = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDigree[i] == 0) nextIndexes.add(i);
        }

        int[] result = new int[numCourses];
        int idx = 0;

        while(!nextIndexes.isEmpty()) {
            int index = nextIndexes.removeFirst();
            result[idx++] = index;
            List<Integer> connections = coursesConnections.getOrDefault(index, List.of());
            for (Integer connection : connections) {
                inDigree[connection]--;
                if (inDigree[connection] == 0) {
                    nextIndexes.add(connection);
                }
            }
        }

        if (idx != numCourses) {
            return new int[]{};
        } else {
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.err.println(Arrays.toString(solution.findOrder(4, new int[][]{{1,0},{2,0},{3,1},{3,2}})));
    }
}