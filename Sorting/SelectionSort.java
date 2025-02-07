public class SelectionSort {
    /**
     * Selection sort is a sorting algorithm that selects the smallest element from an unsorted list in each iteration
     * and places that element at the beginning of the unsorted list.
     *
     * Time: O(n^2)
     * Space: O(1)
     */
    public int[] selectionSort(int[] arr) {
        if (arr == null || arr.length == 0) return arr;

        for (int i = 0; i < arr.length - 1; i++) {
            int minIdx = i;

            for (int j = i + 1; j < arr.length; j++) {

                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // swap
            swap(arr, minIdx, i);
        }

        return arr;
    }

    private void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
