/**
 * 625. Longest subarray contains only 1s
 * Given an array of integers that contains only 0s and 1s and a positive integer k, you can flip at most k 0s to 1s,
 * return the longest subarray that contains only integer 1 after flipping.
 *
 * Assume
 * 1. Length of given array is between [1, 20000].
 * 2. The given array only contains 1s and 0s.
 * 3. 0 <= k <= length of given array.
 *
 * Example 1:
 * Input: array = [1,1,0,0,1,1,1,0,0,0], k = 2
 * Output: 7
 * Explanation: flip 0s at index 2 and 3, then the array becomes [1,1,1,1,1,1,1,0,0,0], so that the length of longest
 * subarray that contains only integer 1 is 7.
 *
 * Example 2:
 * Input: array = [1,1,0,0,1,1,1,0,0,0], k = 0
 * Output: 3
 * Explanation: k is 0, so you can not flip any 0 to 1, then the length of longest subarray that contains only integer
 * 1 is 3.
 */
public class LongestSubarrayContainsOnly1s {
    // Time: O(n) since left and right points will all move n times for the sliding window 
    // Space: O(1)
    public int longestConsecutiveOnes(int[] nums, int k) {
        // corner case check
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length <= k) {
            return nums.length;
        }
        // sliding windows: [l, r) used as much as k flips
        int cntZero = 0;
        int res = 0;

        for (int l = 0, r = 0; l < nums.length; l++) {
            while (r < nums.length) {
                if (nums[r] == 1) {
                    r++;
                    res = Math.max(res, r - l);
                } else if (cntZero < k) {
                    cntZero++;
                    r++;
                    res = Math.max(res, r - l);
                } else {
                    break;
                }
            }

            if (nums[l] == 0) {
                cntZero--;
            }
        }

        return res;
    }
}
