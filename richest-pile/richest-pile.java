
import java.util.ArrayList;
import java.util.List;

class Solution {

    public long pickGifts(int[] gifts, int k) {
        MaxHeap heap = new MaxHeap(gifts);

        for (int i = 0; i < k; i++) {
            int max = heap.extractMax();
            int newVal = (int) Math.floor(Math.sqrt(max));
            heap.insert(newVal);
        }

        long result = 0;
        while (heap.getSize() > 0) {
            result += heap.extractMax();
        }

        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.pickGifts(new int[]{25, 64, 9, 4, 100}, 4));
    }

    private static class MaxHeap {

        private final List<Integer> heap = new ArrayList();

        public MaxHeap(int[] nums) {
            for (int i = 0; i < nums.length; i++) {
                insert(nums[i]);
            }
        }

        public void log() {
            System.out.println(heap);
        }

        public int getSize() {
            return heap.size();
        }

        public int extractMax() {
            if (heap.size() == 1) {
                return heap.remove(0);
            }

            if (heap.size() == 2) {
                swap(0, 1);
                return heap.remove(1);
            }

            int max = heap.get(0);

            int lastNode = heap.get(heap.size() - 1);
            heap.remove(heap.size() - 1);
            heap.set(0, lastNode);

            int parent = 0;

            while (parent * 2 + 1 < heap.size()) {
                int leftChild = parent * 2 + 1;
                int rightChild = leftChild + 1;
                if (heap.size() > rightChild) {
                    if (heap.get(parent) < heap.get(leftChild) && heap.get(leftChild) > heap.get(rightChild)) {
                        swap(leftChild, parent);
                        parent = leftChild;
                    } else if (heap.get(parent) < heap.get(rightChild)) {
                        swap(rightChild, parent);
                        parent = rightChild;
                    } else {
                        break;
                    }
                } else {
                    if (heap.get(parent) < heap.get(leftChild)) {
                        swap(leftChild, parent);
                        parent = leftChild;
                    } else {
                        break;
                    }
                }
            }

            return max;
        }

        public void insert(int num) {
            heap.add(num);

            bubbleUp();
        }

        private void bubbleUp() {
            int bubbledIndex = heap.size() - 1;

            int parent = getParentIndex(bubbledIndex);
            int i = bubbledIndex;

            while (parent >= 0 && heap.get(parent) < heap.get(i)) {
                swap(parent, i);
                i = parent;
                parent = getParentIndex(parent);
            }
        }

        private int getParentIndex(int index) {
            return index % 2 == 0 ? index / 2 - 1 : index / 2;
        }

        private void swap(int i, int j) {
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }
    }
}
