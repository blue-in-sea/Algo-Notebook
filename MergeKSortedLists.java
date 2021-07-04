/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class MergeKSortedLists {
    static class MyComparator implements Comparator<ListNode> {
        @Override
        public int compare(ListNode o1, ListNode o2) {
            if (o1.val == o2.val) {
                return 0;
            }
            return o1.val < o2.val ? -1 : 1;
        }
    }
    public ListNode mergeKLists(ListNode[] lists) {
        // assume input arr is not null, and none of the lists given is null
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(11,new MyComparator());

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            if (node != null) {
                minHeap.offer(node);
            }
        }
        while (!minHeap.isEmpty()) {
            curr.next = minHeap.poll();
            if (curr.next.next != null) {
                minHeap.offer(curr.next.next);
            }
            curr = curr.next;
        }
        return dummy.next;
    }
}
