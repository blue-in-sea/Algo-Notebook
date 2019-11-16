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
   * M[i] represents [within the range from the beginning index to i-th index]
   * the max length of the ascending subarray. [must include the i-th element]
   *
   * M[i] = M[i - 1] + 1     a[i] > a[i - 1] 
   *      = 1                otherwise
   */
   public int longest(int[] array) {
   
   
   }
   
  /** 
   * Method 1: Sliding Window
   *
   * Window include [within the range from the leftBound to rightBound]
   * the max length of the ascending subarray [two bounds are inclusive]
   *
   * right bound++;         a[i] > a[i - 1] 
   * reset windows;         otherwise
   */ 
   public int longest(int[] array) {
   
   }
}
