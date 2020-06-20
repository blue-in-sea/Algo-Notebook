/**
 * Given a target integer T and an integer array A sorted in ascending order, 
 * find the index i such that A[i] == T or return -1 if there is no such index. 
 *
 * Assumption: There can be duplicate elements in the array, 
 * and you can return any of the indices i such that A[i] == T.
 */

public class ClassicalBinarySearch {
  /**
   * @param nums: The integer array.
   * @param target: Target to find.
   * @return: The first position of target. Position starts from 0.
   */
  pulic int binarySearch(int[] array, int target) {
    if (array == null || array.length == 0) {
      return -1;
    }
    int left = 0;
    int right = array.length - 1;
    while (left < right - 1) {
      int mid = left + (right - left) / 2;
      if (array[mid] == target) {
        return mid;
      } else if (array[mid] < target) {
        left = mid;
        // or left = mid + 1;
      } else {
        right = mid;
        // or right = mid - 1;
      }
    }
    
    if (array[left] == target) {
      return left;
    }
    if (array[right] == target) {
      return right;
    } 
    return -1;    
  }
}
  
  /**
   * @param nums: The integer array.
   * @param target: Target to find.
   * @return: The first position of target. Position starts from 0.
   */
  pulic int binarySearch(int[] array, int target) {
    if (array == null || array.length == 0) {
      return -1;
    }
    int left = 0;
    int right = array.length - 1;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (array[mid] == target) {
        return mid;
      } else if (array[mid] < target) {
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return -1;
  }
}
