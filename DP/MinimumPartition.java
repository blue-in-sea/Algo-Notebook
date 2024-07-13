/**
 * 724 · Minimum Partition
 * Given a set of positive integers, write a function to divide it into two sets S1 and S2 such that the absolute difference 
 * between their sums is minimum.
 *
 * If there is a set S with n elements, then if we assume Subset1 has m elements, Subset2 must have n-m elements and the value of 
 * abs(sum(Subset1) – sum(Subset2)) should be minimum.
 */
// https://blog.csdn.net/weixin_43981315/article/details/105569525
public class MinimumPartition {
    public int findMin(int[] nums) {
        int total = 0;
        for (int n : nums) {
            total += n;
        }
        int half = total / 2;

        boolean[] dp = new boolean[half + 1];
        Arrays.fill(dp, false);

        dp[0] = true;

        for (int i = 0; i < nums.length; i++) {
            for (int j = half; j >= nums[i]; j--) {
                if (dp[j - nums[i]]) {
                    dp[j] = true;
                }
            }
        }

        for (int j = half; j >= 0; j--) {
            if (dp[j]) {
                return (total - j - j);
            }
        }

        return 0;
    }
}
