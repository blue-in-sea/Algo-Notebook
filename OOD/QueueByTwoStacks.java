// Stack LIFO(Last In First Out) 
// Queue FIFO(First In Last Out)
public class QueueByTwoStacks {
  private Deque<Integer> s1;  // in
  private Deque<Integer> s2;  // out

  public QueueByTwoStacks() {
    s1 = new LinkedList<>();
    s2 = new LinkedList<>();
  }
  
  public Integer poll() {
    move();
    return s2.pollFirst();
  }
  
  public void offer(int element) {
    s1.offerFirst(element);
  }
  
  public Integer peek() {
    move();
    return s2.peekFirst();
  }
  
  public int size() {
    return s1.size() + s2.size();
  }
  
  public boolean isEmpty() {
    return s1.isEmpty() && s2.isEmpty();
  }

  private void move() {
    if (s2.isEmpty()) {
      while (!s1.isEmpty()) {
        s2.offerFirst(s1.pollFirst());
      }
    }
  }
}


// push 1, 2, 3
// s1 [3, 2, 1]
// s2[]

// peek -> 1
// s1 []
// s2 [1, 2, 3]
