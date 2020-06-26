public class LongestSubstringwithAtMostKDistinctCharacters {
    // Version 2
    // sliding window with frequency_map 
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
