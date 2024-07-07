/**
 * Given an array of integers, sort the array in ascending order and return it.
 * [5,2,3,1] -> [1,2,3,5]
 */
public class SelectionSort {
    /**
     * Selection Sort
     * select min for each index by scanning through rest of the array
     *
     * for i [0 -> n)
     *    for j [i -> n)
     *       if (a[i] > a[j])
     *           swap (i, j)
     *
     * Time: O(n^2)
     * Space: O(1)
     */
    public int[] selectionSort(int[] nums) {
        if (nums == null || nums.length == 0) return nums;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    swap(nums, i, j);
                }
            }
        }

        return nums;
    }

    private void swap(int nums[], int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
