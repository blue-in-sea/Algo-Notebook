class DailyTemperatures {
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
}

// * stack store index
// * stack is strictly increasing from bottom to top 
/**
 * ======================
 * index 0  [0] 
 * ======================
 * index 1  [1]
 * ======================
 * index 2  [2]
 * ======================
 * index 3  [3, 2] bottom
 * ======================
 * index 4  [4, 3, 2]
 * ======================
 * index 5  [5, 2]
 * ======================
 * index 6  [6]
 * ======================
 * index 7  [7, 6]
 * ======================
 * [73, 74, 75, 71, 69, 72, 76, 73]
 * [1,   1,  4,  2,  1,  1,  0,  0]
 */
