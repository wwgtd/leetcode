
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int lastStoneWeight(int[] stones) {
        MaxHeap heap = new MaxHeap(stones);

        while (heap.getSize() >= 2) {
            int firstMax = heap.extractMax();
            int secondMax = heap.extractMax();

            if (firstMax == secondMax) {
                continue;
            } else {
                heap.insert(firstMax - secondMax);
            }
        }

        return heap.getSize() == 0 ? 0 : heap.extractMax();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.lastStoneWeight(new int[] {2,7,4,1,8,1}));
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