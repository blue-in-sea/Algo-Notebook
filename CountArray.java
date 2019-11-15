/* *
 * Given an array A of length N containing all positive integers from [1...N]. 
 * How to get an array B such that B[i] represents: 
 * how many elements A[j] (j > i) in array A that are smaller than A[i].
 *
 * Assume the given array A is not null.
 * A = { 4, 1, 3, 2 }, we should get B = { 3, 0, 1, 0 }.
 *
 * Time Complexity: O(nlogn)
 * Space Complexity: O(3n) --> O(n)
 */

public class CountArray {
  private int[] indexArray;
  private int[] countArray;  // the actual return array
  private int[] helper;      // helpe with merge sorting indices 
  
  public int[] countArray(int[] array) { 
    // assume the array is not null;
    indexArray = initialindexArray(array);
    countArray = new int[array.length];
    helper = new int[array.length];
    mergeSort();
    mergeSort();
    merge();
    return countArray;
  }
}
