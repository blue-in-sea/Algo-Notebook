public class KSmallestInUnsortedArray {
  // Method 1: K sized maxHeap
  public int[] kSmallest(int[] array, int k) {
    // corner case
    if (array.length == 0 || k == 0) {
      return new int[0];
    }
    PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        if (o1.equals(o2)) {
          return 0;
        }
        return o1 > o2 ? -1 : 1;
      }
    });
    for (int i = 0; i < array.length; i++) {
      if (i < k) {
        // offer the first k element into maxHeap
        // Note if you want to utilize heapify(), the only thing you can do
        // is to call a certain constructor of PriorityQueue
        maxHeap.offer(array[i]);
      } else if (array[i] < maxHeap.peek()) {
        // for the other elements, only offer it into the maxHeap if it is smaller
        // than the max value in the maxHeap.
        maxHeap.poll();
        maxHeap.offer(array[i]);
      }
    }
    int[] result = new int[k];
    for (int i = k - 1; i >= 0; i--) {
      result[i] = maxHeap.poll();
    }
    return result;
  }
  
  // Method 2: Quick Select
  public int[] kSmallest(int[] array, int k) {
    // corner case
    if (array.length == 0 || k == 0) {
      return new int[0];
    }
    // quick-select to find the kth smallest elements 
    // after calling, the first k elements in the array
    // are the k smallest ones (but not sorted)
    quickSelect(array, 0, array.length - 1, k - 1);
    // copy out the first k elements and sort them
    int[] result = Arrays.copyOf(array, k);
    Arrays.sort(result);
    return result;
  }
  private void quickSelect(int[] array, int left, int right, int target) {
    // like quickSort, we need to do the partition using pivot value
    int mid = partition(array, left, right);
    // unlike quickSort, we only need quickSelect on 
    // at the most one partition
    if (mid == target) {
      return;
    } else if (target < mid) {
      // only need to recursivly call quickSelect on the left partition 
      // if the k-th smallest one is in the left partition
      quickSelect(array, left, mid - 1, target);
    } else {
      // only need to recursivly call quickSelect on the right partition 
      // if the k-th smallest one is in the right partition
      quickSelect(array, mid + 1, right, target);
    }
  }
  private int partition(int[] array, int left, int right) {
    int pivot = array[right]; // last index
    int start = left;
    int end = right - 1;
    while (start <= end) {
      if (array[start] < pivot) {
        start++;
      } else if (array[end] >= pivot) {
        end--;
      } else {
        swap(array, start++, end--);
      }
    }
    swap(array, start, right);
    return start;
  }
  private void swap(int[] array, int a, int b) {
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
  }
}
