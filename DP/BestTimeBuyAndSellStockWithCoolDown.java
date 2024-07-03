/**
 * 309. Best Time to Buy and Sell Stock with Cooldown
 *
 * You are given an array prices where prices[i] is the price of a given stock on the ith day.
 *
 * Find the maximum profit you can achieve. You may complete as many transactions as you like
 * (i.e., buy one and sell one share of the stock multiple times) with the following restrictions:
 *
 * After you sell your stock, you cannot buy stock on the next day (i.e., cooldown one day).
 * Note: You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 *
 * Input: prices = [1,2,3,0,2]
 * Output: 3
 * Explanation: transactions = [buy, sell, cooldown, buy, sell]
 *
 * Input: prices = [1]
 * Output: 0
 */
public class BestTimeBuyAndSellStockWithCoolDown {
    /**
     * DP: State Machine with transition (Sold, Held, CoolDown)
     *
     * Each element in each array represents the maximal profits that we could gain
     * at the specific price point i with the specific state.
     *
     * sold[i]  = hold[i−1] + price[i]
     * held[i]  = max( held[i−1], reset[i−1] − price[i] )
     * reset[i] = max( reset[i−1], sold[i−1] )
     *
     * sold[i]: the previous state of sold can only be held
     * held[i]: the previous state of held could also be held, or reset
     * reset[i]: the previous state of reset could either be reset or sold
     */

    // Time: O(n) for one pass
    // Space: O(1)
    public int maxProfit(int[] prices) {
        int sold = Integer.MIN_VALUE, held = Integer.MIN_VALUE, reset = 0;

        for (int price : prices) {
            int preSold = sold;

            sold = held + price;
            held = Math.max(held, reset - price);
            reset = Math.max(reset, preSold);
        }

        return Math.max(sold, reset);
    }
}
