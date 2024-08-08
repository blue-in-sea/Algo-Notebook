/**
 * 204. Maximum Values Of Size K Sliding Windows
 * Given an integer array A and a sliding window of size K, find the maximum value of each window as it slides from 
 * left to right.
 *
 * Assumptions
 * The given array is not null and is not empty
 *
 * K >= 1, K <= A.length
 *
 * A = {1, 2, 3, 2, 4, 2, 1}, K = 3, the windows are {{1,2,3}, {2,3,2}, {3,2,4}, {2,4,2}, {4,2,1}},
 * and the maximum values of each K-sized sliding window are [3, 3, 4, 4, 4]
 */
public class MaximumValuesOfSizeKSlidingWindows {
  // Method 1: Maintain a monolithic Deque 
  public List<Integer> maxWindows(int[] array, int k) {
    List<Integer> res = new ArrayList<>();
    // corner case
    if (array == null || array.length == 0) {
      return res;
    }
    //维护一个单调非递减序列
    Deque<Integer> dq = new ArrayDeque<>();
    for (int i = 0; i < array.length; i++) {
      while (!dq.isEmpty() && array[i] >= array[dq.peekFirst()]) {
        dq.pollFirst();
      }
      dq.offerFirst(i);
      if (i >= k - 1) {
        res.add(array[dq.peekLast()]);
      }
      while(!dq.isEmpty() && dq.peekLast() <= i - k + 1){
        dq.pollLast();
      }
    }
    return res;
  }

  // ==================================================================
  // Method 2: Maintain a TreeSet 
  class Node {
    int pos;
    int val;
    public Node (int pos, int val) {
      this.pos = pos;
      this.val = val;
    }
  }

  public List<Integer> maxWindows(int[] array, int k) {
    Comparator<Node> comparator = new Comparator<Node>() {
      public int compare(Node left, Node right) {
        if (right.val == left.val) {
          // 如果相等，位置不变
          return left.pos - right.pos;    
        }
        // 如果不相等，max first (单调非递减序列)
        return right.val - left.val;
      }  
    };

    TreeSet<Node> set = new TreeSet<>(comparator);
    ArrayList<Integer> res = new ArrayList<>();

    for (int i = 0; i < array.length; i++) {
      Node node = new Node(i, array[i]);
      set.add(node);

      if (set.size() > k) {
        Node last = new Node(i - k, array[i - k]);
        set.remove(last);
      }

      if (set.size() == k) {
        res.add(set.first().val);
      }
    }
    return res;
  }
}
