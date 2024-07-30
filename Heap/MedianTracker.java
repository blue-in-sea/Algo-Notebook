/**
 * 295. Find Median from Data Stream
 * The median is the middle value in an ordered integer list. If the size of the list is even, there is no middle 
 * value, and the median is the mean of the two middle values.
 *
 * For example, for arr = [2,3,4], the median is 3.
 * For example, for arr = [2,3], the median is (2 + 3) / 2 = 2.5.
 *
 * API
 * MedianFinder() - initializes the MedianFinder object.
 * void addNum(int num) - adds the integer num from the data stream to the data structure.
 * double findMedian() - returns the median of all elements so far. Answers within 10-5 of the actual answer will be accepted.
 *
 * ["MedianFinder", "addNum", "addNum", "findMedian", "addNum", "findMedian"]
 * [[], [1], [2], [], [3], []]
 * Output
 * [null, null, null, 1.5, null, 2.0]
 *
 * Explanation
 * MedianFinder medianFinder = new MedianFinder();
 * medianFinder.addNum(1);    // arr = [1]
 * medianFinder.addNum(2);    // arr = [1, 2]
 * medianFinder.findMedian(); // return 1.5 (i.e., (1 + 2) / 2)
 * medianFinder.addNum(3);    // arr[1, 2, 3]
 * medianFinder.findMedian(); // return 2.0
 * 
 * Method: maxHeap + minHeap
 * Time O(logn)
 *      Inserting an element takes O(logn) time for a standard multiset scheme. 4
 *      Finding the mean takes constant O(1) time since the tops of heaps are directly accessible.
 * Space: O(n) linear space to hold input in containers.
 */
class MedianTracker {
    PriorityQueue<Integer> maxHeap; // top of the heap be the max of all
    PriorityQueue<Integer> minHeap; // top of the heap be the min of all

    public MedianFinder() {
        maxHeap = new PriorityQueue<>((x, y) -> y - x);  
        minHeap = new PriorityQueue<>((x, y) -> x - y); // minHeap.size() = maxHeap.size() - 1 for odd, minHeap.size() = maxHeap.size() for even
    }
    
    public void addNum(int num) {
        if (maxHeap.size() == 0 || num <= maxHeap.peek()) {
            maxHeap.offer(num);
        } else {
            minHeap.offer(num);
        }
        balance();
    }

    private void balance() {
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }

        while (minHeap.size() < maxHeap.size() - 1) {
            minHeap.offer(maxHeap.poll());
        }
    }
    
    public double findMedian() {
        return minHeap.size() < maxHeap.size() ? maxHeap.peek() : ((double) maxHeap.peek() + minHeap.peek()) * 0.5;
    }
}
