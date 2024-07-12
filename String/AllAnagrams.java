/**
 * 438. Find All Anagrams in a String
 * Given two strings s and p, return an array of all the start indices of p's anagrams in s. 
 * You may return the answer in any order.
 *
 * An Anagram is a word or phrase formed by rearranging the letters of a different word or phrase,
 * typically using all the original letters exactly once.
 *
 * Input: s = "cbaebabacd", p = "abc"
 * Output: [0,6]
 * Explanation:
 * The substring with start index = 0 is "cba", which is an anagram of "abc".
 * The substring with start index = 6 is "bac", which is an anagram of "abc".
 *
 * Input: s = "abab", p = "ab"
 * Output: [0,1,2]
 * Explanation:
 * The substring with start index = 0 is "ab", which is an anagram of "ab".
 * The substring with start index = 1 is "ba", which is an anagram of "ab".
 * The substring with start index = 2 is "ab", which is an anagram of "ab".
 */
public class AllAnagrams {
     // Time: O(ns)
     // Space: 0(26) size of the cntMap
     public List<Integer> findAnagrams(String s, String p) {
        int ns = s.length(), np = p.length();
        if (ns < np) return new ArrayList<>();

        Map<Character, Integer> pCntMap = buildPCntMap(p);
        Map<Character, Integer> sCntMap = new HashMap<>();

        List<Integer> res = new ArrayList<>();
        
        // A valid window must has the FIXED size of np for [l ... r] 
        // Once r exceed np, if there exist a valid substring (agrams) 
        // then l-bound of window (aka. res_index) = r - np + 1
        for (int r = 0; r < ns; r++) {
            char c = s.charAt(r);
            sCntMap.put(c, sCntMap.getOrDefault(c, 0) + 1);
            
            // shrink window by remove 1 char
            if (r >= np) {
                char cl = s.charAt(r - np);

                if (sCntMap.get(cl) == 1) {
                    // 1->0
                    sCntMap.remove(cl);
                } else {
                    sCntMap.put(cl, sCntMap.get(cl) - 1);
                }
            }

            // Compare sCntMap on the sliding window
            // with tCntMap
            if (pCntMap.equals(sCntMap)) {
                res.add(r - np + 1);
            }
        } 

        return res;
    }

    private Map<Character, Integer> buildPCntMap(String p) {
        Map<Character, Integer>  map = new HashMap<>();
        for (char c : p.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    // *******************************************************************************
    // Find all anagrams of String s in String l
    // return all start indices 
    // Assumption: s, l are not null, s is not empty
    public List<Integer> allAnagrams(String s, String l) {
        List<Integer> res = new ArrayList<Integer>();
        if (s.length() > l.length()) {
            return res;
        }
        // This map record for each distinct character,
        // how many of each needed to build an anagram
        // (e.g.) s = "abbc", map = {'a': 1, 'b': 2, 'c': 1}
        // when we get an instance of 'a' in string l, we decrement cnt by 1
        // whenever cnt down to 0, we obtain a match letter 
        Map<Character, Integer> map = buildAnagramsMap(s);
        // match stores the # of distinct letters that've matched
        // we find an anagram when all value in the map down to 0
        // (A.K.A) match == map.size() !!
        int match = 0;
        // sliding window [l ... r] of fixed len
        for (int i = 0; i < l.length(); i++) {
            // case 1: when window size is smaller than s.length(), no need to reset 
            // case 2: when window size is larger or equal to s.length(), need to reset window 
            char cur = l.charAt(i);
            Integer cnt = map.get(cur);
            if (cnt != null) {
                map.put(cur, cnt - 1);
                // when cnt is from 1 to 0, we found one match 
                if (cnt == 1) {
                    match++;
                }
            }

            if (i >= s.length()) {
                cur = l.charAt(i - s.length());  // offset
                cnt = map.get(cur);
                if (cnt != null) {
                    map.put(cur, cnt + 1);     // recover the map
                    // when cnt is from 0 to 1, we lost one match 
                    if (cnt == 0) {
                        match--;
                    }
                }
            }

            // post-processing inside the for-loop
            if (match == map.size()) {
                res.add(i - s.length() + 1);
            }
        }

        return res;
    }

    private Map<Character, Integer> buildAnagramsMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }
}
