/**
 * 322. Coin Change
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 *
 * Input: coins = [2], amount = 3
 * Output: -1
 *
 * Input: coins = [1], amount = 0
 * Output: 0
 */
public class CoinChange {
    // Method 1: BottomUp DP (Interview!!)
    // Time: O(amount * n) nested for-loop
    // Space: O(amount) for dp array
    public int coinChange(int[] coins, int amount) {
        // dp[i] represents the fewest number of coins to make up the amount i
        // base case:
        // * amount 0 using 0 coin
        // inductive rule:
        // * if we have amount left to fill
        // * * dp[i] = min(dp[i], dp[i - coins[j] + 1)) where i -> amount, j -> coin_index
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1); // fill the arr with an impossible amount

        dp[0] = 0;
        for (int j = 0; j< coins.length; j++) {
            for (int i = 0; i <= amount; i++) {
                if (i - coins[j] >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }

    // Method 2: TopDown DFS + Memo
    // Time: O(amount * n) where n is # of coins
    // Space: O(amount) stack calls
    public int coinChange(int[] coins, int amount) {
        // Sanity Check 
        if (amount < 1) return 0;
        // 自上而下的动态规划方法
        return coinChange(coins, amount, new int[amount]);
    }

    private int coinChange(int[] coins, int rem, int[] count) {
        // base case: this path is invalid
        if (rem < 0) return -1;
        // base case: rem as 0, successfully exit
        if (rem == 0) return 0;
        // deduplication: directly return, avoid duplicate case
        if (count[rem - 1] != 0) return count[rem - 1];
        int min = Integer.MAX_VALUE;
        // dfs every possible path
        for (int coin : coins) {
            // 用一下coin这个面值的硬币会怎样？res是这个方法的最优情况
            int res = coinChange(coins, rem - coin, count);
            // res < 0 will result res = -1, 
            // res > min not the optimal, exclude
            if (res >= 0 && res < min) {
                min = 1 + res;
            }
        }
        // count[rem - 1]存储着给定金额amount的解
        // 若为Integer.MAX_VALUE则该情况无解
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }
}

/**
 * dp[i] represents the fewest number of coins to make up the amount i
 *
 *  initialize dp[i] as (amount+1) an impossible value
 *  Input: coins = [1, 7, 13], amount = 21
 *  Output: 3
 *  Explanation: 13 + 7 + 1 = 21
 *
 *  in the first round of iteration, coins[j] has value 1
 *  so dp[i] equals value i
 *
 *  in the second round of iteration, it will replace seven "1" to one "7"
 *
 *  in the third round, it will replace amount of 13 (no matter what comb of "1" and "7") to one "13"
 *  dp[amount] is the fewest number of coins consisting that amount
 *
 * ========================================
 * Test Case: coins = [1, 7, 13], amount = 21
 * Return 3 since 1 + 7 + 13 = 21 or 7 + 7 + 7 = 21
 * ========================================
 * 1st Iteration, i: 0 - 21, coin = 1
 * dp[0] = 0
 * dp[1] = min(dp[1], dp[0] + 1) = min(22, 1) = 1
 * dp[2] = min(dp[2], dp[1] + 1) = min(22, 2) = 2
 * …
 * dp[21] = min(dp[21], dp[20] + 1) = min(22, 21) = 21
 * ========================================
 * 2nd Iteration, i: 0 - 21, coin = 7
 * …
 * dp[7] = min(dp[7], dp[0] + 1) = min(7, 1) = 1
 * dp[8] = 2
 * …
 * dp[13] = 7
 * dp[14] = min(dp[14], dp[7] + 1) = min(14, 1 + 1) = 2
 * dp[15] = 3
 * …
 * dp[21] = min(dp[14], dp[14] + 1) = min(14, 2 + 1) = 3
 * ========================================
 * 3rd Iteration, i: 0 - 21, coin = 7
 * …
 * dp[13] = min(dp[13], dp[0] + 1) = min(7, 0 + 1) = 13
 * …
 * dp[21] = min(dp[21], dp[14] + 1) = min(3, 2 + 1) = 3
 */
