/**
 * Definition for a Node.
 * class Node {
 *     int val;
 *     Node next;
 *     Node random;
 *
 *     public Node(int val) {
 *         this.val = val;
 *         this.next = null;
 *         this.random = null;
 *     }
 * }
 */
public class DeepCopyListWithRandomPointer {
    public Node copyRandomList(Node head) {
        // corner case 
        if (head == null) return head;
        // dummy node help to construct the deep copy
        Node dummy = new Node(0);
        Node curr = dummy;
        // maping the one-to-one relation in the given hashmap
        Map<Node, Node> map = new HashMap<>();

        while (head != null) {
            if (!map.containsKey(head)) {
                map.put(head, new Node(head.val));
            }
            // connect the copied node on the copy list 
            curr.next = map.get(head);

            // copy the random node if necessary
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new Node(head.random.val));
                }
                // connect the copied node on the copy list 
                curr.next.random = map.get(head.random);
            }

            head = head.next;
            curr = curr.next;
        }

        return dummy.next;
    }
}
