
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;

class Solution {

    private final Comparator<Entry<String, Double>> byValueComparator = (a, b) -> {
        return Double.compare(a.getValue(), b.getValue());
    };

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, List<Entry<String, Double>>> graph = new HashMap<>();

        for (int i = 0; i < equations.size(); i++) {
            String firstVertex = equations.get(i).getFirst();
            String secondVertex = equations.get(i).getLast();
            graph.putIfAbsent(firstVertex, new ArrayList<>());
            graph.putIfAbsent(secondVertex, new ArrayList<>());

            double value = values[i];
            graph.get(firstVertex).add(new SimpleEntry<>(secondVertex, value));
            graph.get(secondVertex).add(new SimpleEntry<>(firstVertex, 1 / value));
        }

        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            List<String> query = queries.get(i);
            if (!graph.containsKey(query.getFirst()) || !graph.containsKey(query.getLast())) {
                result[i] = -1;
            } else if (query.getFirst().equals(query.getLast())) {
                result[i] = 1;
            } else {
                result[i] = computeDistance(query.getFirst(), query.getLast(), graph);
            }
        }
        return result;
    }

    private boolean isReachable(String source, String dest, Map<String, List<Entry<String, Double>>> graph) {
        Set<String> visited = new HashSet<>();
        List<String> nextList = new LinkedList<>(List.of(source));

        while (nextList.size() > 0) {
            String next = nextList.removeFirst();
            visited.add(next);
            for (var adj : graph.get(next)) {
                String key = adj.getKey();
                if (key.equals(dest)) return true;
                if (!visited.contains(key)) {
                    nextList.add(key);
                }
            }
        }

        return false;
    }

    private double computeDistance(String source, String dest, Map<String, List<Entry<String, Double>>> graph) {
        if (!isReachable(source, dest, graph)) {
            return -1.0;
        }

        Map<String, Entry<String, Double>> entries = new HashMap<>();
        PriorityQueue<Entry<String, Double>> queue = new PriorityQueue<>(byValueComparator);
        for (var entry : graph.entrySet()) {
            String key = entry.getKey();
            Entry<String, Double> vertex = new SimpleEntry<>(key, key.equals(source) ? 1 : Double.MAX_VALUE);
            queue.add(vertex);
            entries.put(key, vertex);
        }

        while (!queue.isEmpty()) {
            Entry<String, Double> vertex = queue.poll();
            for (var adj : graph.get(vertex.getKey())) {
                var entry = entries.get(adj.getKey());
                double value = entry.getValue();
                if (value > adj.getValue() * vertex.getValue()) {
                    queue.remove(entry);
                    entry.setValue(adj.getValue() * vertex.getValue());
                    queue.offer(entry);
                }
            }
        }

        double result = entries.get(dest).getValue();
        return result == Double.MAX_VALUE ? -1.0 : result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        List<List<String>> equations = new ArrayList();
        equations.add(List.of("a", "b"));
        equations.add(List.of("c", "d"));
        equations.add(List.of("e", "f"));
        equations.add(List.of("g", "h"));

        double[] values = new double[] {4.5,2.3,8.9,0.44};

        List<List<String>> queries = new ArrayList();
        queries.add(List.of("a", "c"));
        queries.add(List.of("d", "f"));
        queries.add(List.of("h", "e"));
        queries.add(List.of("b", "e"));
        queries.add(List.of("d", "h"));
        queries.add(List.of("g", "f"));
        queries.add(List.of("c", "g"));

        
        System.out.println(Arrays.toString(solution.calcEquation(equations, values, queries)));
    }
}
