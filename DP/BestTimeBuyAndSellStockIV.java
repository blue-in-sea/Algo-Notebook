/**
 * 188. Best Time to Buy and Sell Stock IV (Complete At Most k Transactions)
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day, and an integer k.
 *
 * Find the maximum profit you can achieve. You may complete at most k transactions:
 * i.e. you may buy at most k times and sell at most k times.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Input: k = 2, prices = [2,4,1]
 * Output: 2
 * Explanation: Buy on day 1 (price = 2) and sell on day 2 (price = 4), profit = 4-2 = 2.
 *
 * Input: k = 2, prices = [3,2,6,5,0,3]
 * Output: 7
 * Explanation: Buy on day 2 (price = 2) and sell on day 3 (price = 6), profit = 6-2 = 4. Then buy on day 5 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 */
public class BestTimeBuyAndSellStockIV {
    /**
     * DP (2 ways to think of it)
     * 1. a brute-force approach and reduce unnecessary calculations
     * 2. treat the stored results as "states", and try to jump from the starting state to the ending state
     *
     * Brutal Force to DP:
     * To find the maximum profit from prices[i] in k transactions:
     * Iterate all the possible combinations of k transactions, and then find the best combination.
     * As for those with less than k, they are similar and can be considered later.
     *
     * A transaction consists of two parts: buying and selling (2 states) =>
     * we need to find 2k points in the stock line, k points for buying, and k points for selling.
     *
     *   2K          N!
     * C     = ---------------   (not good, factorial --> TLE)
     *   N      (2K)!(N-2K)!
     *
     * Special case, if k * 2 > n, no limit on trades given the size of prices
     * Therefore, all we need to do is to iterate each day, and if the price of day i rises,
     * buy the stock in i-1th day and sell it at ith day.
     *
     * Among all combinations, to memo the repeated the calculation => key to find
     * 1. day number
     * 2. transactions we made before
     * 3. value (aka. balance used to calculate profit)
     *
     * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
     *
     * dp[day_number][used_transaction_number][stock_holding_status]
     *      n                 k                      2-states
     *
     * The value of dp[i][j][l] represents the best profit we can have
     * 1. at the end of the i-th day
     * 2. with j remaining transactions to make and l stocks.
     *
     * Transition equation
     * (base case)
     *   dp[0][0][0] = 0
     *   dp[0][1][1] = -prices[0]
     *
     * (inductive rule)
     *   dp[i][j][1] = dp[i−1][j][1] (Keep holding the stock)
     *   dp[i][j][0] = dp[i−1][j][0] (Keep not holding the stock)
     *
     *   dp[i][j][1] = dp[i−1][j−1][0] − prices[i] (Buying, when j>0)
     *   dp[i][j][0] = dp[i−1][j][1]   + prices[i]   (Selling)
     *
     *   combine them together
     *   dp[i][j][1] = max(dp[i−1][j][1], dp[i−1][j−1][0] − prices[i])
     *   dp[i][j][0] = max(dp[i−1][j][0], dp[i−1][j][1] + prices[i])
     */
    
    // Time: O(nk) if k * 2 <= n,  Space: O(nk) for dp array 
    // Time: O(n) if k * 2 > n for one pass, Space: O(1) for dp array 
    public int maxProfit(int k, int[] prices) {
        int n = prices.length;

        // corner case
        if (n <= 0 || k <= 0) {
            return 0;
        }

        if (k * 2 >= n) {
            int res = 0;
            for (int i = 1; i < n; i++) {
                res += Math.max(0, prices[i] - prices[i - 1]);
            }
            return res;
        }

        // dp[i][used_k][ishold] = balance
        // ishold: 0 nothold, 1 hold
        int[][][] dp = new int[n][k + 1][2];

        // initialize the array with -inf
        // we use -1e9 here to prevent overflow
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                dp[i][j][0] = -1000000000;
                dp[i][j][1] = -1000000000;
            }
        }

        // set starting value
        dp[0][0][0] = 0;
        dp[0][1][1] = -prices[0];

        // fill the array
        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // transition equation
                dp[i][j][0] = Math.max(
                        dp[i - 1][j][0],
                        dp[i - 1][j][1] + prices[i]
                );
                // you can't hold stock without any transaction
                if (j > 0) {
                    dp[i][j][1] = Math.max(
                            dp[i - 1][j][1],
                            dp[i - 1][j - 1][0] - prices[i]
                    );
                }
            }
        }

        int res = 0;
        for (int j = 0; j <= k; j++) {
            res = Math.max(res, dp[n - 1][j][0]);
        }

        return res;
    }
}
