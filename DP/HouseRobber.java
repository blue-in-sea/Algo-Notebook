/**
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount
 * of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses
 * have security system connected and it will automatically contact the police if two adjacent houses
 * were broken into on the same night.
 *
 * Given a list of non-negative integers representing the amount of money of each house, determine
 * the maximum amount of money you can rob tonight without alerting the police.
 *
 * Example
 * Input: nums = [1,2,3,1], Output: 4
 * Rob house 1 (money = 1) and then rob house 3 (money = 3). Total amount you can rob = 1 + 3 = 4.
 */

public class HouseRobber {
    public int rob(int[] nums) {

        int prevMax = 0;
        int currMax = 0;
        for (int house : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + house, currMax);
            prevMax = temp;
        }
        return currMax;
    }

    public int robDP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        if (len == 1) return nums[0];

        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < len; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[len - 1];
    }
}

/**
 * dp[i] represents the max amount of money we can rob (including a[i])
 *
 * Base Case
 * 1. dp[0] = a[0] only has one house, rob this house
 * 2. dp[1] = Math.max(a[0], a[1]) only has two house, rob the rich one
 *
 * Inductive Rule
 * dp[i] = Math.max(dp[i - 2] + a[i], dp[i - 1])
 */
