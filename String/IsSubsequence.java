public class IsSubsequence {
    // check if s is subsequence of t
    // Time: O(n), Space: O(n) where n is the length of t-srting
    public boolean isSubsequence(String s, String t) {
        // corner case: empty string is subsequence of any string 
        if (s.length() == 0) return true;
        int i = 0, j = 0;
        while (j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                if (i == s.length()) return true;
            }
            j++;
        }
        return false;
    }
}
