/**
 * Find the longest common substring of two given strings.
 * 
 * Assumptions
 * The two given strings are not null     
 *     
 * Examples       
 * S = “abcde”, T = “cdf”, the longest common substring of S and T is “cd” 
 * S = “sweden”, T = “student”, the longest common substring of S and T is “den”    
 */

public class LongestCommonSubstring {
  // Version 1 : DP
  // dp[i][j] = lonest common substring between s and t
  // base case: dp[i][0] = 0 and M[0][j] = 0 
  // induction: dp[i][j] = 1 + M[i-1][j-1]   when s[i-1] == j[j-1]
  //                     = 0                      otherwise -> reset the start
  public String longestCommon(String s, String t) {
    int[][] common = new int[s.length() + 1][t.length() + 1];
    
    int start = 0;
    int maxLen = 0;

    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          common[i][j] = common[i - 1][j - 1] + 1;
        } 
        // else dp[i][j] = 0 (default)

        if (common[i][j] > maxLen) {
          maxLen = common[i][j];
          start = i - maxLen;
        }
      }
    }

    return s.substring(start, start + maxLen);
  }
  
  // Version 2 : Java Solution (no offset)
  public String longestCommon(String s, String t) {
    char[] sa = s.toCharArray();
    char[] ta = t.toCharArray();

    int start = 0;
    int maxLen = 0;
    int[][] common = new int[sa.length][ta.length];
    for (int i = 0; i < sa.length; i++) {
      for (int j = 0; j < ta.length; j++) {
        if (sa[i] == ta[j]) {
          if (i == 0 || j == 0) {
            common[i][j] = 1;
          } else {
            common[i][j] = common[i - 1][j - 1] + 1;
          }
        }

        if (common[i][j] > maxLen) {
          maxLen = common[i][j];
          start = i - maxLen + 1;
        }
      }
    }

    return s.substring(start, start + maxLen);
  }
}

/*
S = “sweden” (i), T = “student” (j), the longest common substring of S and T is “den”

// base case : M[i][0] = 0 and M[0][j] = 0 
// induction : M[i][j] = M[i - 1][j - 1] + 1  if a[i - 1] == a[j - 1]
//                     = 0                    otherwise
      s  t  u  d  e  n  t
  [0, 0, 0, 0, 0, 0, 0, 0]
s [0, 1, 0, 0, 0, 0, 0, 0]
w [0, 0, 0, 0, 0, 0, 0, 0]
e [0, 0, 0, 0, 0, 1, 0, 0]
d [0, 0, 0, 0, 1, 0, 0, 0]
e [0, 0, 0, 0, 0, 2, 0, 0]    // index_i = 6, index_j = 6, maxLen = 3.   start = 6 - 3 = 3, end = 3 + 3 = 6
n [0, 0, 0, 0, 0, 0, 3, 0]    // return s.substring(start = 3 inclusive, end = 6 exclusive);


// M[i][j] = lonest common substring between the first i of a[i]
// and the first j of b[j] (must include the a[i] and b[i])
// base case : M[0][j] = 1 or M[i][0] = 1     if a[i] == a[j]
// induction : M[i][j] = M[i - 1][j - 1] + 1  if a[i] == a[j]
//                     = 0                    otherwise
   s  t  u  d  e  n  t
s [1, 0, 0, 0, 0, 0, 0]
w [0, 0, 0, 0, 0, 0, 0]
e [0, 0, 0, 0, 1, 0, 0]
d [0, 0, 0, 1, 0, 0, 0]
e [0, 0, 0, 0, 2, 0, 0]
n [0, 0, 0, 0, 0, 3, 0]

*/

// public String substring(int beginIndex, int endIndex)
// beginIndex - the beginning index, inclusive.
// endIndex   - the ending index, exclusive.
