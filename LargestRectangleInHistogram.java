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
      // at leaste, so that we explicitly add a bar of height 0
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
}
