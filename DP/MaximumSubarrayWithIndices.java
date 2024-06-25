/**
 * Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum and 
 * the indices of the left and right boundaries of the subarray. If there are multiple solutions, 
 * return the leftmost subarray.
 *
 * Assumptions 
 * The given array is not null and has length of at least 1.
 *
 * Examples
 * {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5. 
 * The indices of the left and right boundaries are 0 and 2, respectively.
 *
 * {-2, -1, -3},      the largest subarray sum is -1
 * The indices of the left and right boundaries are both 1
 */
public class MaximumSubarrayWithIndices {

  // M[i] means the largest sum of subarray ending at index i
  // M[i] = M[i - 1] + array[i]             if M[i - 1] >= 0
  //        (do nothing)
  //      = array[i]                        if M[i - 1] <  0
  //        (reset curLeft = i)

  public int largestSum(int[] array) {
    // base case
    int lastMax = array[0];
    int globalMax = array[0];
    
    // three temp var
    int curLeft = 0;
    // curRight moves with index i
    int globalLeft = 0;
    int globalRight = 0;
    
    for (int i = 1; i < array.length; i++) {
      if (lastMax < 0) {
        lastMax = array[i];
        curLeft = i;
      } else {
        lastMax += array[i];
      }
      
      if (lastMax > globalMax) {
        globalMax = lastMax;
        globalLeft = curLeft;
        globalRight = i;
      }
    }
    int[] res = new int[]{globalMax, globalLeft, globalRight};
    return res;
  }
}
 
