/**
 * Given an unsorted integer array, find the subarray that has the greatest sum. Return the sum.
 *
 * Assumptions 
 * The given array is not null and has length of at least 1.
 *
 * Examples
 * {2, -1, 4, -2, 1}, the largest subarray sum is 2 + (-1) + 4 = 5
 * {-2, -1, -3},      the largest subarray sum is -1
 */

public class LargestSubArraySum {
  /** 
   * Method 1: Dynamic Programming  
   * Time: O(n) for linear scan, Space: O(1)
   */
  public int largestSum(int[] array) {
    // Assume Array != null && length >= 1
    // The subarray must contains at leaste one elements
    int[] M = new int[array.length];
    M[0] = array[0];
    // M[i] means the largest sum of subarray ending at index i
    // M[i] = array[i]           if M[i-1] <= 0
    //      = M[i-1] + array[i]  if M[i-1] > 0
    int res = M[0];
    for (int i = 1; i < array.length; i++) {
      M[i] = Math.max(M[i - 1] + array[i], array[i]); 
      res = Math.max(M[i], res);
    }
    return res;
  }
  
  /** 
   * DP: We can reduce the space consumption by recording the latest largest sum
   * Time: O(1) for linear scan, Space: O(1)
   */
  public int largestSum(int[] array) {  
    int maxSum = array[0];
    int lastSum = array[0]; 
    
    for (int i = 1; i < array.length; i++) {
      lastSum = Math.max(lastSum + array[i], array[i]);
      maxSum = Math.max(lastSum, maxSum);
    }
    return maxSum;
  }
}

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

public class LargestSubArraySumWithIndices {
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


// Math. max(int a, int b) returns the greater of two int values. 
// That is, the result is the argument closer to positive infinity. 
// If the arguments have the same value, the result is that same value.
 
