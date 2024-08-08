/**
 * 279. Sort With 3 Stacks
 * Given one stack with integers, sort it with two additional stacks (total 3 stacks). 
 *
 * After sorting the original stack should contain the sorted integers and from top to bottom the integers are sorted
 * in ascending order.
 */

// Version 1: Merge Sort
// Time: O(n logn): divide/sorting take logn level; merge take logn level (with O(n) to copy the result)
public class Solution {
  public void sort(LinkedList<Integer> s1) {
    LinkedList<Integer> s2 = new LinkedList<Integer>();
    LinkedList<Integer> s3 = new LinkedList<Integer>();
    // Write your solution here.
    // Corner case:
    if (s1 == null || s1.size() <= 1) {
      return;
    }
    // Sort elements in s1 using s2 as buffer and s3 as
    // temporary storage for the results
    // Specify the number of elements to get sorted in s1
    sort(s1, s2, s3, s1.size());
  }

  private void sort(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int len) {
    // Base case: when there are only one element left to be sorted
    if (len == 1) {
      return;
    }
    // Divide the input s1 into two halves and sort them respectively:
    // Put 1/2 of the elements in s1 to the buffer s2
    // Sort the elements in s1 and the transfered elements in s2
    int half = len / 2;

    for (int i = 0; i < half; i++) {
      s2.offerFirst(s1.pollFirst());
    }
    // There are (len - half) elements left in s1
    sort(s1, s2, s3, len - half);
    // There are half elements left in s2
    sort(s2, s1, s3, half);
    // Merge the newly sorted part in s1 and s2 into s3
    merge(s1, s2, s3, len - half, half);
    // Transfer the sorted part from s3 back to s1
    transfer(s1, s3, len);
  }

  // Use s3 to sort s1/s2                                 
  private void merge(LinkedList<Integer> s1, LinkedList<Integer> s2, LinkedList<Integer> s3, int mid1, int mid2) {
    // Put the smaller number in s1 and s2 into s3 first
    // such that s3 will have the elements sorted in
    // descending order from top to bottom
    int i = 0;
    int j = 0;
    while (i < mid1 && j < mid2) {
      if (s1.peekFirst() < s2.peekFirst()) {
        s3.offerFirst(s1.pollFirst());
        i++;
      } else {
        s3.offerFirst(s2.pollFirst());
        j++;
      }
    }
    while (i < mid1) {
      s3.offerFirst(s1.pollFirst());
      i++;
    }
    while (j < mid2) {
      s3.offerFirst(s2.pollFirst());
      j++;
    }
  }

  private void transfer(LinkedList<Integer> s1, LinkedList<Integer> s3, int len) {
    for (int i = 0; i < len; i++) {
      s1.offerFirst(s3.pollFirst());
    }
  }
}

// ************************************************************************

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
