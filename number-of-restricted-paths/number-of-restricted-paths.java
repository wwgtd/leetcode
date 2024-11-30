
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Solution {

    public int countRestrictedPaths(int n, int[][] edges) {
        int[][] reverseEdges = new int[edges.length][];
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            int u = edge[0] < edge[1] ? edge[0] : edge[1];
            int v = edge[0] > edge[1] ? edge[0] : edge[1];
            edge[0] = u - 1;
            edge[1] = v - 1;
        }

        Map<Integer, Set<int[]>> reversedGraph = buildReversedGraph(edges);
        Map<Integer, Set<int[]>> graph = buildGraph(edges);
        Map<Integer, Set<int[]>> twoWayGraph = buildTwoWayGraph(edges);

        int[] weights = dijkstra(n, twoWayGraph);

        return sumVertices(graph, weights, n - 1);
    }

    private int sumVertices(Map<Integer, Set<int[]>> graph, int[] weights, int finish) {
        Map<Integer, Set<Integer>> graphFiltered = new HashMap<>();
        for (var entry : graph.entrySet()) {
            graphFiltered.put(entry.getKey(), entry.getValue().stream().map(vertexData -> vertexData[0]).collect(Collectors.toSet()));
        }

        int[] counter = new int[graph.size()];
        counter[0] = 1;

        boolean[] visited = new boolean[graph.size()];

        List<Integer> next = new ArrayList<>(List.of(0));
        while (next.size() > 0) {
            int vertex = next.removeFirst();

            for (var adj : graphFiltered.get(vertex)) {
                if (weights[vertex] > weights[adj]) {
                    if (!visited[adj]) {
                        next.add(adj);
                        visited[adj] = true;
                    }
                }
                counter[adj] += counter[vertex];
            }
        }

        return counter[finish];
    }

    // public static void main(String[] args) {
    //     Solution solution = new Solution();
    //     int answer = solution.countRestrictedPaths(7, new int[][]{{1, 3, 1}, {4, 1, 2}, {7, 3, 4}, {2, 5, 3}, {5, 6, 1}, {6, 7, 2}, {7, 5, 3}, {2, 6, 4}});
    //     System.out.println(answer);
    // }

    private int[] dijkstra(int start, Map<Integer, Set<int[]>> graph) {
        int[] weights = new int[graph.size()];

        for (int i = 0; i < graph.size(); i++) {
            if (i != start - 1) {
                weights[i] = Integer.MAX_VALUE;
            }
        }
        MinHeap heap = new MinHeap(weights);

        while (heap.size() > 0) {
            int[] min = heap.extractMin();
            int vertex = min[0];

            Set<int[]> adjs = graph.getOrDefault(vertex, Collections.emptySet());

            for (int[] adj : adjs) {
                int adjVertex = adj[0];
                int adjWeight = adj[1];

                int weight = adjWeight + weights[vertex];
                if (weights[adjVertex] > weight) {
                    weights[adjVertex] = weight;
                    heap.decreaseKey(adjVertex, weight);
                }
            }
        }

        return weights;
    }

    private Map<Integer, Set<int[]>> buildGraph(int[][] edges) {
        Map<Integer, Set<int[]>> graph = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            graph.putIfAbsent(edges[i][0], new HashSet<>());
            graph.putIfAbsent(edges[i][1], new HashSet<>());
            graph.get(edges[i][0]).add(new int[]{edges[i][1], edges[i][2]});
        }

        return graph;
    }

    private Map<Integer, Set<int[]>> buildTwoWayGraph(int[][] edges) {
        Map<Integer, Set<int[]>> graph = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            graph.putIfAbsent(edges[i][0], new HashSet<>());
            graph.putIfAbsent(edges[i][1], new HashSet<>());
            graph.get(edges[i][0]).add(new int[]{edges[i][1], edges[i][2]});
            graph.get(edges[i][1]).add(new int[]{edges[i][0], edges[i][2]});
        }

        return graph;
    }


    private Map<Integer, Set<int[]>> buildReversedGraph(int[][] edges) {
        Map<Integer, Set<int[]>> graph = new HashMap<>();

        for (int[] edge : edges) {
            graph.putIfAbsent(edge[1], new HashSet<>());
            graph.putIfAbsent(edge[0], new HashSet<>());
            graph.get(edge[1]).add(new int[]{edge[0], edge[2]});
        }

        return graph;
    }

    private final class MinHeap {

        private final List<int[]> heap = new ArrayList<>();

        public MinHeap(int[] weights) {
            for (int i = 0; i < weights.length; i++) {
                insert(i, weights[i]);
            }
        }

        public void insert(int key, int value) {
            heap.add(new int[]{key, value});

            bubbleUp(heap.size() - 1);
        }

        public int size() {
            return heap.size();
        }

        public int[] extractMin() {
            int[] min = heap.get(0);

            int[] last = heap.removeLast();
            if (heap.size() > 0) {
                heap.set(0, last);
                bubbleDown(0);
            }
            return min;
        }

        public void decreaseKey(int key, int newValue) {
            for (int i = 0; i < heap.size(); i++) {
                int[] record = heap.get(i);
                if (record[0] == key) {
                    record[1] = newValue;
                    bubbleUp(i);
                    break;
                }
            }
        }

        private void bubbleUp(int key) {
            int elemIndex = key;
            int parent = (elemIndex - 1) / 2;

            while (parent >= 0) {
                if (heap.get(elemIndex)[1] < heap.get(parent)[1]) {
                    swap(parent, elemIndex);
                    elemIndex = parent;
                    parent = (parent - 1) / 2;
                } else {
                    break;
                }
            }
        }

        private void bubbleDown(int index) {
            int elemIndex = index;
            int leftChildIndex = (elemIndex * 2) + 1;
            int rightChildIndex = leftChildIndex + 1;

            while (leftChildIndex < heap.size()) {
                int smallest = elemIndex;
                if (heap.get(leftChildIndex)[1] < heap.get(smallest)[1]) {
                    smallest = leftChildIndex;
                }

                if (heap.size() > rightChildIndex && heap.get(rightChildIndex)[1] < heap.get(smallest)[1]) {
                    smallest = rightChildIndex;
                }

                if (smallest != elemIndex) {
                    swap(smallest, elemIndex);
                    elemIndex = smallest;
                    leftChildIndex = (elemIndex * 2) + 1;
                    rightChildIndex = leftChildIndex + 1;
                } else {
                    break;
                }
            }
        }

        private void swap(int i, int j) {
            int[] temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}
