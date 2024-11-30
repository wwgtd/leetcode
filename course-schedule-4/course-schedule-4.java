
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        int[] inDigree = new int[numCourses];
        
        Map<Integer, Set<Integer>> prerequisitesReversed = new HashMap<>();

        for (int i = 0; i < prerequisites.length; i++) {
            int from = prerequisites[i][1];
            int to = prerequisites[i][0];
            prerequisitesReversed.putIfAbsent(from, new HashSet<>());
            prerequisitesReversed.get(from).add(to);
            inDigree[to]++;
        }

        List<Integer> next = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDigree[i] == 0) {
                next.add(i);
            }
        }


        Map<Integer, Set<Integer>> reachableCourses = new HashMap<>();
        while (next.size() > 0) {
            int vertex = next.removeFirst();
            for (int adj : prerequisitesReversed.getOrDefault(vertex, Collections.emptySet())) {
                inDigree[adj]--;
                if (inDigree[adj] == 0) next.add(adj);
                reachableCourses.putIfAbsent(adj, new HashSet<>());
                Set<Integer> reachable = reachableCourses.get(adj);
                reachable.add(vertex);
                reachable.addAll(reachableCourses.getOrDefault(vertex, Collections.emptySet()));
            }
        }


        List<Boolean> result = new LinkedList<>();
        for (int[] query : queries) {
            result.add(reachableCourses.getOrDefault(query[0], Collections.emptySet()).contains(query[1]));
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.checkIfPrerequisite(3, new int[][]{{1,2},{1,0},{2,0}}, new int[][]{{1,0},{1,2}}));
    }
}