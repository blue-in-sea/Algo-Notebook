/**
 * 96. Unique Binary Search Trees (LeetCode)
 * Given an integer n, return the number of structurally unique BST's (binary search trees) which has exactly n nodes
 * of unique values from 1 to n.
 *
 * Input: n = 3
 * Output: 5
 *
 * Input: n = 1
 * Output: 1
 */
public class FindNumberOfBSTsGenerated {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }
}
