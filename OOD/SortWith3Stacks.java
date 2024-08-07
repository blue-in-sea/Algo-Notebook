/**
 * 279. Sort With 3 Stacks
 * Given one stack with integers, sort it with two additional stacks (total 3 stacks). 
 *
 * After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted
 * in ascending order.
 */




// Version 2: Selects the min each time from input (similar to selection sort)
// Time: O(n^2)
public class SortWith3Stacks {
  // declare min, cnt (if dup)
  //   1. supply input to buffer: find min in each iteration 
  //   2. min to s3
  //   3. buffer return to input
  // after sorted, s3 will be in descending order from top to bottom
  // s3 to s1 where s1 is sorted
  public void sort(LinkedList<Integer> s1) {
    if (s1 == null || s1.size() <= 1) {
      return;
    }

    LinkedList<Integer> s2 = new LinkedList<Integer>();
    LinkedList<Integer> s3 = new LinkedList<Integer>();
    sort(s1, s2, s3);
  }

  private void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3) {
    while (!s1.isEmpty()) {
      int min = Integer.MAX_VALUE;
      int cnt = 0;
      // 1. supply input s1 to buffer s2
      while (!s1.isEmpty()) {
        Integer cur = s1.pollFirst();
        s2.offerFirst(cur);

        if (cur < min) {
          min = cur;
          cnt = 1;
        } else if (cur == min) {
          cnt++;
        }
      }
      // 2. min to s3
      while (cnt-- > 0) {
        s3.offerFirst(min);
      }
      // 3. buffer s2 return to input s1
      while (!s2.isEmpty() && s2.peekFirst() >= min) {
        Integer tmp = s2.pollFirst();
        if (tmp != min) {
          s1.offerFirst(tmp);
        }
      }
    }
    // s3 contains integers and from top to bottom that are 
    // sorted in descending order (max in the top), when it
    // returns all element to s1, s1 contains reverse order 
    while (!s3.isEmpty()) {
      s1.offerFirst(s3.pollFirst());
    }
  }
}
