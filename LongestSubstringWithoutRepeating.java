/**
 * 1.2.3 同向双指针2: Longest Substring Without Repeating (L384)
 * Fix l: find right-most r such that subarray[l, r] contains no duplicates
 * Note!! i 往右的时候, j 一定不会往右
 */

/**
 * Algo 
 * 同向双指针，需要记录两个指针中每个字符出现的次数（均<=1)
 * 只要右指针下一个字符出现次数=0，就可以向右移动
 */

public class LongestSubstringWithoutRepeating {
    /**
     * @param s: a string
     * @return: an integer
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] arr = s.toCharArray();
        
        int res = 0;
        for (int l = 0, r = 0; l < arr.length; l++) {
            
            while (r < arr.length && !set.contains(arr[r])) {
                set.add(arr[r]);
                res = Math.max(res, r - l + 1);
                r++;
            }
            
            set.remove(arr[l]);
        }
        
        return res;
    }

    // ASCII Table: https://www.cs.cmu.edu/~pattis/15-1XX/common/handouts/ascii.html
    public int lengthOfLongestSubstring(String s) {
        int[] cnt = new int[256]; 
        char[] arr = s.toCharArray();
        
        int res = 0;
        for (int l = 0, r = 0; l < arr.length; l++) {
            
            while (r < arr.length && cnt[arr[r]] == 0) {
                cnt[arr[r]]++; 
                res = Math.max(res, r - l + 1);
                r++;
            }
            
            cnt[arr[l]] = 0;
        }
        
        return res;
    }
  
    // cnt_arr for all lower letters case
    public int lengthOfLongestSubstring(String s) {
        int[] cnt = new int[26]; 
        char[] arr = s.toCharArray();
        
        int res = 0;
        for (int l = 0, r = 0; l < arr.length; l++) {
            
            while (r < arr.length && cnt[arr[r] - 'a'] == 0) {
                cnt[arr[r] - 'a']++; 
                res = Math.max(res, r - l + 1);
                r++;
            }
            
            cnt[arr[l] - 'a'] = 0;
        }
        
        return res;
    }
}
