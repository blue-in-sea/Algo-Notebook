public class LongestValidParentheses {
    // Method 3: Dynamic Programming 
    // Time: O(n), Space: O(n)
    public int longestValidParentheses(String s) {
        int max = 0;
        int n = s.length();

        int dp[] = new int[n];

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
            }

            max = Math.max(max, dp[i]);
        }

        return max;
    }
}

// Case-1: if s[i] = ‘)’ & s[i - 1] = ‘(’ then we found ()
//         dp[i - 2] + 2
// Case-2: if s[i] = ‘)’ & s[i - 1] = ‘)’ 
//         then if s[i − dp[i−1] − 1]=‘(’
//         dp[i] = dp[i − 1] + dp[i − dp[i−1] − 2] + 2
