public class MyQueue {
    private Deque<Integer> s1; // in
    private Deque<Integer> s2; // out
    
    public MyQueue() {
        s1 = new LinkedList<>();
        s2 = new LinkedList<>();
    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        s1.offerFirst(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        move();
        return s2.pollFirst();
    }

    /*
     * @return: An integer
     */
    public int top() {
        move();
        return s2.peekFirst();
    }
    
    /*
     * helper function to balance stack 
     */
    public void move() { 
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.offerFirst(s1.pollFirst());
            }
        }
    }
}
