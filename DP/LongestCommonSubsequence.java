/**
 * 1143. Longest Common Subsequence
 * Given two strings text1 and text2, return the length of their longest common subsequence. If there is no common 
 * subsequence, return 0.
 *
 * A subsequence of a string is a new string generated from the original string with some characters (can be none) 
 * deleted without changing the relative order of the remaining characters.
 *
 * For example, "ace" is a subsequence of "abcde".
 * A common subsequence of two strings is a subsequence that is common to both strings.
 *
 * Input: text1 = "abcde", text2 = "ace" 
 * Output: 3 "ace" 
 *
 * Input: text1 = "abc", text2 = "abc"
 * Output: 3 "abc" 
 *
 * Input: text1 = "abc", text2 = "def"
 * Output: 0  no such common subsequence
 */
class LongestCommonSubsequence {
    /**
     * dp[i][j] = the longest common subsequence at s.charAt(i) for j -> [1, tn)
     * base case dp[i][0] = 0, dp[0][j]  = 0
     * induction: dp[i][j] = dp[i - 1][j - 1] + 1                when s[i - 1] == s[j - 1]
     *            dp[i][j] = max( dp[i][j - 1], dp[i - 1][j] )   otherwise   
     *
     * Proof 
     * If the characters match where s[i - 1] == s[j - 1], 
     *     the len of LCS increase by 1
     * If the characters do not match, 
     *     the len of LCS is the maximum of the two possible cases
     *          1) Exclude the current character of s and consider the LCS of s[0..i-1] and t[0..j]
     *          2) Exclude the current character of t and consider the LCS of s[0..i] and t[0..j-1]
     *
     *         c  b  a  b  d  f  e            T = “cbabdfe”
     *    [[0, 0, 0, 0, 0, 0, 0, 0],
     * a   [0, 0, 0, 1, 1, 1, 1, 1],
     * b   [0, 0, 1, 1, 2, 2, 2, 2],
     * c   [0, 1, 1, 1, 2, 2, 2, 2],
     * d   [0, 1, 1, 1, 2, 3, 3, 3],
     * e   [0, 1, 1, 1, 2, 3, 3, 4]]
     *
     * S = “abcde”, T = “cbabdfe”, the longest common subsequence "abde"
     */
    
    // Time: O(sn * tn) for filling the DP table
    // Space: O(sn * tn) for the size of DP table
    public int longestCommonSubsequence(String s, String t) {
        int sn = s.length();
        int tn = t.length();

        int[][] dp = new int[sn + 1][tn + 1];

        for (int i = 1; i <= sn; i++) {
            for (int j = 1; j <= tn; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[sn][tn];
    }
}

