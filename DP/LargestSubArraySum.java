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
    
}

// Math. max(int a, int b) returns the greater of two int values. 
// That is, the result is the argument closer to positive infinity. 
// If the arguments have the same value, the result is that same value.
  
    
 
