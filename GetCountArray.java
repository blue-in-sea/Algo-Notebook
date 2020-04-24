/**
 * Given an array A of length N containing all positive integers from [1...N]. 
 * How to get an array B such that B[i] represents how many elements A[j] 
 * (j > i) in array A that are smaller than A[i].
 *
 * Assumption: The given array A is not null.
 * Examples: A = { 4, 1, 3, 2 }, we should get B = { 3, 0, 1, 0 }.
 * Requirement: Time complexity = O(nlogn).
 */

public class GetCountArray {
  public int[] countArray(int[] array) {
    // The indexArray contains the indices in the original array
    // and it will be sorted corresponding number in the input array
    int[] indexArr = initialIndexArr(array);
    int[] countArr = new int[array.length]; // the actual return array
    int[] helper = new int[array.length];   // helper arr in merge sort
    
    mergeSort(array, indexArr, countArr, helper, left, 0, array.length - 1);
    return countArr;
  }
  
  private void mergeSort(int[] array, int[] indexArr, int[] countArr, int[] helper, int left, int right) {
    if (left >= right) {
      return;
    }
    int mid = left + (right - left) / 2;
    mergeSort(array, indexArr, countArr, helper, left, mid);
    mergeSort(array, indexArr, coutArr, helper, mid + 1, right);
    merger(array, indexArr, coutArr, helper, left, mid, right);
  }
  
  private void merge(int[] array, int[] indexArr, int[] countArr, 
                     int[] helper, int left, int mid, int right) {
    copyArr(indexArr, helper, left, right);
    int i = left;
    int j = mid + 1;
    int cur = left;
    // when sorting the indexArr, we use the corresponding value
    // in the original arr
    while (i  <= mid) {
      // 谁小移谁, 记录移动元素的 index 
      if (j > right || array[helper[i]] <= array[helper[j]]) {
        // counter: 在 merge sort 的过程中，有多少个比我小的数从我的右边移动到了我的左边
        countArr[helper[i]] += (j - mid - 1);  
        indexArr[cur++] = helper[i++];
      } else {
        // no need to update the counter
        indexArray[cur++] = helper[j++];
      } 
    }
  }
  
  private int[] copyArr(int[] array, int[] helper, int left, int right) {
    for (int i = left; i <= right; i++) {
      helper[i] = indexArr[i];
    }
    return helper;
  }
  
  private int[] initialindexArr(int[] array) {
    int[] indices = new int[array.length];
    for (int i = 0; i < array.length; i++) {
      indices[i] = i;
    }
    return indices;
  }
}

/**
 *              4 1 3 2
 *               /   \
 *            4  1   3  2
 *           /   \   /   \
 *        4(0) 1(0) 3(0) 2(0)
 * conuter (semantic meaning) : in the process of merger, how many ele that is 
 * smaller than me have move from my right-side 右边 to my left-side 左边
 * ===========================================================================
 *          \   /    \   /
 *        1(0) 4(1) 2(0) 3(1)
 *              i              j
 *               \    /
 *         1(0) 2(0) 3(1) 4(1+2)
 *
 *   xxxx X xxxx   yyyy Y yyyyyy
 *        i             j
 * 
 *  res : [xyyxyxyyyxx]
 *  case 1: if X < Y
 *     res.push_back(X)     // [4]  
 *     X's counter += j
 *     i++
 *  case 2: if X > Y
 *     res.push_back(Y)     // [2, 3]
 *     Y's counter += 0
 *     j++
 */
