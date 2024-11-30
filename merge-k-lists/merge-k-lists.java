
class Solution {

    // public ListNode mergeKLists(ListNode[] lists) {
    //     ListNode[] pointers = new ListNode[lists.length];
    //     Set<Integer> notEmptyPointers = new HashSet<>();
    //     for (int i = 0; i < pointers.length; i++) {
    //         pointers[i] = lists[i];
    //         notEmptyPointers.add(i);
    //     }
    //     ListNode result = new ListNode();
    //     while (notEmptyPointers.size() > 0) {
    //         int min = 0;
    //         for (int pointer : notEmptyPointers) {
    //         }
    //     }
    // }
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 1) {
            return lists[0];
        }

        int newListLength = lists.length / 2 + lists.length % 2;

        ListNode[] newList = new ListNode[newListLength];

        for (int i = 0; i < newListLength; i++) {
            ListNode firstList = lists[i * 2];
            ListNode secondList = i * 2 + 1 < lists.length ? lists[i * 2 + 1] : null;
            newList[i] = mergeTwoLists(firstList, secondList);
        }

        return mergeKLists(newList);
    }

    // public static void main(String[] args) {
    //     Solution solution = new Solution();
    //     ListNode[] listNodes = solution.fromIntArray(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}});
    //     for (int i = 0; i < listNodes.length; i++) {
    //         // System.out.print(i + " listnode: ");
    //         // solution.printNode(listNodes[i]);
    //     }
    //     ListNode result = solution.mergeKLists(listNodes);
    //     solution.printNode(result);

    // }

    private void printNode(ListNode p) {
        while (p != null) {
            System.out.print(" -- " + p.val);
            p = p.next;
        }
        System.out.println();
    }

    private ListNode[] fromIntArray(int[][] array) {
        ListNode[] listNodes = new ListNode[array.length];
        ListNode[] result = new ListNode[array.length];

        for (int i = 0; i < array.length; i++) {
            listNodes[i] = new ListNode();
            result[i] = listNodes[i];
        }

        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                listNodes[i].val = array[i][j];
                if (j + 1 != array[i].length) {
                    listNodes[i].next = new ListNode();
                    listNodes[i] = listNodes[i].next;
                }
            }
        }

        return result;
    }

    private ListNode mergeTwoLists(ListNode first, ListNode second) {
        ListNode p1 = first;
        ListNode p2 = second;

        ListNode result = null;
        ListNode start = result;

        while (p1 != null || p2 != null) {
            if (result == null) {
                start = result = new ListNode();
            }

            if (p1 == null) {
                result.val = p2.val;
                result.next = p2.next;
                break;
            } else if (p2 == null) {
                result.val = p1.val;
                result.next = p1.next;
                break;
            } else if (p1.val > p2.val) {
                result.val = p2.val;
                p2 = p2.next;
            } else {
                result.val = p1.val;
                p1 = p1.next;
            }

            result.next = new ListNode();
            result = result.next;
        }

        return start;
    }

    private class ListNode {

        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
