
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Solution {

    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        int[] result = new int[n];
        Map<Integer, List<Integer>> edgesMap = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            edgesMap.putIfAbsent(edge[0], new LinkedList<>());
            edgesMap.putIfAbsent(edge[1], new LinkedList<>());
            edgesMap.get(edge[0]).add(edge[1]);
            edgesMap.get(edge[1]).add(edge[0]);
        }

        LinkedHashMap<Integer, List<Integer>> verticesWithMostEdges = sortVertices(edgesMap);

        List<Integer> verticesWithMinDepth = new LinkedList<>();
        verticesWithMostEdges.putIfAbsent(0, List.of());
        int currentMinDepth = Integer.MAX_VALUE;

        for (Integer i : verticesWithMostEdges.keySet()) {
            int depth = getMaxDepth(i, edgesMap);
            if (depth < currentMinDepth) {
                currentMinDepth = depth;
                verticesWithMinDepth.clear();
                verticesWithMinDepth.add(i);
            } else if (depth == currentMinDepth) {
                verticesWithMinDepth.add(i);
            }

            if (verticesWithMinDepth.size() > 1) {
                break;
            }
        }

        return verticesWithMinDepth;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.err.println(solution.findMinHeightTrees(1, new int[][]{}));
        System.err.println(solution.findMinHeightTrees(6, new int[][]{{3, 0}, {3, 1}, {3, 2}, {3, 4}, {5, 4}}));
    }

    private int getMaxDepth(int startVertex, Map<Integer, List<Integer>> edges) {
        int vertecesSize = edges.size();

        int[] shortest = new int[vertecesSize];
        Integer[] pred = new Integer[vertecesSize];
        for (int i = 0; i < vertecesSize; i++) {
            shortest[i] = i == startVertex ? 0 : Integer.MAX_VALUE;
        }

        List<Integer> next = new LinkedList(Arrays.asList(startVertex));

        int result = 0;
        while (!next.isEmpty()) {
            int vertex = next.removeFirst();
            List<Integer> paths = edges.getOrDefault(vertex, List.of());

            for (Integer path : paths) {
                if (path != startVertex && shortest[path] == Integer.MAX_VALUE) {
                    pred[path] = vertex;
                    shortest[path] = shortest[vertex] + 1;
                    result = shortest[path];
                    next.add(path);
                }
            }
        }

        return result;
    }

    private LinkedHashMap<Integer, List<Integer>> sortVertices(Map<Integer, List<Integer>> map) {
        return map.entrySet().stream().sorted((a, b) -> b.getValue().size() - a.getValue().size()).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
    }
}
