/**
 * 706. Design HashMap
 * Design a HashMap without using any built-in hash table libraries.
 *
 * MyHashMap() initializes the object with an empty map.
 *   void put(int key, int value) inserts a (key, value) pair into the HashMap. If the key already exists in the map, update the corresponding value.
 *   int get(int key) returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key.
 *   void remove(key) removes the key and its corresponding value if the map contains the mapping for the key.
 *
 * ["MyHashMap", "put", "put", "get", "get", "put", "get", "remove", "get"]
 * [[], [1, 1], [2, 2], [1], [3], [2, 1], [2], [2], [2]]
 * Output
 * [null, null, null, 1, -1, null, 1, null, -1]
 *
 * MyHashMap myHashMap = new MyHashMap();
 * myHashMap.put(1, 1); // The map is now [[1,1]]
 * myHashMap.put(2, 2); // The map is now [[1,1], [2,2]]
 * myHashMap.get(1);    // return 1, The map is now [[1,1], [2,2]]
 * myHashMap.get(3);    // return -1 (i.e., not found), The map is now [[1,1], [2,2]]
 * myHashMap.put(2, 1); // The map is now [[1,1], [2,1]] (i.e., update the existing value)
 * myHashMap.get(2);    // return 1, The map is now [[1,1], [2,1]]
 * myHashMap.remove(2); // remove the mapping for 2, The map is now [[1,1]]
 * myHashMap.get(2);    // return -1 (i.e., not found), The map is now [[1,1]]
 */
/**
 * Time: O(N/K) for each of the methods
 *     N is the number of all possible keys and
 *     K is the number of predefined buckets in the hashmap, which is 2069 in our case
 *
 * Space: O(M + K) 
 *     M is the number of unique keys inserted 
 */
class MyHashMap<K, V> {
    private class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    private LinkedList<Entry<Integer, Integer>>[] buckets;

    public MyHashMap() {
        // provide a default capacity, JAVA default 16
        this.capacity = 2069; 
        buckets = new LinkedList[capacity];

        // initialization 
        for (int i = 0; i < capacity; i++) {
            buckets[i] = new LinkedList<>();
        }
    }
    
    public void put(int key, int value) {
        int index = getBucketIndex(key);
        // case 1: if key exit in the map, update the value
        LinkedList<Entry<Integer, Integer>> bucket = buckets[index];
        for (Entry<Integer, Integer> entry : bucket) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        } 
        // case 2: new key 
        bucket.add(new Entry<Integer, Integer>(key, value));
    }

    public int get(int key) {
        int index = getBucketIndex(key);
        // case 1: if key exit in the map, find the value
        LinkedList<Entry<Integer, Integer>> bucket = buckets[index];
        for (Entry<Integer, Integer> entry : bucket) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        } 
        // case 2: key non-exit
        return -1;
    }
    
    public void remove(int key) {
        int bucketIndex = getBucketIndex(key);
        LinkedList<Entry<Integer, Integer>> bucket = buckets[bucketIndex];
        Entry<Integer, Integer> toRemove = null;
        for (Entry<Integer, Integer> entry : bucket) {
            if (entry.key.equals(key)) {
                toRemove = entry;
                break;
            }
        }
        if (toRemove != null) {
            bucket.remove(toRemove);
        }
    }

    private int getBucketIndex(Integer key) {
        int hashCode = key.hashCode();
        return Math.abs(hashCode) % capacity;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
