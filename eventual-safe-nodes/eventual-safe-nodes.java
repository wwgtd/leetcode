
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {

    public List<Integer> eventualSafeNodes(int[][] graph) {
        Map<Integer, List<Integer>> reversedGraph = new HashMap<>();
        Set<Integer> safeNodes = new HashSet<>();
        
        int[] inDigree = new int[graph.length];
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                int vertex = graph[i][j];
                reversedGraph.putIfAbsent(vertex, new ArrayList<>());
                reversedGraph.get(vertex).add(i);
                inDigree[i]++;
            }
        }

        List<Integer> next = new LinkedList<>();
        for (int i = 0; i < inDigree.length; i++) {
            if (inDigree[i] == 0) {
                next.add(i);
            }
        }

        while (!next.isEmpty()) {
            int node = next.removeFirst();
            safeNodes.add(node);
            if (reversedGraph.containsKey(node)) {
                for (int adj : reversedGraph.get(node)) {
                    inDigree[adj]--;
                    if (inDigree[adj] < 1) {
                        next.add(adj);
                    }
                }
            }
        }

        List<Integer> result = new LinkedList<>();
        result.addAll(safeNodes);
        result.sort((a,b) -> a - b);

        return result;
    }


    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.eventualSafeNodes(new int[][]{{1,2},{2,3},{5},{0},{5},{},{}}));
    }
}
