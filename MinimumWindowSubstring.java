/**
 * 1.2.4 同向双指针3: Minimun Window Substring (L32)
 * see note-book
 */
 
public class MinimumWindowSubstring  {
    /**
     * @param source : A string
     * @param target: A string
     * @return: A string denote the minimum window, return "" if there is no such a string
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
