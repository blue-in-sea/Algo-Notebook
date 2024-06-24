package FreqMap;

/**
 * 32. Longest Valid Parentheses
 *
 * Input: s = "(()"
 * Output: 2
 * Explanation: The longest valid parentheses substring is "()".
 * Example 2:
 *
 * Input: s = ")()())"
 * Output: 4
 * Explanation: The longest valid parentheses substring is "()()".
 * Example 3:
 *
 * Input: s = ""
 * Output: 0
 */
public class LongestValidParentheses {
    // Method 1: Mono Stack store the index of (unmateched left-parentheses)
    // Time: O(n), Space: O(n)
    public int longestValidParentheses(String s) {
        Deque<Integer> stack = new ArrayDeque<>();

        int max = 0;
        stack.offerFirst(-1);
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.offerFirst(i);
            } else {
                stack.pollFirst();
                if (stack.isEmpty()) {
                    stack.offerFirst(i);
                } else {
                    max = Math.max(max, i - stack.peekFirst());
                }
            }
        }
        return max;
    }
    
    
    // Method 2: 计数器 l & r count # of '(' & # of ')'      interview!!
    // Time: O(2n) -> O(n), Space: O(1)
    public int longestValidParentheses(String s) {
        int l = 0, r = 0, max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(')  {
                l++;
            } else {
                r++;
            }

            if (l == r) {
                max = Math.max(max, 2 * r);
            } else if (r > l) {
                l = r = 0;
            }
        }

        l = r = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == '(') {
                l++;
            } else {
                r++;
            }

            if (l == r) {
                max = Math.max(max, 2 * l);
            } else if (l > r) {
                l = r = 0;
            }
        }

        return max;
    }
    
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
