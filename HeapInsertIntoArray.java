/**
 * Root is at index 0 in array (base case).
 * Left child of i-th node is at (2*i + 1)th index.
 * Right child of i-th node is at (2*i + 2)th index.
 * Parent of i-th node is at (i-1)/2 index (recursively shift up in min Heap)
 */
public class HeapInsertIntoArray {
  // Binary Heap: insert O(log n), deleteMin O(log n), removeMin O(log n), findMin O(1)
  public int[] offerHeap(int[] array, int ele) {
    // assume array is not null and has length >= 1
    int n = array.length;
    array[n - 1] = ele; // insert to the lastIndex
    shiftUp(array, n - 1);
    return array;
  }

  private void shiftUp(int[] array, int idx) {
    if (idx == 0) {
      return;
    }
    int parentIdx = (idx - 1) / 2;
    if (array[parentIdx] > array[idx]) {
      swap(array, idx, parentIdx);
      shiftUp(array, parentIdx);
    }
  }

  private void swap(int[] array, int small, int large) {
    int tmp = array[small];
    array[small] = array[large];
    array[large] = tmp;
  }
}
// defintion ref: https://www.cs.cmu.edu/~adamchik/15-121/lectures/Binary%20Heaps/heaps.html
// code ref: http://www.algolist.net/Data_structures/Binary_heap/Insertion
