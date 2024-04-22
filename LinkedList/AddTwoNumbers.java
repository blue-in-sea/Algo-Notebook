public class AddTwoNumbers {

    static class ListNode {
        int val;
        ListNode next;
        public ListNode(int val) {
            this.val = val;
        }
    }

    public static ListNode addTwoNumbers(ListNode a, ListNode b) {
        ListNode dummy = new ListNode(0); // Create a dummy head to construct the result list
        ListNode cur = dummy;

        int sum = 0;

        // Traverse two lists and sum
        while (a != null || b != null || sum != 0) {
            if (a != null) {
                sum += a.val;
                a = a.next;
            }
            if (b != null) {
                sum += b.val;
                b = b.next;
            }

            // if sum is greate than 10
            ListNode node = new ListNode(sum % 10);
            sum = sum / 10;

            cur.next = node;
            cur = cur.next;
        }

        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode a = new ListNode(2);
        a.next = new ListNode(4);
        a.next.next = new ListNode(3);

        ListNode b = new ListNode(5);
        b.next = new ListNode(6);
        b.next.next = new ListNode(4);

        printLinkedList(addTwoNumbers(a, b));

    }

    private static void printLinkedList(ListNode head) {
        for (ListNode cur = head; cur != null; cur = cur.next) {
            System.out.println(cur.val);
        }
    }
}
