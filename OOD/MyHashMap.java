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
