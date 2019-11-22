public class DataStreamMedian {
    /**
     * @param nums: A list of integers
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(n, Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(n);
        
        for (int i = 0; i < n; i++) {
            if (maxHeap.size() == 0 || nums[i] <= maxHeap.peek()) {
                maxHeap.offer(nums[i]);
            } else {
                minHeap.offer(nums[i]);
            }
            // (only allow) maxHeap = minHeap or maxHeap = minHeap - 1
            balance(maxHeap, minHeap);
            res[i] = maxHeap.peek();
        }
        
        return res;
    }
    
    private void balance(PriorityQueue<Integer> maxHeap, PriorityQueue<Integer> minHeap) {
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
        
        while (minHeap.size() < maxHeap.size() - 1) {
            minHeap.offer(maxHeap.poll());
        }
    }
}

/*
reverseOrder()
Returns a comparator that imposes the reverse of the natural ordering on a collection of objects 
that implement the Comparable interface.
https://docs.oracle.com/javase/8/docs/api/?java/util/Collections.html
*/
/*
https://stackoverflow.com/questions/11003155/change-priorityqueue-to-max-priorityqueue
*/
