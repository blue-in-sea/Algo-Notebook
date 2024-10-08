/**
 * 288. First Non-Repeating Character In Stream
 * Given a stream of characters, find the first non-repeating character from stream. You need to tell the first 
 * non-repeating character in O(1) time at any moment.
 *
 * Implement two methods of the class:
 * read() - read one character from the stream
 * firstNonRepeating() - return the first non-repoeating character from the stream at any time when calling the 
 * method, return null if there does not exist such characters
 *
 * read()
 * a   b   c   a   c   c    b
 *
 * firstNonRepeating()
 * a   a   a   b   b   b   null
 */

public class FirstNonRepeatingCharacterInStream {
  // each node is a double linked list node
  // and it contains one dinstict character
  static class Node {
    Node prev;
    Node next;
    Character ch;

    Node(Character ch) {
      this.ch = ch;
    }
  }

  // record the head and tail of the list all the time
  // only the characters appearing just once will be 
  // in the double linked list
  private Node head;
  private Node tail;
  // for any character, it could be in three stage: 
  // 1. noly exists once, it will not be in singled map or the repeated set
  // 2. only exists once, it will be in singled map, with a corresponding
  //    node in the doubly linked list
  // 3. exists more than once, it will be in the repeated set, and it will  
  //    be removed from the repeated set 
  private HashMap<Character, Node> singled;
  private HashSet<Character> repeated;
  public Solution() {
    // using sentinel node to eliminate some corner case
    tail = new Node(null);
    tail.next = tail.prev = tail;
    head = tail;
    singled = new HashMap<Character, Node>();
    repeated = new HashSet<Character>();
  }
  
  public void read(char ch) {
    // 1) If the character already exists more than once, 
    //    we just ignore
    if (repeated.contains(ch)) {
      return;
    }
    Node node = singled.get(ch);
    if (node == null) {
      // 2) if the character appears for the first time 
      // it should be added to the list and added to
      // the singled map (still not in repeated set)
      node = new Node(ch);
      append(node);
    } else {
      // 3) if the character is already in the singled map,
      // we should remove it from the map and repeated set,
      // and put it into the repeated set
      remove(node);
    }
  }
  
  public Character firstNonRepeating() {
    // when head == tail, it means there is only the sentinel node here
    if (head == tail) {
      return null;
    }
    return head.next.ch;
  }

  private void append(Node node) {
    singled.put(node.ch, node);
    tail.next = node;
    node.prev = tail;
    node.next = head;  // circle 
    
    tail = tail.next;
  }

  private void remove(Node node) {
    // using sentinel node to eliminate some corner case
    node.prev.next = node.next;
    node.next.prev = node.prev;

    if (node == tail) {
      tail = node.prev;
    }
    node.prev = node.next = null;

    repeated.add(node.ch);
    singled.remove(node.ch);
  }
}
