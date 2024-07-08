/**
 * Insertion/Updation of Record:
 * Insert/Update the stock price at the current timestamp in timestampPriceMap.
 * Push the (price, timestamp) pair into the minHeap and maxHeap.
 *
 * Get the Latest Price:
 * Use one variable to keep track of the latest time and get the stock's price at the latest time from timestampPriceMap.
 * Get Minimum and Maximum Stock Price:
 *
 * Get the (price, timestamp) pair from the top of minHeap/maxHeap.
 * If timestampPriceMap[timestamp] != price, it means the price for the current timestamp was updated and that this price is outdated. 
 * So we discard this pair and repeat the above step. Otherwise, return the current price.
 */

// Let n be the number of records in the input stream
// Time: O(nlogn) 
//   * update: for each heap push call O(logn) and for n update calls, it takes O(nlogn)
//   * current: O(1)
//   * max() & min(): O(nlogn)
// Space: O(n)
class StockPriceFluctuation {
    private int latestTime;
    // Store price of each stock at each timestamp.
    private Map<Integer, Integer> timestampPriceMap;
    
    // Store stock prices in sorted order to get min and max price.
    private PriorityQueue<int[]> minHeap, maxHeap;

    public StockPrice() {
        latestTime = 0;
        timestampPriceMap = new HashMap<>();
        minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    }
    
    public void update(int timestamp, int price) {
        // Update latestTime to latest timestamp.
        latestTime = Math.max(latestTime, timestamp);
        
        // Add latest price for timestamp.
        timestampPriceMap.put(timestamp, price);
        
        minHeap.add(new int[]{ price, timestamp });
        maxHeap.add(new int[]{ price, timestamp });
    }
    
    public int current() {
        // Return latest price of the stock.
        return timestampPriceMap.get(latestTime);
    }
    
    public int maximum() {
        int[] top = maxHeap.peek();
        // Pop pairs from heap with the price doesn't match with hashmap.
        while (timestampPriceMap.get(top[1]) != top[0]) {
            maxHeap.remove();
            top = maxHeap.peek();
        }
        
        return top[0];
    }
    
    public int minimum() {
        int[] top = minHeap.peek();
        // Pop pairs from heap with the price doesn't match with hashmap.
        while (timestampPriceMap.get(top[1]) != top[0]) {
            minHeap.remove();
            top = minHeap.peek();
        }
        
        return top[0];
    }
}

/**
 * Your StockPrice object will be instantiated and called as such:
 * StockPrice obj = new StockPrice();
 * obj.update(timestamp,price);
 * int param_2 = obj.current();
 * int param_3 = obj.maximum();
 * int param_4 = obj.minimum();
 */
