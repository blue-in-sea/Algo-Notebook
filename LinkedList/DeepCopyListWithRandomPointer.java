/**
 * Deep Copy Linked List With Random Pointer
 * Each of the nodes in the linked list has another pointer pointing to a random node in the list or null. 
 * Make a deep copy of the original list.
 */
public class DeepCopyListWithRandomPointer {
    // HashMap: <K: node, V: node_copy> where the new linked list will be chained on node_copy
    // Time: O(n), Space: O(n)
    
    public Node copyRandomList(Node head) {
        if (head == null) return head;

        Node dummy = new Node(0);
        Node curr = dummy;
       
        Map<Node, Node> map = new HashMap<>();

        while (head != null) {
            if (!map.containsKey(head)) {
                map.put(head, new Node(head.val));
            }
            curr.next = map.get(head);

            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new Node(head.random.val));
                }
                curr.next.random = map.get(head.random);
            }

            head = head.next;
            curr = curr.next;
        }

        return dummy.next;
    }
}
/**
 * class Node {
 *     int val;
 *     Node next;
 *     Node random;
 *
 *     public Node(int val) {
 *         this.val = val;
 *         // this.next = null;
 *         // this.random = null;
 *     }
 * }
 */
