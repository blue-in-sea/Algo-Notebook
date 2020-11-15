public class KthLargestElementInArray {
    public int findKthLargest(int[] nums, int k) {
        // corner case check 

        // Step1: Heapify the first k elements to form a min heap of size == k
        // Step2: Iterate over the remaining (n-k) elements
        // [Compare the new element with the SMALLEST element in the heap]
	    // Case1: new element >= SMALLEST element   => in heap
	    // Case2: new element  <  SMALLEST element  => ignore 

        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((x, y) -> x - y);

        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                minHeap.offer(nums[i]);
            } else if  (nums[i] >= minHeap.peek()) {
                minHeap.poll();
                minHeap.offer(nums[i]);
            }
        }
        
        return minHeap.peek();
    }
}
