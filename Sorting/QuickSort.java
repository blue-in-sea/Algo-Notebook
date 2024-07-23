/**
 * The time complexity of the Quick Sort algorithm is O(n*log(n)) on average and in the best case, 
 * but O(n^2) in the worst case. 
 *
 * The worst-case time complexity occurs when the pivot choice consistently results in unbalanced partitions. This is
 * rare and usually happens if the array is already sorted or close to being sorted
 */
class QuickSort {
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
