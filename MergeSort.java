/**
 * Given an array of integers, sort the elements in the array in ascending order. 
 * The merge sort algorithm should be used to solve this problem.
 */

public class MergeSort {
  /**
   * Time: O(nlogn) for logn level while sort for n elements 
   * Space: O(n) for the helper array
   */
  public int[] mergeSort(int[] array) {
    // corner case 
    if (array == null || array.length <= 1) {
      return array;
    }
    // allocate a helperArray to help with merge step
    // so that we guarantee no more than O(n) space is used
    // the spece complexity in this case is O(n) 
    int[] helper = new int[array.length];
    mergeSortR(array, helper, 0, array.length - 1);
    return array;
  }
  
  private void mergeSortR(int[] array, int[] helper, int left, int right) {
    while (left >= right) {
      return;
    }
    int mid = left + (right - left) / 2;
    mergeSortR(array, helper, left, mid);
    mergeSortR(array, helper, mid + 1, right);
    merge(array, helper, left, mid, right);
  }
  
  private void merge(int[] array, int[] helper, int left, int mid, int right) {
    for (int i = 0; i < array.length; i++) {
      helper[i] = array[i];
    }
    int i = left;     // left index
    int j = mid + 1;  // right index
    while (i <= mid && j <= right) {
      if (helper[i] <= helper[j]) {
        array[left++] = helper[i++];
      } else {
        array[left++] = helper[j++];
      }
    }
    // if we have some elements at left side, we need copy them 
    while (i <= mid) {
      array[left++] = helper[i++];
    }
    // if we still have some elements at right side, no need to copy
    // because they already in their position 
  }
}
