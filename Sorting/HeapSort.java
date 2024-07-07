/**
 * Heap Sort
 *
 * 1. Build the binary heap:
 * Organize the elements of the array into a max binary heap such that the parent node is either greater than or equal to its children nodes.
 * In the resulting max binary heap we will have the largest element at the root node.
 *
 * 2. Swap the root node and the last element:
 * Swap the root node (which is the largest element) with the last element in the heap. So that
 * we place the largest element at the end, thus, trying to sort in ascending order.
 *
 * 4. Rebuild the heap:
 * Rebuild the heap with the new root node, and while NOR considering the already swapped elements from the array to satisfy the heap property.
 */
class HeapSort {
    // Time: O(nlogn) to traverse the height of the complete binary tree made using n elements, which leads to O(logn) time operations, and the heapify is done n times, once for each element.
    // Space complexity: O(logn) the recursive stack will take O(logn) space
    public int[] sortArray(int[] nums) {
        heapSort(nums);
        return nums;
    }

    private void heapSort(int[] arr) {
        int n = arr.length;
        // Build max heap; heapify (top-down) all elements except leaf nodes.
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(arr, n, i);
        }

        // Traverse elements one by one, to move current root to end, and
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            // call max heapify on the reduced heap.
            heapify(arr, i, 0);
        }
    }

    // Function to heapify a subtree (in top-down order) rooted at index i.
    private void heapify(int[] arr, int n, int i) {
        // Initialize largest as root 'i'.
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        // If left child is larger than root.
        if (left < n && arr[left] > arr[largest]) {
            largest = left;
        }

        // If right child is larger than largest so far.
        if (right < n && arr[right] > arr[largest]) {
            largest = right;
        }

        // If largest is not root swap root with largest element
        // Recursively heapify the affected subtree (i.e. move down).
        if (largest != i) {
            swap(arr, i, largest);
            heapify(arr, n, largest);
        }
    }

    private void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
