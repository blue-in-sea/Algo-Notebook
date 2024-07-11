/**
 * Given an array of integers temperatures represents the daily temperatures, return an array answer such that
 * answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 *
 * If there is no future day for which this is possible, keep answer[i] == 0 instead.
 *
 * Input: temperatures = [73,74,75,71,69,72,76,73]
 * Output: [1,1,4,2,1,1,0,0]
 * Example 2:
 *
 * Input: temperatures = [30,40,50,60]
 * Output: [1,1,1,0]
 * Example 3:
 *
 * Input: temperatures = [30,60,90]
 * Output: [1,1,0]
 */
class DailyTemperatures {
    // 1. MonoStack (interview!!)
    // Time: O(N) looping
    // Space: O(N) stack
    public int[] dailyTemperatures(int[] temperatures) {
        if (temperatures == null || temperatures.length == 0) {
            return new int[0];
        }

        int n = temperatures.length;
        int res[] = new int[n];

        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            int curT = temperatures[i];
            // pop until the T is not hotter than the T set at the top of stack 
            while (!stack.isEmpty() && temperatures[stack.peek()] < curT) {
                int prevDay = stack.pop();
                res[prevDay] = i - prevDay; 
            }
            stack.push(i); // where i is curDay at the curIndex
        }

        return res;
    }
    /**
     * stack store index
     * stack peak [coldest -> warmest] bottom
     *
     * peak    stack        ele
     * ==============================
     * index 0  [0]         [73]
     * ==============================
     * index 1  [1]         [74]
     * ==============================
     * index 2  [2]         [75]
     * ==============================
     * index 3  [3, 2]      [71, 75]
     * ==============================
     * index 4  [4, 3, 2]   [69, 71, 75]
     * ==============================
     * index 5  [5, 2]      [69, 71, 75]
     * ==============================
     * index 6  [6]         [76]
     * ==============================
     * index 7  [7, 6]      [73, 76]
     * ==============================
     *  0    1   2   3   4   5   6   7
     * [73, 74, 75, 71, 69, 72, 76, 73]
     * [1,   1,  4,  2,  1,  1,  0,  0]
     */

    // 2. DP: Linear scan from tail to end
    // Time: O(N) scan the array for once
    // Space: O(1)
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int dp[] = new int[n]; // store the days to wait to get a warmer T

        // base case: 
        dp[n - 1] = 0;
        int globalHot = dp[n - 1];

        // induction 
        for (int i = n - 1; i >= 0; i --) {
            int curT = temperatures[i];
            
            if (curT >= globalHot) {
                globalHot = curT;

            } else {
                int cnt = 1; // days to wait to get a warmer T
                
                while (temperatures[i + cnt] <= curT) {
                    cnt += dp[i + cnt];
                }
                dp[i] = cnt;
            }
        }
        return dp;
    }
}
