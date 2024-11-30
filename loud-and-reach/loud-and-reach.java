
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class Solution {
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int[] inDegree = new int[quiet.length];
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < richer.length; i++) {
            int vertexIn = richer[i][1];
            int vertexOut = richer[i][0];
            inDegree[vertexIn]++;
            graph.putIfAbsent(vertexOut, new LinkedList<>());
            graph.get(vertexOut).add(vertexIn);
        }

        
        List<Integer> next = new LinkedList<>();
        int[] shortest = new int[quiet.length];

        for (int i = 0; i < quiet.length; i++) {
            if (inDegree[i] == 0) next.add(i);
            shortest[i] = i;
        }

        while (next.size() > 0) {
            int i = next.removeFirst();

            for (int j : graph.getOrDefault(i, List.of())) {
                inDegree[j]--;
                if (quiet[shortest[i]] < quiet[shortest[j]]) {
                    shortest[j] = shortest[i];
                }

                if (inDegree[j] == 0) next.add(j);
            }
        }
       

        return shortest;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.loudAndRich(new int[][]{{1,0},{2,1},{3,1},{3,7},{4,3},{5,3},{6,3}}, new int[]{3,2,5,4,6,1,7,0});
        System.out.println(Arrays.toString(result));
    }

}