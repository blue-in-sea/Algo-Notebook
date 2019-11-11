/*
 * Reverse pairs of elements in a singly-linked list.
 *
 * Examples
 * input  N1 -> N2 -> N3 -> N4 -> NULL
 * return N2 -> N1 -> N4 -> N3 -> NULL
 * */
class ListNode {
  int value;
  int ListNode next;
  
  ListNode(int value) {
    this.value = value;
    next = null;
 }
 
 public class Solution {
   /* 
    * Method1: Recursive
    *
    * 1. 返回值：head 为空指针或者 next 为空指针,
    *    也就是当前无节点或者只有一个节点, 无法进行交换
    * 2. 调用单元：设需要交换的两个点为 head 和 next, head 连接后面
    *    交换完成的子链表，next 连接 head, 完成交换
    * 3. 返回值：交换完成的子链表
    * */  
  public ListNode reverseInPairs(ListNode head) {
    // 1. Terminating condition  
    if (head == null || head.next == null) {
      return head;
    }
    // 2. What should we do on the current level?
    ListNode newHead = head.next;
    head.next = reverseInPairs(head.next.next);
    newHead.next = head;
    // 3. What should we return to the parent level?
    return newHead;
  }
  
   /* 
    * Method2: Iterative
    *
    * Idea similar to swap two nodes
    * 
    * input  N1 -> N2 -> N3 -> NULL
    * return N2 -> N1 -> N3 -> NULL
    * */   
  public ListNode reverseInPairsIterative(ListNode head) {
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = dummy;
    while (cur.next != null && cur.next.next != null) {
      ListNode next = cur.next.next;        // next = N2
      
      cur.next.next = cur.next.next.next;   // N2 -> N3
      next.next = cur.next;                 // N3 -> N1
      cur.next = next;                      // newHead = N2 
      
      cur = cur.next.next;                  // cur move 2 steps along the list 
    }                                       // to process the remaining part
    return dummy.next;
  }
   
   /*
    * Method3: Stack
    *
    * 利用一个stack，然后不断迭代链表，每次取出两个节点放入stack中，再从stack中拿出两个节点。
    * 借助stack后进先出的特点，放进去的时候是1,2。拿出来的时候就是2，1两个节点了。
    * 再把这两个节点串联起来，重复这个逻辑遍历完整个链表，就可以做到两两反转的效果了。
    * 虽然用到了stack，但因为只存了两个元素，所以空间复杂度还是O(1)，时间复杂度是O(n)。
    * 
    * */
   public ListNode reverseInPairsStack(ListNode head) {
    // if list is empty or contains only one node, cannot reverse
    if (head == null || head.next == null) {
      return head;
    }
    //用stack保存每次迭代的两个节点
    Deque<ListNode> stack = new LinkedList<>();

    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode cur = dummy;

    while (head != null && head.next != null) {
      //将两个节点放入stack中
      stack.push(head);
      stack.push(head.next);
      //当前节点往前走两步
      head = head.next.next;
      //从stack中弹出两个节点，然后放入新的list里
      cur.next = stack.poll();
      cur.next.next = stack.poll();
      cur = cur.next.next;
    }
    // post-processing: if list has odd number of nodes
    // link the last node to the new list
    if (head != null) {
      cur.next = head;
    } else {
      cur.next = null;  // set null after the tail of list!!
    }

    return dummy.next;
}
