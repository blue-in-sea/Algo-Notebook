public class SlidingWindowMedian {
    /**
     * @param nums: A list of integers
     * @param k: An integer
     * @return: The median of the element inside the window at each moving
     */
    public List<Integer> medianSlidingWindow(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        
        if (nums == null || nums.length == 0) {
            return res;
        }
        
        // PriorityQueue<Integer> maxHeap = new PriorityQueue<>((x, y) -> y - x); one test case issue!! see note
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (int i = 0; i < nums.length; i++) {
            if (maxHeap.size() == 0 || nums[i] <= maxHeap.peek()) {
                maxHeap.offer(nums[i]);
            } else {
                minHeap.offer(nums[i]);
            }
            
            if (i >= k) {
                if (nums[i - k] > maxHeap.peek()) {
                    minHeap.remove(nums[i - k]);
                } else {
                    maxHeap.remove(nums[i - k]);
                }
            }
            
            balance(maxHeap, minHeap);
            if (i >= k - 1) {
                res.add(maxHeap.peek());
            }
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
