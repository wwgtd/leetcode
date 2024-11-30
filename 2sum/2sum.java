
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {

    public int[] twoSum(int[] nums, int target) {
        Set<Entry> numsSet = new HashSet();
        for (int i = 0; i < nums.length; i++) {
            numsSet.add(new Entry(nums[i], i));
        }


        for (Entry num : numsSet) {
            if (numsSet.contains(new Entry(target - num.val, -1))) {
                int index = -1;
                int searchVal = target - num.val;
                for (int i = 0; i < nums.length; i++) {
                    if (nums[i] == searchVal && num.index != i) {
                        return new int[] {num.index, i};
                    }
                }
            }
        }

        return null;
    }


    private class Entry {
        public int val;
        public int index;

        public Entry(int val, int index) {
            this.val = val;
            this.index = index;
        }


        @Override
        public boolean equals(Object obj) {
            if (obj instanceof Entry) {
                Entry entry = (Entry) obj;
                return entry.val == val;
            }

            return false;
        }

        @Override
        public int hashCode() {
            return val;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }

}
