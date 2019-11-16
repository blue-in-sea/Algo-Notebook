public class DictionaryWord {
  /** 
   * M[i] represents whether the substring from the 0-th index to i-th index
   * can be composed by dictionary words
   *
   * 	M[i] = M[j] && (M[j, i] is in dic?)
	 *       左大段  &&  右小段
   */
  public boolean dictionaryWord(String input, String[] dict) {
    Set<Integer> dictSet = toSet(dict);
    int n = input.length;
    boolean[] canBreak = new int[n + 1];
    for (int i = 0; i < n + 1; i++) {
      for (int j = 0; j < i; j++) {
        if (canBreak[j] && set.contains(input.substring(j, i))) {
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
