/**
 * 123. Best Time to Buy and Sell Stock III (Complete At Most 2 Transactions)
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 * Find the maximum profit you can achieve. You may complete at most two transactions.
 *
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Input: prices = [3,3,5,0,0,3,1,4]
 * Output: 6
 * Explanation: Buy on day 4 (price = 0) and sell on day 6 (price = 3), profit = 3-0 = 3.
 * Then buy on day 7 (price = 1) and sell on day 8 (price = 4), profit = 4-1 = 3.
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Note that you cannot buy on day 1, buy on day 2 and sell them later, as you are engaging multiple transactions at the same time. You must sell before buying again.
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: In this case, no transaction is done, i.e. max profit = 0.
 */
public class BestTimeBuyAndSellStockIII {
    /**
     * Algorithm
     *
     * t1_cost: the minimal cost of buying the stock in transaction #1.
     *          The minimal cost to acquire a stock would be the minimal price value that we have seen so far at each step.
     *
     * t1_profit: the maximal profit of selling the stock in transaction #1.
     *            Actually, at the end of the iteration, this value would be the answer for the first problem in the series,
     *            i.e. Best Time to Buy and Sell Stock.
     *
     * t2_cost: the minimal cost of buying the stock in transaction #2, while taking into account the profit gained from the previous transaction #1.
     *          One can consider this as the cost of reinvestment (Similar with t1_cost)
     *          we try to find the lowest price so far, which in addition would be partially compensated by the profits gained from the first transaction.
     *
     * t2_profit: the maximal profit of selling the stock in transaction #2.
     *            With the help of t2_cost as we prepared so far, we would find out the maximal profits with at most two transactions at each step.
     */
    public int maxProfit(int[] prices) {
        int t1Cost = Integer.MAX_VALUE, t2Cost = Integer.MAX_VALUE;
        int t1Profit = 0, t2Profit = 0;

        for (int price : prices) {
            // the maximum profit if only one transaction is allowed
            t1Cost = Math.min(t1Cost, price);
            t1Profit = Math.max(t1Profit, price - t1Cost);
            // reinvest the gained profit in the second transaction
            t2Cost = Math.min(t2Cost, price - t1Profit);
            t2Profit = Math.max(t2Profit, price - t2Cost);
        }

        return t2Profit;
    }
}
