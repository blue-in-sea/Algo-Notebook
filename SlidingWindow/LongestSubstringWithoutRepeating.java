package SlidingWindow;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 *
 * Input: s = "abcabcbb"
 * Output: 3
 * Explanation: The answer is "abc", with the length of 3.
 *
 * Input: s = "bbbbb"
 * Output: 1
 * Explanation: The answer is "b", with the length of 1.
 *
 * Input: s = "pwwkew"
 * Output: 3
 * Explanation: The answer is "wke", with the length of 3.
 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class LongestSubstringWithoutRepeating {
    /**
     * Method 1: 同向双指针 + Set (visited char)
     *
     * Sliding Window Algo:
     * l = 0
     * for r -> [0, n)
     *    while a[r] is a dup
     *       shrink window left by removing a[l]
     *       l++
     *    until a[r] is not a dup in the current window
     *
     *    set.add(a[r])
     *    update max
     * end-for
     */
     // Time: O(n) where n is the len(s)
     // Space: O(n) size of set
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] arr = s.toCharArray();

        int max = 0;
        int l = 0;
        for (int r = 0; r < arr.length; r++) {

            while (set.contains(arr[r])) {
                // shrink window-l
                set.remove(arr[l]);
                l++;
            }

            set.add(arr[r]);
            max = Math.max(max, r - l + 1);
        }

        return max;
    }

    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    // Fixed l, expand r
    // Fix l: find right-most r such that subarray[l, r] contains no duplicates
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>(); 
        char[] arr = s.toCharArray();

        int max = 0;
        int l = 0;
        for (int r = 0; l < arr.length; l++) {
            
            while (r < arr.length && !set.contains(arr[r])) {
                set.add(arr[r]);
                max = Math.max(max, r - l + 1);
                r++;
            }
            
            set.remove(arr[l]);
        }
        
        return max;
    } 
    
    // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
    /**
     * Method 2: 同向双指针 + Cnt[]
     */
    // 能不能用Hashmap或set来记录当前窗口中的字符？其实只要能保证这些操作时间复杂度是O(1)的方法都是可以的。
    // 但是当字母表比较小的时候，还是建议大家用这种开一个数组的方式来存字母出现次数。
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

    /**
     * Method 3: 同向双指针 + Cnt[]
     */
    // cnt_arr for all lowercase letters 'a-z', no special char or uppercase letters allowed
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
