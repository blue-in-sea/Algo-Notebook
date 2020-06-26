public class LongestSubstringwithAtMostKDistinctCharacters {
    // Version-1
    // Sliding window with cnt_array
    // [l, r] represents the sliding window that contain at most k distinct char
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (s == null || s.length() == 0 || k == 0) {
            return 0;
        }
        
        int left = 0, right = 0, unique = 0;
        int cnt[] = new int[256];
        int res = 0;
        
        while (right < s.length()) {
            // expand right border: when the char at right has  
            // the first appearrance in the sliding window
            // unique++ and cnt frequency++
            if (cnt[s.charAt(right)] == 0) {
                unique++;
            }
            cnt[s.charAt(right)]++;
            right++;
            
            // when the window contains more than k distinct char
            // shrink left then update unique and cnt_table
            while (unique > k) {
                cnt[s.charAt(left)]--;
                if (cnt[s.charAt(left)] == 0) {
                    unique--;
                }
                left++;
            }
            
            // update the global_max of the window's length
            res = Math.max(res, right - left);
        }
        
        return res;
    }
    
    // Version-2
    // Sliding window with frequency_map 
    // [l, r] represents the sliding window that contain at most k distinct char
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, right = 0;
        int res = 0;
        
        while (right < s.length()) {
            char curr = s.charAt(right);
            map.put(curr, map.getOrDefault(curr, 0) + 1);
            right++;
            
            // when the window contains more than k distinct char
            // shrink left then update unique and cnt_table
            while (map.size() > k) {
                char past = s.charAt(left);
                map.put(past, map.get(past) - 1);
                if (map.get(past) == 0) {
                    map.remove(past);
                }
                left++;
            }
            
            // update the global_max of the window's length
            res = Math.max(res, right - left);
        }
        
        return res;
    }
}
