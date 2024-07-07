/**
 * Given an array of integers, sort the array in ascending order and return it.
 * [5,2,3,1] -> [1,2,3,5]
 */
public class BubbleSort {
    /**
     * Selection Sort
     * compares two adjacent elements and swaps them until they are in the intended order.
     *
     * for i [0 -> n)
     *    for j [0 -> n - 1)
     *        if (a[j] > a[j + 1])
     *            swap (j, j+1)
     *
     * Time: O(n^2)
     * Space: O(1)
     */
    public int[] sortArray(int[] nums) {
        if (nums == null || nums.length == 0) return nums;

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    swap(nums, j, j + 1);
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
