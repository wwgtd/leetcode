
class Solution {

    public boolean hasCycle(ListNode head) {
        ListNode p1 = head;
        ListNode p2 = head.next;

        while (p2 != null) {
            if (p1 == p2) return true;
            p1 = p1.next;
            p2 = p2.next;
            if (p2 != null) {
                p2 = p2.next;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        boolean answer = solution.hasCycle(fromIntArray(new int[]{1,2}, 0));

        System.out.println(answer);
    }

    private static ListNode fromIntArray(int[] array, int pos) {
        ListNode listNode = new ListNode(array[0]);
        ListNode result = listNode;
        
        ListNode cycledNode = null; 

        for (int i = 1; i < array.length; i++) {
            listNode.next = new ListNode(array[i]);
            listNode = listNode.next;

            if (i == pos) {
                cycledNode = listNode;
            }
        }

        if (cycledNode != null) {
            listNode.next = cycledNode;
        }

        return result;
    }

    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

}
