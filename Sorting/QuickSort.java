/**
 * Quick Sort Algorithm:
 * 1. Choose a Pivot:
 * Select an element from the array as the pivot (e.g., the first element, last element, or a random element).
 *
 * 2. Partition the Array:
 * Rearrange the array so that all elements less than the pivot are on its left, and all elements greater than the
 * pivot are on its right. The pivot is now in its correct sorted position.
 *
 * 3. Recursively Sort Sub-arrays
 * Recursively apply the above steps to the left and right subarrays.
 */
class QuickSort {
    /**
     * Time: O(n*log(n)) - average or best case, O(n^2) in the worst case.
     * Space: O(log n) due to the recursion stack.
     *
     * Worst-case time complexity occurs: when the pivot choice consistently results in unbalanced partitions.
     * This is and usually happens if the array is already sorted or close to being sorted.
     * (Randomized pivot selection to avoid worst-case behavior)
     */
    public int[] sortArray(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(array, left, right);
        quickSort(array, left, pivotIndex - 1);
        quickSort(array, pivotIndex + 1, right);
    }

    private int partition(int[] array, int left, int right) {
        int pivotIndex = findPivotIndex(left, right);
        int pivot = array[pivotIndex];
        
        // Move pivot to the last position
        swap(array, pivotIndex, right); 
        
        int i = left;
        int j = right - 1;
        while (i <= j) {
            if (array[i] <= pivot) {
                i++;
            } else if (array[j] >= pivot) {
                j--;
            } else {
                swap(array, i, j);
                i++;
                j--;
            }
        }
        // Move pivot back to the correct place 
        swap(array, i, right);
        
        return i;
    }

    private int findPivotIndex(int left, int right) {
        return left + (int) (Math.random() * (right - left + 1));
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
