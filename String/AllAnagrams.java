public class AllAnagrams {
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
