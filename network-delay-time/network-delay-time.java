
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

class Solution {
    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Integer[]>> graph = buildGraph(times);

        int[] weights = new int[n];
        Set<Integer> notVisited = new HashSet();

        for (int i = 0; i < n; i++) {
            if (i != k - 1) {
                notVisited.add(i);
                weights[i] = Integer.MAX_VALUE;
            }
        }
        MinHeap heap = new MinHeap(weights);

        int max = -1;

        while (heap.size() > 0) {
            int[] min = heap.extractMin();
            int vertex = min[0];
            max = max < weights[vertex] ? weights[vertex] : max;
            
            
            List<Integer[]> adjs = graph.getOrDefault(vertex, Collections.emptyList());

            for (Integer[] adj : adjs) {
                int adjVertex = adj[0];
                int adjWeight = adj[1];

                int weight = adjWeight + weights[vertex];
                if (weights[adjVertex] > weight) {
                    weights[adjVertex] = weight;
                    heap.decreaseKey(adjVertex, weight);
                    notVisited.remove(adjVertex);
                }
            }
        }
        
        return notVisited.isEmpty() ? max : -1;
    }


    private Map<Integer, List<Integer[]>> buildGraph(int[][] edges) {
        Map<Integer, List<Integer[]>> graph = new HashMap<>();

        for (int i = 0 ; i < edges.length; i++) {
            graph.putIfAbsent(edges[i][0] - 1, new ArrayList<>());
            graph.get(edges[i][0] - 1).add(new Integer[]{edges[i][1] - 1, edges[i][2]});
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