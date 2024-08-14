/**
 * 280. Sort With 2 Stacks
 * Given an array that is initially stored in one stack, sort it with one additional stacks (total 2 stacks).
 *
 * After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted
 * in ascending order.
 *
 * The given stack is not null.
 * There can be duplicated numbers in the give stack.
 * 
 * Requirements: No additional memory, time complexity = O(n ^ 2).
 */
public class SortWith2Stacks {
  // Selection Sort
  // * s2 -> buffer, select min each time from s1, add to s2
  // * return to s1 
  // Time: O(n^2), No extra space created
  public void sort(LinkedList<Integer> s1) {
    if(s1 == null || s1.size() <= 1) {
      return;
    }
    LinkedList<Integer> s2 = new LinkedList<Integer>();
    sort(s1, s2);  // s1: input, s2: buffer
  }

  private void sort(LinkedList<Integer> s1, LinkedList<Integer> s2) {
    while (!s1.isEmpty()) {
      int cur = s1.pollFirst();
      // 每次比栈顶小的元素进 s2
      while (!s2.isEmpty() && cur < s2.peekFirst()) {
        s1.offerFirst(s2.pollFirst());
      }
      s2.offerFirst(cur);
    } 
    // after sort, s2 is in descending order from top to bottom (max in the top)
    // return to s1
    while (!s2.isEmpty()) {
      s1.offerFirst(s2.pollFirst());
    }
  }
}
