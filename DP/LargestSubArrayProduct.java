/**
 * 98. Largest SubArray Product
 * Given an unsorted array of doubles, find the subarray that has the greatest product. Return the product.
 *
 * Assumptions
 * The given array is not null and has length of at least 1.
 *
 * {2.0, -0.1, 4, -2, -1.5}, the largest subarray product is 4 * (-2) * (-1.5) = 12
 */
public class LargestSubArrayProduct {
    public double largestProduct(double[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // dp[i][0] is the minimum product of the subarray ending in nums[i]
        // dp[i][1] is the maximum product of the subarray ending in nums[i]
        double dp[][] = new double[len][2];

        dp[0][0] = nums[0];
        dp[0][1] = nums[0];
        for (int i = 1; i < len; i++) {
            if (nums[i] >= 0) {
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][0]);
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][1]);
            } else {
                dp[i][0] = Math.min(nums[i], nums[i] * dp[i - 1][1]);
                dp[i][1] = Math.max(nums[i], nums[i] * dp[i - 1][0]);
            }
        }
        // iterate through dp[i][1] to calculate the global max
        double res = dp[0][1];
        for (int i = 1; i < len; i++) {
            res = Math.max(res, dp[i][1]);
        }
        return res;
    }
}
