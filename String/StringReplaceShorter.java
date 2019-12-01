public class StringReplaceShorter {
  // case 1: if a[f .. f+2] == pattern, a[s] = this char in the replacement, s += t.length(), f += s.length()
  // case 2: if a[f .. f+2] != pattern, a[s] = a[f], s++, f++
  public String replace(String input, String s, String t) {
    // Assumptions: input, s, t are not null, s is not empty
    char[] array = input.toCharArray();
/*    
    if (s.length() < t.length()) {
      replaceLonger(array, s, t);
    }
*/
    return replaceShorter(array, s, t);
  }

  private String replaceShorter(char[] array, String s, String t) {
    // we reuse the input char array since we do not need expand
    // the original array size
    // slow and fast pointer both move from left to right direction
    int slow = 0;
    int fast = 0;
    while (fast < array.length) {
      // when we find a match of s on the substring starting from 
      // the fast pointer, we copy the t at slow pointer 
      if (fast <= array.length - s.length() && equalSubstring(array, fast, s)) {
        copySubstring(array, slow, t);
        slow += t.length();
        fast += s.length();
      } else {
        array[slow++] = array[fast++];
      }
    }
    return new String(array, 0, slow);
  }

  // check if the substring starting at fromIdx is same as s (pattern)
  private boolean equalSubstring(char[] array, int fromIdx, String s) {
    for (int i = 0; i < s.length(); i++) {
      if (array[fromIdx + i] != s.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  // copy the string t to result at fromIdx
  private void copySubstring(char[] array, int fromIdx, String t) {
    for (int i = 0; i < t.length(); i++) {
      array[fromIdx + i] = t.charAt(i);
    }
  }
}
