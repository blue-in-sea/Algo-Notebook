public class KthSmallestWithOnly3,5,7AsFactors {
    // Method 1: BFS
    public long kth(int k) {
        // Assumption: K >= 1
        PriorityQueue<Long> minHeap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        // We used the actual product value to represent the states 
        // <x, y, z>, the value is 3^x * 5^y * 7^z, and the initial 
        // state is <1, 1, 1>
        minHeap.offer(3 * 5 * 7L);
        visited.add(3 * 5 * 7L);

        while (k > 1) {
            long current = minHeap.poll();
            // for the state <x+1, y, z>, the actual value is *3
            if (visited.add(3 * current)) {
                minHeap.offer(3 * current);
            }
            // for the state <x, y+1, z>, the actual value is *5
            if (visited.add(5 * current)) {
                minHeap.offer(5 * current);
            }
            // for the state <x, y, z+1>, the actual value is *7
            if (visited.add(7 * current)) {
                minHeap.offer(7 * current);
            }
            k--;
        }

        return minHeap.peek();
    }
}
