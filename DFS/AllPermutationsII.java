public class AllPermutationsII {
  public List<String> permutations(String set) {
    List<String> result = new ArrayList<String>();
    if (set == null) {
      return result;
    }
    // does not the use StringBuilder for path solution 
    // because size of path soln does not change 
    char[] array = set.toCharArray();
    helper(array, 0, result);
    return result;
  }
  // index: at the level of index, we are to determine for the current
  // permutation, which element is positioned at the index
  private void helper(char[] array, int index, List<String> result) {
    if (index == array.length) {
      // base case: at the level of index, we are determine the current indices
      // of the current permutation which element to choose
      result.add(new String(array));
      return;
    }
    
    Set<Character> used = new HashSet<Character> ();
    for (int i = index; i < array.length; i++) {
      // hashset.add(a[i]) will return false if the value a[i]
      // is already in set
      if (used.add(array[i])) {
        swap(array, i, index);
        helper(array, index + 1, result); 
        // has to be (index + 1) to push down the unswap part (trim)
        // # of breaking points decreases as level goes deep
        swap(array, i, index);
      }
    }
  }
  
  private void swap(char[] array, int a, int b) {
    char tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
  }
}
