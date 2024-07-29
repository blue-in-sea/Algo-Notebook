// Find Kth Largest Element in an Array

public class QuickSelect  { 
    // Time: O(nlogn) on average and O(^2) worst case for unluck pivot
    // Space: O(1)
    public int findKthLargest(int[] array, int k) {
        return quickSelect(array, k, 0, array.length - 1);
    }

    private int quickSelect(int[] array, int k, int left, int right) {
        int pivotIndex = partition(array, left, right);

        if (pivotIndex == array.length - k) {
            return array[pivotIndex];
        } else if (pivotIndex > array.length - k) {
            return quickSelect(array, k, left, pivotIndex - 1);
        } else {
            return quickSelect(array, k, pivotIndex + 1, right);
        }
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
        return left + (int) Math.random() * (right - left + 1);
    }

    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }	
}
