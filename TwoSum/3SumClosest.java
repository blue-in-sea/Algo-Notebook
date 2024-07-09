/**
 * 16. 3Sum Closest
 * Given an integer array nums of length n and an integer target, find three integers in nums such that the sum is closest to target.
 * Return the sum of the three integers.
 *
 * Input: nums = [-1,2,1,-4], target = 1
 * Output: 2
 * Explanation: The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).
 *
 * Input: nums = [0,0,0], target = 1
 * Output: 0
 * Explanation: The
 */
public class 3SumClosest {
    // Similar: search in sorted matrix
    // Time: O(n^2) for O(nlogn) for sorting, O(n^2) for finding the pairs 
    // space: O(1) since in place
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int res = nums[0] + nums[1] + nums[2];
        for(int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[left] + nums[right] + nums[i];
                // update the result sum
                if(Math.abs(target - sum) < Math.abs(target - res)) {
                    res = sum;
                } 
                // two pointers 
                if(sum > target) {
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    return res;
                }
            }
        }
        return res;
    }
}
