/**
 * https://leetcode.com/problems/logger-rate-limiter/description/
 */
class Logger {
    // Time: O(1) - Operation of the map is constant 
    // Space: O(N) - N is the size of all incoming msg

    // key: msg, value: timestamp of expiry 
    private Map<String, Integer> expiry;
    private int TIMEOUT = 10;

    public Logger() {
        expiry = new HashMap<String, Integer>();
    }
    
    public boolean shouldPrintMessage(int timestamp, String msg) {
        if (expiry.containsKey(msg) && expiry.get(msg) > timestamp) {
            return false;
        }
        expiry.put(msg, timestamp + TIMEOUT);
        return true;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

/**
 * Design a logger system that receives a stream of messages along with their timestamps. Each unique message should 
 * only be printed at most every 10 seconds (i.e. a message printed at timestamp t will prevent other identical 
 * messages from being printed until timestamp t + 10).
 *
 * All messages will come in chronological order. Several messages may arrive at the same timestamp.
 *
 * Example 1:
 * Input
 * ["Logger", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", "shouldPrintMessage", 
 * "shouldPrintMessage", "shouldPrintMessage"]
 * [[], [1, "foo"], [2, "bar"], [3, "foo"], [8, "bar"], [10, "foo"], [11, "foo"]]
 * Output
 * [null, true, true, false, false, false, true]
 *
 * Explanation
 * Logger logger = new Logger();
 * logger.shouldPrintMessage(1, "foo");  // return true, next allowed timestamp for "foo" is 1 + 10 = 11
 * logger.shouldPrintMessage(2, "bar");  // return true, next allowed timestamp for "bar" is 2 + 10 = 12
 * logger.shouldPrintMessage(3, "foo");  // 3 < 11, return false
 * logger.shouldPrintMessage(8, "bar");  // 8 < 12, return false
 * logger.shouldPrintMessage(10, "foo"); // 10 < 11, return false
 * logger.shouldPrintMessage(11, "foo"); // 11 >= 11, return true, next allowed timestamp for "foo" is 11 + 10 = 21
 */
