
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Solution {

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> counter = new HashMap<>();
        Map<Character, Integer> banCounter = new HashMap<>();

        for (int i = 0; i < tasks.length; i++) {
            if (!counter.containsKey(tasks[i])) {
                counter.put(tasks[i], 1);
            } else {
                counter.put(tasks[i], counter.get(tasks[i]) + 1);
            }
        }

        MaxHeap heap = new MaxHeap(counter.entrySet());

        int interationsAmount = 0;

        while (heap.getSize() > 0) {
            Iterator<Entry<Character, Integer>> it = banCounter.entrySet().iterator();
            while (it.hasNext()) {
                Entry<Character, Integer> entry = it.next();
                int value = entry.getValue();
                if (value > 1) {
                    entry.setValue(value - 1);
                } else {
                    it.remove();
                }
            }

            List<Entry<Character, Integer>> toReturnBack = new ArrayList<>();

            Character next = null;

            while (next == null && heap.getSize() > 0) {
                Entry<Character, Integer> entry = heap.extractMax();
                if (banCounter.containsKey(entry.getKey())) {
                    toReturnBack.add(entry);
                } else {
                    next = entry.getKey();
                    if (entry.getValue() > 1) {
                        entry.setValue(entry.getValue() - 1);
                        heap.insert(entry);
                    }
                    banCounter.put(next, n + 1);
                }
            }

            interationsAmount++;

            toReturnBack.forEach(heap::insert);
        }

        return interationsAmount;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        char[] tasks = new char[]{'A', 'A', 'A', 'B', 'B', 'B'};
        System.err.println(solution.leastInterval(tasks, 3));
    }

    private static class MaxHeap {

        private final List<Entry<Character, Integer>> heap = new ArrayList();

        public MaxHeap(Set<Entry<Character, Integer>> counted) {
            for (Entry<Character, Integer> entry : counted) {
                insert(entry);
            }
        }

        public void log() {
            System.out.println(heap);
        }

        public int getSize() {
            return heap.size();
        }

        public Entry<Character, Integer> extractMax() {
            if (heap.size() == 1) {
                return heap.remove(0);
            }

            if (heap.size() == 2) {
                swap(0, 1);
                return heap.remove(1);
            }

            Entry<Character, Integer> max = heap.get(0);

            Entry<Character, Integer> lastNode = heap.get(heap.size() - 1);
            heap.remove(heap.size() - 1);
            heap.set(0, lastNode);

            int parent = 0;

            while (parent * 2 + 1 < heap.size()) {
                int leftChild = parent * 2 + 1;
                int rightChild = leftChild + 1;
                if (heap.size() > rightChild) {
                    if (heap.get(parent).getValue() < heap.get(leftChild).getValue() && heap.get(leftChild).getValue() > heap.get(rightChild).getValue()) {
                        swap(leftChild, parent);
                        parent = leftChild;
                    } else if (heap.get(parent).getValue() < heap.get(rightChild).getValue()) {
                        swap(rightChild, parent);
                        parent = rightChild;
                    } else {
                        break;
                    }
                } else {
                    if (heap.get(parent).getValue() < heap.get(leftChild).getValue()) {
                        swap(leftChild, parent);
                        parent = leftChild;
                    } else {
                        break;
                    }
                }
            }

            return max;
        }

        public void insert(Entry<Character, Integer> num) {
            heap.add(num);

            bubbleUp();
        }

        private void bubbleUp() {
            int bubbledIndex = heap.size() - 1;

            int parent = getParentIndex(bubbledIndex);
            int i = bubbledIndex;

            while (parent >= 0 && heap.get(parent).getValue() < heap.get(i).getValue()) {
                swap(parent, i);
                i = parent;
                parent = getParentIndex(parent);
            }
        }

        private int getParentIndex(int index) {
            return index % 2 == 0 ? index / 2 - 1 : index / 2;
        }

        private void swap(int i, int j) {
            Entry<Character, Integer> temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}
