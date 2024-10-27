
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class Solution {

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> counter = new HashMap<>();
        

        for (int i = 0; i < nums.length; i++) {
            if (!counter.containsKey(nums[i])) {
                counter.put(nums[i], 1);
            } else {
                counter.put(nums[i], counter.get(nums[i]) + 1);
            }
        }

        MaxHeap heap = new MaxHeap(counter.entrySet());

        int[] max = new int[k];
        while (k-- > 0) {
            max[k] = heap.extractMax().getKey();
        }

        return max;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(Arrays.toString(solution.topKFrequent(new int[]{1,1,1,2,2,3}, 2)));
    }

    private static class MaxHeap {

        private final List<Entry<Integer,Integer>> heap = new ArrayList();

        public MaxHeap(Set<Entry<Integer,Integer>> counted) {
            for (Entry<Integer,Integer> entry : counted) {
                insert(entry);
            }
        }

        public void log() {
            System.out.println(heap);
        }

        public int getSize() {
            return heap.size();
        }

        public Entry<Integer,Integer> extractMax() {
            if (heap.size() == 1) {
                return heap.remove(0);
            }

            if (heap.size() == 2) {
                swap(0, 1);
                return heap.remove(1);
            }

            Entry<Integer,Integer> max = heap.get(0);

            Entry<Integer,Integer> lastNode = heap.get(heap.size() - 1);
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

        public void insert(Entry<Integer,Integer> num) {
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
            Entry<Integer,Integer> temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}
