/**
 * 32. Stack With min()
 * 
 * Enhance the stack implementation to support min() operation. min() should return the current minimum value in the 
 * stack. If the stack is empty, min() should return -1.
 *
 * push(int element) - push the element to top
 * pop() - return the top element and remove it, if the stack is empty, return -1
 * top() - return the top element without remove it, if the stack is empty, return -1
 * min() - return the current min value in the stack.
 */
public class StackWithMin {
  private Deque<Integer> stack;
  private Deque<Integer> minStack;

  public StackWithMin() {
    stack = new LinkedList<Integer>();
    minStack = new LinkedList<Integer>();
  }

  public int min() {
    if (minStack.isEmpty()) {
      return -1;
    }
    return minStack.peekFirst();
  }

  public void push(int value) {
    stack.offerFirst(value);
    // when value <= current min value in stack,
    // need to push the value to minStack.
    if (minStack.isEmpty() || value <= minStack.peekFirst()) {
      minStack.offerFirst(value);
    }
  }

  public int pop() {
    if (stack.isEmpty()) {
      return -1;
    }
    Integer result = stack.pollFirst();
    // when the popped value is the same as top value of minStack, the value
    // need to be popped from minStack as well.
    if (minStack.peekFirst().equals(result)) {
      minStack.pollFirst();
    }
    return result;
  }

  public int top() {
    if (stack.isEmpty()) {
      return -1;
    }
    return stack.peekFirst();
  }
}
