class LRUCache {
    static class Node {
        int key;
        int value;
        Node prev;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        void update(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private Node head;
    private Node tail;
    private Map<Integer, Node> map;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;  // or a special value
        }
        remove(node);
        append(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = null;
        // 1. if the node is already in the cache, we need to update its
        // value and move it to the head (most recent postion)
        if (map.containsKey(key)) {
            node = map.get(key);
            node.value = value;
            remove(node);
        }
        // 2. if the node is not in the cache, we still have capacity
        // we can append the node to the head 
        else if (map.size() < capacity) {
            node = new Node(key, value); 
        }
        // 3. if the node is not in the cache, we don't have capacity
        // we need to evict the tail and reuse the node, and put it
        // into the head
        else {
            node = tail;
            remove(node);
            node.update(key, value);
        }
        append(node);
    }

    private Node remove(Node node) {
        map.remove(node.key);
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        // special case 
        if (node == head) {
            head = node.next;
        }
        if (node == tail) {
            tail = node.prev;
        }
        // GC the node
        node.next = node.prev = null;
        return node;
    }

    private Node append(Node node) {
        map.put(node.key, node);
        if (head == null) {
            head = tail = node;
        } else {
            node.next = head;
            head.prev = node;
            head = node;
        }
        return node;
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
