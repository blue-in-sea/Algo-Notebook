class DailyTemperatures {
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

// stack = bottom [(0, 75), (1, 71), (2, 69)] top 
// * stack store index
// * stack is strictly increasing from bottom to top 
