public class KthSmallestWithOnly3,5,7AsFactors {
    // Method 1: Min Heap
    // Time: O(klogn), Space: O(k)
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

    // Method 2: Deuque (linear solution)
    // Time: O(k), Space: O(k)
    public long kth(int k) {
        // Assume K >= 1
        long seed = 3 * 5 * 7L;
        // We sue three deques to maintaon all the possible values.
        // The rule is 
        // deque3 only maintains the value of seed * 3^x
        // deque5 only maintains the value of seed * 3^x * 5^y
        // deque7 only maintains the value of seed * 3^x * 5^y * 7^z
        Deque<Long> three = new LinkedList<>();
        Deque<Long> five = new LinkedList<>();
        Deque<Long> seven = new LinkedList<>();

        three.add(seed * 3);
        five.add(seed * 5);
        seven.add(seed * 7);

        long res = seed;
        while (k > 1) {
            // each round, pick the samllest one from the head of the three deques
            // when pushing back the value into the deques, following the rules 
            // 
            // if the samllest number x is from deque5
            // we don't need to push x*3 again
            // because x*3 has to be already genereted before
            // x = 3^a * 5^b, x * 3 = 3^(a+1) * 5^(b-1) * 5

            if (three.peekFirst() < five.peekFirst() && three.peekFirst() < seven.peekFirst()) {
                res = three.pollFirst();
                three.offerLast(res * 3);
                five.offerLast(res * 5);
                seven.offerLast(res * 7);
            } else if (five.peekFirst() < three.peekFirst() && five.peekFirst() < seven.peekFirst()) {
                res = five.pollFirst();
                five.offerLast(res * 5);
                seven.offerLast(res * 7);
            } else {
                res = seven.pollFirst();
                seven.offerLast(res * 7);
            }
            k--;
        }
        return res;
    }
}
