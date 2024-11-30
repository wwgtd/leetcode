class Solution {
    public ListNode reverseList(ListNode head) {
        return reverse(null, head);
    }

    private ListNode reverse(ListNode reversedHead, ListNode head) {
        if (head == null) {
            return reversedHead;
        }

        ListNode temp = head.next;
        head.next = reversedHead;
        
        return reverse(head, temp);
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