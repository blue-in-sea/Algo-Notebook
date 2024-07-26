/**
 * 14. Longest Common Prefix
 * Write a function to find the longest common prefix string amongst an array of strings.
 * If there is no common prefix, return an empty string "".
 *
 * Input: strs = ["flower","flow","flight"]
 * Output: "fl"
 * Example 2:
 *
 * Input: strs = ["dog","racecar","car"]
 * Output: ""
 * Explanation: There is no common prefix among the input strings.
 */
class LongestCommonPrefix {
    // Method 1: Horizontal scanning
    // Time: O(S) where S is the sum of all characters in all strings
    // Space: O(1) we only used constant extra space
    public String longestCommonPrefix(String[] strArr) {
        if (strArr.length == 0) return "";

        String prefix = strArr[0];
        for (int i = 1; i < strArr.length; i++) {
            String s = strArr[i];
            while (s.indexOf(prefix) != 0) {
                prefix = prefix.substring(0, prefix.length() - 1);
                if (prefix.isEmpty()) return "";
            }
        }

        return prefix; 
    }

     // Method 2: Horizontal scanning
    // Time: O(S) where S is the sum of all characters in all strings
    // Space: O(1). We only used constant extra space
}
