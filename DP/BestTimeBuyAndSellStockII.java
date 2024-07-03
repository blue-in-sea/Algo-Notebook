/**
 * 122. Best Time to Buy and Sell Stock II (Hold at Most 1 Share At The Time)
 * 
 * You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
 * On each day, you may decide to buy and/or sell the stock. You can only hold at most one share of
 * the stock at any time. However, you can buy it then immediately sell it on the same day.
 *
 * Find and return the maximum profit you can achieve.
 *
 * Input: prices = [7,1,5,3,6,4]
 * Output: 7
 * Explanation: Buy on day 2 (price = 1) and sell on day 3 (price = 5), profit = 5-1 = 4.
 * Then buy on day 4 (price = 3) and sell on day 5 (price = 6), profit = 6-3 = 3.
 * Total profit is 4 + 3 = 7.
 *
 * Input: prices = [1,2,3,4,5]
 * Output: 4
 * Explanation: Buy on day 1 (price = 1) and sell on day 5 (price = 5), profit = 5-1 = 4.
 * Total profit is 4.
 *
 * Input: prices = [7,6,4,3,1]
 * Output: 0
 * Explanation: There is no way to make a positive profit, so we never buy the stock to achieve the maximum profit of 0.
 */
public class BestTimeBuyAndSellStockII {
    /**
     * DP: Greedy in Simple One Pass - Track slope
     * Instead of looking for every peak following a valley, we can simply go on crawling over the slope
     * and keep on adding the profit obtained from every consecutive transaction.
     *
     * We need not track the costs corresponding to the peaks and valleys along with the maximum profit,
     * but we can directly keep on adding the difference between the consecutive numbers of the array
     * if the second number is larger than the first one, and at the total sum we obtain will be the current maximum profit.
     */

    // Time: O(n)
    // Space: O(1)
    public int maxProfit(int[] prices) {
        int maxprofit = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) {
                maxprofit += prices[i] - prices[i - 1];
            }
        }
        return maxprofit;
    }
}
