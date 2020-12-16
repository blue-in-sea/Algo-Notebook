public class ValidPalindromeII {
    // Time: O(n), Space: O(1)
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
