/**
 * 518. Coin Change II
 * You are given an integer array coins representing coins of different denominations and an integer amount 
 * representing a total amount of money.
 *
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any 
 * combination of the coins, return 0.
 *
 * You may assume that you have an infinite number of each kind of coin.
 *
 * Input: amount = 5, coins = [1,2,5]
 * Output: 4
 * Explanation: there are four ways to make up the amount:
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 * Example 2:
 *
 * Input: amount = 3, coins = [2]
 * Output: 0
 * Explanation: the amount of 3 cannot be made up just with coins of 2.
 *
 * Input: amount = 10, coins = [10]
 * Output: 1
 */
class CoinChangeII {
    // Method 1: BottomUp DP (Interview!!)
    // dp[i][j] stores the number of combination of coins used to make amount j from conins[i...n-1]

    // Induction Rule
    // dp[i][j] = dp[i + 1][j] + dp[i][j - coins[i]]
    // Base Case
    // dp[n][0] = 1 since we can always make up the amount 0 by not selecting any coins. 

    // 总数背包
    // dp[j] stores the number of combination of coins used to make amount j 
    // dp[0] = 1;
    // dp[j] = dp[j] + dp[j - coins[i]]
    //                   用掉 coins[i] 的情况

    // 滚动数组
    // for i from [0, n)
    //     for j from [0, amount]
    //         dp[j] += dp[j - coins[i]]

    public int change(int amount, int[] coins) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        
        dp[0] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = coins[i]; j <= amount; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        return dp[amount];
    }
}
