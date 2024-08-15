/**
 * 634. Stack by Queue(s)
 * Implement a stack containing integers using queue(s). The stack should provide push(x), pop(), top() and isEmpty().
 */
class StackByQueue {
  private Queue<Integer> queue;
    
    /** Initialize your data structure here. */  
    public Solution() {
      queue = new ArrayDeque<>();
 
    }

    /** Push element x onto stack. */                                              // O(1)
    public void push(int x) {  
      queue.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */       // O(n)
    public Integer pop() {
      if (queue.isEmpty()) {
        return null;
      }
      int size = queue.size();
      while (--size != 0) {
        queue.offer(queue.poll());
      }
      return queue.poll();
    }

    /** Get the top element. */                                                      // O(n)
    public Integer top() {
      if (queue.isEmpty()) {
        return null;
      }
      int top = pop();
      queue.offer(top);
      return top;
    }

    /** Returns whether the stack is empty. */                                        // O(1)
    public boolean isEmpty() {
      return queue.isEmpty();
    }
}
