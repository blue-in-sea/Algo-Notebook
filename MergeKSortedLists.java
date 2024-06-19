/**
 * 23. Merge k Sorted Lists
 * You are given an array of k linked-lists lists, each linked-list is sorted in ascending order.
 * Merge all the linked-lists into one sorted linked-list and return it.
 *
 * Input: lists = [[1,4,5],[1,3,4],[2,6]]
 * Output: [1,1,2,3,4,4,5,6]
 * Explanation: The linked-lists are:
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * merging them into one sorted list: 1->1->2->3->4->4->5->6
 */

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
    // Algo: minHeap store K nodes
    // Time: O(nlogk) -> where every poll & insertion to minHeap will be O(logk)
    //                -> where n nodes in total
    // Space: O(1) no extra space
    public ListNode mergeKLists(ListNode[] lists) {
        // assume input arr is not null, and none of the lists given is null

        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>((o1, o2) -> o1.val - o2.val);

        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        for (int i = 0; i < lists.length; i++) {
            ListNode node = lists[i];
            if (node != null) {
                minHeap.offer(node);
            }
        }
        while (!minHeap.isEmpty()) {           // (n nodes to be linked)
            curr.next = minHeap.poll();        //    logk for comparison
            if (curr.next.next != null) {
                minHeap.offer(curr.next.next);
            }
            curr = curr.next;
        }
        return dummy.next;
    }

    /**
     * Brutal force
     * 1. Sort the all nodes in the lists and linked
     * Time: O(nlogn)
     * Space: O(n)
     *
     * 2. Merge List 1 by 1
     * Time: O(kn)
     * Space: O(1)
     *
     * Advance: given n >>> k ** (not required)
     * 3. Merge with Divide And Conquer
     * Time: O(nlogk) -> where merging two sorted linked list in O(n)
     *                -> where it takes logk to merge k lists
     * Space: O(1)
     * https://leetcode.com/problems/merge-k-sorted-lists/editorial/
     */
}
