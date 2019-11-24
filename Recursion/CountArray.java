/**
 * Given an array A of length N containing all positive integers from [1...N]. 
 * How to get an array B such that B[i] represents: 
 * how many elements A[j] (j > i) in array A that are smaller than A[i].
 *
 * Assumption: the given array A is not null.
 * A = { 4, 1, 3, 2 }, we should get B = { 3, 0, 1, 0 }.
 */

public class CountArray {
  private int[] indexArray;
  private int[] countArray;  // the actual return array
  private int[] helper;      // helpe with merge sorting indices 
  
  /**
   * Time Complexity: O(nlogn)
   * Space Complexity: O(3n) --> O(n)
   */
  public int[] countArray(int[] array) { 
    indexArray = initialIndexArray(array);
    countArray = new int[array.length];
    helper = new int[array.length];
   
    mergeSort(array, 0, array.length - 1);
    return countArray;
  }
  
  /**
   * 针对数组 array 指定的区间 [left, right] 进行归并排序，在排序的过程中完成统计任务
   *
   * @param array
   * @param left
   * @param right
   */
  private void mergeSort(int[] array, int left, int right) {
    if (left >= right) {
      return; // 数组只有一个元素的时候，没有比较，不统计
    }
    int mid = left + (right - left) / 2;
    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    
    // 归并排序的优化，同样适用于该问题
    // 如果索引数组有序，就没有必要再继续计算了
    if (array[indexArray[mid]] > array[indexArray[mid + 1]]) {
      merge(array, left, mid, right);
    }
  }
  
  /**
   * merge 谁小移动谁
   * [left, mid] 是排好序的
   * [mid + 1, right] 是排好序的
   *
   * @param array
   * @param left
   * @param mid
   * @param right
   */  
  private void merge(int[] array, int left, int mid, int right) {
    for (int i = left; i <= right; i++) {
      helper[i] = indexArray[i];
    }
    
    int i = left;
    int j = mid + 1; 
    int cur = left;
    
    while (i <= mid) {
      if (j > right || array[helper[i]] <= array[helper[j]]) {
        countArray[helper[i]] += (j - mid - 1); 
        indexArray[cur++] = helper[i++];
      } else {
        // no need to update count
        indexArray[cur++] = helper[j++];
      }
    }
  }
  
  /**
   * IndexArray store the indices
   * merge sort the array but records the sequence of 
   * original indices after sorting
   */
  private int[] initialIndexArray(int[] array) {
    int[] indices = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      indices[i] = i;
    }
    return indices;
  }
}
