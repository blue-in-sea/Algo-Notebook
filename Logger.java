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
