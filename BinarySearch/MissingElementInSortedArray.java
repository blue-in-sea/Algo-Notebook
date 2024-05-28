
/**
 * 1060. Missing Element in Sorted Array
 * Given an integer array nums which is sorted in ascending order and all of its elements are unique and given also
 * an integer k, return the kth missing number starting from the leftmost number of the array.
 *
 * Example
 * Input: nums = [4,7,9,10], k = 1
 * Output: 5
 * Explanation: The first missing number is 5.
 *
 * Input: nums = [4,7,9,10], k = 3
 * Output: 8
 * Explanation: The missing numbers are [5,6,8,...], hence the third missing number is 8.
 *
 * Input: nums = [1,2,4], k = 3
 * Output: 6
 * Explanation: The missing numbers are [3,5,6,7,...], hence the third missing number is 6.
 * 
 * Assumptions:
 * nums is sorted in ascending order, and all the elements are unique.
 * 1 <= nums.length <= 5 * 104
 * 1 <= nums[i] <= 107
 * 1 <= k <= 108
 */
class MissingElementInSortedArray {
    // Time: O(NlogN)
    // Space: O(1)

    // Solution 1
    public int missingElement(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Calculate the number of missing elements between nums[mid] and nums[0] as nums[mid] - nums[0] - mid.
            if (nums[mid] - nums[0] - mid < k) { 
                // If the count of missing elements calculated in last step 
                // is less than k, set left = mid.
                left = mid + 1;
            } else{
                // Otherwise, set right = mid - 1.
                right = mid - 1;
            }
        }
        
        return nums[0] + k + right;
    }

    // Solution 2
    public int missingElement(int[] nums, int k) {
        int n = nums.length;
        int left = 0, right = n - 1;
        
        while (left < right) {
            int mid = right - (right - left) / 2;
            if (nums[mid] - nums[0] - mid < k) {
                left = mid;
            } else{
                right = mid - 1;
            }
        }
        
        return nums[0] + k + left;
    }
}
