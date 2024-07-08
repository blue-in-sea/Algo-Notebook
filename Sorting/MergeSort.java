/**
 *                    [38,27,43,10]                        Height: logn
 *               /                    \
 *           [38,27]                   [43,10]
 *         /      \                  /      \               (divide)
 *     [38]      [27]             [43]      [10]          -------------
 *        \       /                 \       /               (merge)
 *         [27,38]                  [10,43]
 *                \                /
 *                  [10,27,38,43]                   <- take maximum n times to sort the n elements 
 *
 */
public class MergeSort {
    /**
     * Merge Sort
     * Divide and Conquer + Combine
     *
     * MergeSort(A, l, r):
     *     if l > r
     *         return
     *     m = (l+r)/2
     *     mergeSort(A, l, m)
     *     mergeSort(A, m+1, r)
     *     merge(A, l, m, r)
     *
     * Time: O(nlogn)
     *       We divide the array into two halves till there is only one element in the array, which will lead to O(logn) steps
     *       And after each division, we merge those respective halves which will take O(n) time each.
     * Space: O(n)
     *      Size of helper array 
     */
    public int[] sortArray(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }

        int n = array.length;
        // allocate helperArray to help merger step
        // so that we guarantee no more than O(n) space is used
        // the space complexity is O(n) in this case
        int[] helper = new int[n];
        mergeSort(array, helper, 0, n - 1);
        return array;
    }

    public void mergeSort(int[] array, int[] helper, int left, int right) {
        while (left >= right) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergeSort(array, helper, left, mid);
        mergeSort(array, helper, mid + 1, right);
        merge(array, helper, left, mid, right);
    }

    public void merge(int[] array, int[] helper, int left, int mid, int right) {
        for (int i = 0; i < array.length; i++) {
            helper[i] = array[i];
        }
        int leftIndex = left;
        int rightIndex = mid + 1;
        while (leftIndex <= mid && rightIndex <= right) {
            if (helper[leftIndex] <= helper[rightIndex]) {
                array[left++] = helper[leftIndex++];
            } else {
                array[left++] = helper[rightIndex++];
            }
        }
        // if we still have some elements at left side, we need to copy them
        while (leftIndex <= mid) {
            array[left++] = helper[leftIndex++];
        }
        // if we still have some elements at right side, no need to copy
        // because they already in their position
    }
}
