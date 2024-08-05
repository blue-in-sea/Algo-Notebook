/**
 * Given an unsorted array, find the length of the longest subarray in which the numbers are in ascending order.
 *
 * Assumptions 
 * The given array is not null.
 *
 * Examples
 * {7, 2, 3, 1, 5, 8, 9, 6}, longest ascending subarray is {1, 5, 8, 9}, length is 4.
 * {1, 2, 3, 3, 4, 4, 5},    longest ascending subarray is {1, 2, 3},    length is 3.
 */

public class LongestAscendingSubArray {
  /** 
   * Method 1: Dynamic Programming
   * 
   * M[i] represents the max length of the ascending subarray
   * M[i] = M[i - 1] + 1     a[i] > a[i - 1] 
   *      = 1                otherwise
   * 
   * Time: O(n) for linear scan, Space: O(1)
   */
  public int longestDP(int[] array) {
    // Check if the array is null
    if (array == null) return -1;
    // Check if it is an empty array
    if (array.length == 0) return 0;

    // optimization: we do not need the whole DP-array,  - O(n) space
    // only need to store a global_max and a cur_len     - O(1) space
    int maxLen = 1;
    int curLen = 1;
    for (int i = 1; i <array.length; i++) {
      if (array[i] > array[i - 1]) {
        curLen++;
        maxLen = Math.max(maxLen, curLen);
      } else {
        curLen = 1;
      }
    }
    return maxLen;
  }
  
  /** 
   * Method 2: Sliding Window
   *
   * Window include [within the range from the leftBound to rightBound]
   * the max length of the ascending subarray [two bounds are inclusive]
   *
   * right bound++;         a[i] > a[i - 1] 
   * reset windows;         otherwise
   * (window len = right - left + 1)
   *
   * Time: O(n) for linear scan, Space: O(1)
   */ 
  public int longestSW(int[] array) {
    int maxLen = 1;
    
    int left = 0;
    for (int i = 0; i < array.length; i++) {
      if (i == 0 || array[i] > array[i - 1]) {
        // Ascending part continue, for-loop drive window's right bound
        maxLen = Math.max(maxLen, i - left + 1);
      } else {
        // Ascending part terminate, reset window's left bound
        left = i;
      }
    }
    return maxLen;
  }
}
