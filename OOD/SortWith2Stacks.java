

public class SortWith2Stacks {
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
      // 每次压当前 min 进入 s2
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
