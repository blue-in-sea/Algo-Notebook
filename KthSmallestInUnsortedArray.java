public class KthSmallestInUnsortedArray {
  /**
   * Method 1: Quick Select 
   * Time: avg O(n), worst O(N^2) depends on the pivot selection
   * 
   * @param k: An integer
   * @param nums: An integer array
   * @return: kth smallest element
   */
  
  public int kthSmallest(int k, int[] nums) {
    return quickSelect(nums, 0, nums.length - 1, k - 1);
  }
  
  private int quickSelect(int[] A, int start, int end, int k) {
    if (start == end) {
      return A[start];
    }
    
    int left = start, right = end;
    int pivot = A[start + (end - start) / 2];
    
    while (left <= right) {
      while (left <= right && A[left] < pivot) {
        left++;
      }
      while (left <= right && A[right] > pivot) {
        right--;
      }
      
      if (left <= right) {
        swap(A, left++, right--);
      }
    }
    
    if (right >= k && start <= right) {
      return quickSelect(A, start, right, k);   // [ x ] P [   ]
      
    } else if (left <= k && left <= end) {
      return quickSelect(A, left, end, k);      // [   ] P [ x ]
      
    } else {
      return A[k];                       // [   ] P [   ]  where P is the Kth Smallest, at A[K - 1] position
      
    }
  }
  
  private void swap(int[] A, int left, int right) {
    int temp = A[left];
    A[left] = A[right];
    A[right] = temp;
  }
}
