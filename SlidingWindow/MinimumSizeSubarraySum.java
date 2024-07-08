/**
 * 209. Minimum Size Subarray Sum
 * Given an array of positive integers nums and a positive integer target, return the minimal length of a subarray
 * whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
 *
 * Input: target = 7, nums = [2,3,1,2,4,3]
 * Output: 2
 * Explanation: The subarray [4,3] has the minimal length under the problem constraint.
 *
 * Input: target = 4, nums = [1,4,4]
 * Output: 1
 *
 * Input: target = 11, nums = [1,1,1,1,1,1,1,1]
 * Output: 0
 */
class MinimumSizeSubarraySum {
    /**
     * Algo 同向双指针
     * Initialize min = MAX_INT, sum = 0 (curSum in the window)
     * l = 0
     * for r -> [0 ... n)
     *    每次 r 前进时，更新 sum
     *    while 只要当前 sum >= target
     *        shrink window left
     *        把移除窗口的数从 sum 里减掉
     * return min
     */

    // Time: O(n) for r moves n steps, l moves n steps, total add up to 2N into the time complexity
    // Space: O(1)
    public int minSubArrayLen(int target, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int sum = 0;
        int min = Integer.MAX_VALUE; // or nums.length + 1
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            // advance the window-right
            sum += nums[r];

            while (sum >= target) {
                // shrink the window-left
                min = Math.min(min, r - l + 1);
                sum -= nums[l];
                l++;
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
