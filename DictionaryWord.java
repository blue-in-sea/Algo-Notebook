/** 
 * Assumption:
 * input is not null or empty
 * dict is not null and is not empty
 * all the strings in dict are not null or empty
 */

public class DictionaryWord {
  /** 
   * M[i] represents whether the substring from the 0-th index to i-th index
   * can be composed by dictionary words
   *
   * M[i] = M[j] && (input[j, i] is in dic?)
   *      左大段  &&  右小段
   */
  public boolean dictionaryWord(String input, String[] dict) {
    Set<String> dictSet = toSet(dict);
    int n = input.length();
    boolean[] canBreak = new boolean[n + 1];
    canBreak[0] = true;
    /**
     * Note: sometimes it will be handy to have such index matching
     * canBreak[i] -> represents index (i-1) in input
     *             -> also the subString (0,i)
     */
    for (int i = 1; i < n + 1; i++) {
      for (int j = 0; j < i; j++) {
        if (canBreak[j] && dictSet.contains(input.substring(j, i))) {
          canBreak[i] = true;
          break;
        }
      }
    }
    return canBreak[n];
  }
  
  private Set<String> toSet(String[] dict) {
    Set<String> set = new HashSet<>();
    for (String word : dict) {
      set.add(word); 
    }
    return set; 
  }
}
