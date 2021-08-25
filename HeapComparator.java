class HeapComparator {
    
    // min heap
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);
    PriorityQueue<Integer> minHeap = new PriorityQueue<>(n, (x, y) -> x - y);
    
    // max heap
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, Collections.reverseOrder());
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, (x, y) -> y - x);
    
    
    // ==== Map Entry Set ===
    
    PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>(
        (e1, e2) -> 
            e1.getValue() == e2.getValue() ? e2.getKey().compareTo(e1.getKey()) 
                                           : e1.getValue().compareTo(e2.getValue())
    );
    
    // ==== Override ===
    
    PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new CellComparator());
    class CellComparator implements Comparator<Cell> {
        @Override 
        public int compare(Cell c1, Cell c2) {
            return c1.val - c2.val;
        }
    }
    
    // ==== Self-defined Class ===
    
    static class MyComparator implements Comparator<Entry> {
        @Override
        public int compare(Entry e1, Entry e2) {
            if (e1.value == e2.value) {
                return 0;
            }
            return e1.value < e2.value ? -1 : 1;
        }
   }
  
    static class Entry {
        // the row number
        int x;
        // the col number 
        int y;
        // the corresponding value
        int value;

    Entry(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
        }
    }
}
