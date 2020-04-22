public class LongestCommonSubstring {
  // Version 1 : DP
  // M[i][j] = lonest common substring between the first i of a[i]
  // and the first j of b[j] (must include the a[i-1] and b[i-1])
  public String longestCommon(String s, String t) {
    int[][] longest = new int[s.length() + 1][t.length() + 1];
    
    int start = 0;
    int maxLen = 0;
    // base case: M[i][0] = 0 and M[0][j] = 0 
    // M[i][j] = 1 + M[i-1][j-1]   if a[i-1] == b[j-1]
    //         = 0                 otherwise
    for (int i = 1; i <= s.length(); i++) {
      for (int j = 1; j <= t.length(); j++) {
        if (s.charAt(i - 1) == t.charAt(j - 1)) {
          longest[i][j] = longest[i - 1][j - 1] + 1;
        } 

        if (longest[i][j] > maxLen) {
          maxLen = longest[i][j];
          start = i - maxLen;
        }
      }
    }

    return s.substring(start, start + maxLen);
  }
}
