/**
 * 84. Largest Rectangle in Histogram
 * Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
 * return the area of the largest rectangle in the histogram.
 *
 * Example 1:
 * Input: heights = [2,1,5,6,2,3]
 * Output: 10
 * Explanation: The above is a histogram where width of each bar is 1.
 * The largest rectangle is shown in the red area, which has an area = 10 units.
 *
 * Example 2:
 * Input: heights = [2,4]
 * Output: 4
 */
public class LargestRectangleInHistogram {
  // Time: O(N) for every single element can only be inserted and popped out of stack only once
  // Space: O(N) for the stack 
  public int largest(int[] array) {
    // assume array is not null or empty
    // and contains no negative value
    int maxArea = 0;
    // stack stores the index of the value
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = 0; i <= array.length; i++) {
      // we need a way of popping out of all elements in the stack
      // at least, so that we explicitly add a bar of height 0
      int cur = i == array.length ? 0 : array[i];
      while (!stack.isEmpty() && array[stack.peekFirst()] >= cur) {
        int height = array[stack.pollFirst()];
        // determine the left bound of the rectangle
        int left = stack.isEmpty() ? 0 : stack.peekFirst() + 1;
        maxArea = Math.max(height * (i - left), maxArea);
      }

      stack.offerFirst(i);
    } 
    return maxArea;
  }
  /**
   *                   0 1 2 3 4 5    6
   * Input: heights = [2,1,5,6,2,3]   x
   *
   * left = index (top of stack), right = 1, height = value (poll stack, lowest height)
   *
   * peak                                 Area   (i -  l) *  h[stack.pollFirst()]       (peak)element
   * stack [0]                               2 = (1 - 0)  *  2                          [2]
   * stack [1] [2, 1] [3, 2, 1]              6 = (4 - 3)  *  6                          [1] [5, 1] [6, 5, 1]
   *                                        10 = (4 - 2)  *  5   maxAre=10
   *
   * stack [4, 1] [5, 4, 1]                  3 = (6 - 5)   * 3                          [2, 1] [3, 2, 1]
   *                                         8 = (6 - 2)   * 2
   *                                         8 = (6 - 0)   * 1
   *
   * stack [6] and cur reset to 0, index out                                            [x]
   */
}

/**
 *                 System.out.print("left: " + left + ", ");
 *                 System.out.print("i: " + i + "(right), ");
 *                 System.out.print("height: " + height + ", ");
 *                 System.out.print("Area: " + height * (i - left) + ", ");
 *                 System.out.print("maxArea: " + maxArea + ". ");
 *                 System.out.println();
 *                 System.out.print(stack);
 */
