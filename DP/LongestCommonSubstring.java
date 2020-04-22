/**
 * Find the longest common substring of two given strings.
 * 
 * Assumptions
 * The two given strings are not null     
 *     
 * Examples       
 * S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd” 
 * S = “student”, T = “sweden”, the longest common substring of S and T is “den”    
 */

public class LongestCommonSubstring {
  // Version 1 : DP
  // M[i][j] = lonest common substring between the first i of a[i]
  // and the first j of b[j] (must include the a[i-1] and b[i-1])
  public String longestCommon(String s, String t) {
    int[][] common = new int[s.length() + 1][t.length() + 1];
    
    int start = 0;
    int maxLen = 0;
    // base case: M[i][0] = 0 and M[0][j] = 0 
    // M[i][j] = 1 + M[i-1][j-1]   if a[i-1] == b[j-1]
    //         = 0                 otherwise
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          common[i][j] = common[i - 1][j - 1] + 1;
        } 

        if (common[i][j] > maxLen) {
          maxLen = common[i][j];
          start = i - maxLen;
        }
      }
    }

    return s.substring(start, start + maxLen);
  }
}

/**
 *     (i) 0 1 2 3 4 5 6 7           // index offset by 1
 * (j)     0 s t u d e n t
 *  0      0 0 0 0 0 0 0 0           // base case: M[0][j] = 0
 *  1   s  0 1 0 0 0 0 0 0 
 *  2   w  0 0 0 0 0 0 0 0
 *  3   e  0 0 0 0 0 1 0 0
 *  4   d  0 0 0 0 1 0 0 0
 *  5   e  0 0 0 0 0 2 0 0
 *  6   n  0 0 0 0 0 0 3 0          // index_i = 6, index_j = 6, maxLen = 3.   start = 6 - 3 = 3, end = 3 + 3 = 6
 * // base case M[i][0] = 0         // return s.substring(start = 3 inclusive, end = 6 exclusive);
 */

// public String substring(int beginIndex, int endIndex)
// beginIndex - the beginning index, inclusive.
// endIndex   - the ending index, exclusive.
