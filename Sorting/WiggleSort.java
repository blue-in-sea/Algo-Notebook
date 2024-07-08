/**
 * 280. Wiggle Sort
 * Given an integer array nums, reorder it such that nums[0] <= nums[1] >= nums[2] <= nums[3]....
 *
 * Input: nums = [3,5,2,1,6,4]
 * Output: [3,5,1,6,2,4]
 * Explanation: [1,6,2,5,3,4] is also accepted.
 *
 * Input: nums = [6,6,5,6,3,8]
 * Output: [6,6,5,6,3,8]
 */
class WiggleSort {
    /**
     * Algo: Wiggle Sort
     * 1. Sort the array 
     * 2. Iterate over every odd index of array starting from index i = 1 until len - 2.
     *    We iterate until the second last element because the last element has no next element to swap with.
     *       We keep incrementing the index by 2 to move only over odd indices.
     *          Swap the element at odd index i with the adjacent element at index i + 1.
     */

   // Time: O(nlogn)
   // Space: O(1)
    public void wiggleSort(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length - 1; i += 2) {
            swap(nums, i, i + 1);
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
