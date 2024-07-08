package Sorting;

/**
 * 76. Minimum Window Substring (同向双指针: 移动窗口)
 * Given two strings s and t of lengths m and n respectively, return the minimum window
 * substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
 *
 * Input: s = "ADOBECODEBANC", t = "ABC"
 * Output: "BANC"
 * Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.
 *
 * Input: s = "a", t = "a"
 * Output: "a"
 * Explanation: The entire string s is the minimum window.
 *
 * Input: s = "a", t = "aa"
 * Output: ""
 * Explanation: Both 'a's from t must be included in the window.
 * Since the largest window of s only has one 'a', return empty string.
 */
public class MinimumWindowSubstring  {
   /**
     * sliding window + dict (freqMap)
     *
     * 1. build dictT from string t <k: char, value: cnt of char in string t>
     * 2. Initialize match = 0, minLen = MAX_INT, index = 0
     * 2. Sliding Window
     *    l = 0,
     *    for r -> (0 ... n)
     *       if s[r] not in dictT
     *          continue
     *       else
     *         update dictT
     *         if found a match char: match++
     *         while found a match substring
     *            update minLen, index
     *            shrink window-left for smaller size 
     *               (when shrinking, recover the dictT)    
     *    end-for
     *  return s.substring(index, index + minLen)
     */

    // Time: O(S + T) where s for len(s), t for len(t)
    // Space: O(T)
    public String minWindow(String s, String t) {
        if (s.length() == 0 || t.length() == 0) {
            return "";
        }

        Map<Character, Integer> dictT = buildDictT(t);

        int match = 0;
        int minLen = Integer.MAX_VALUE;
        int index = -1;

        int l = 0; 
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            Integer cnt = dictT.get(c);

            // if char did not in the dictT
            if (cnt == null) {
                continue;
            }

            dictT.put(c, cnt - 1);
            if (cnt == 1) {
                // 1 -> 0: found a matched char in dictT
                match++;
            }

            // found a match word for t
            while (match == dictT.size()) {
                if (r - l + 1 < minLen) {
                    minLen = r - l + 1;
                    index = l;
                }

                // shrink the window left
                char lc = s.charAt(l++);
                Integer lcCnt = dictT.get(lc);

                // if left most char did not in the dictT
                if (lcCnt == null) {
                    continue;
                }
                // otherwise, recover the dictT
                dictT.put(lc, lcCnt + 1);
                if (lcCnt == 0) {
                    // 0 -> 1: remove a matched char 
                    match--;
                }
            }
        }

        return index == -1 ? "" : s.substring(index, index + minLen);
    }

    private Map<Character, Integer> buildDictT(String t) {
        Map<Character, Integer> dictT = new HashMap<>();
        for (Character c : t.toCharArray()) {
            dictT.put(c, dictT.getOrDefault(c, 0) + 1);
        }
        return dictT;
    }

    // ========================================================================

 
    /**
     * sliding window + cnt[]
     */
    public String minWindow(String source , String target) {
        char[] s = source.toCharArray();
        char[] t = target.toCharArray();
        if (t.length == 0) {
            return "";
        }
        
        int[] cntS = new int[256]; // cnt appearances for each character in the window (moving)
        int[] cntT = new int[256]; // how many times each character appears in T (fixed)
        int K = 0;
        for (int i = 0; i < 256; i++) {
            cntS[i] = cntT[i] = 0; // write own initial value, avoid to use default!
        }
        
        for (char c : t) {
            cntT[c]++;
            if (cntT[c] == 1) {
                K++;
            }
        }
        
        int C = 0; // number of T's unique characters the window contains
        int resL = -1, resR = -1; // final return result
        
        for (int l = 0, r = 0; l < s.length; l++) {
            // insert into window
            while (r < s.length && C < K) {
                cntS[s[r]]++;
                // phase jump
                if (cntS[s[r]] == cntT[s[r]]) {
                    C++;
                }
                
                r++;
            }
            
            if (C == K) {
                if (resL == -1 || r - l < resR - resL) {
                    resL = l;
                    resR = r;
                }
            }
            
            // s[l] is required to be removed from the window
            cntS[s[l]]--;
            if (cntS[s[l]] == cntT[s[l]] - 1) {
                C--;
            }
        }
        
        // s[l...(r-1)]
        if (resL == -1) {
            return "";
        } else {
            return source.substring(resL, resR);
        }
    }
}
