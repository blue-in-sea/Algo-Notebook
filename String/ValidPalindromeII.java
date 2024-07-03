/**
 * 680. Valid Palindrome II 
 *
 * Given a string s, return true if the s can be palindrome after deleting at most one character from it.
 *
 * Input: s = "aba"
 * Output: true
 * Example 2:
 *
 * Input: s = "abca"
 * Output: true
 * Explanation: You could delete the character 'c'.
 *
 * Input: s = "abc"
 * Output: false
 */
public class ValidPalindromeII {
    // Algo (相向双指针) 首位相向而行
    // Time: O(n)
    // Space: O(1)
    public boolean validPalindrome(String s) {
        // assume s is null and s has len equal 0 return true
        int l = 0, r = s.length() - 1;
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) {
                return isPalindrome(s, l + 1, r) || isPalindrome(s, l, r - 1);
            } else {
                l++;
                r--;
            }
        }
        return true;
    }

    private boolean isPalindrome(String s, int l, int r) {
        while (l <= r) {
            if (s.charAt(l) != s.charAt(r)) {
                return false;
            } else {
                l++;
                r--;
            }
        }
        return true;
    }
}
