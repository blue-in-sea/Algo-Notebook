public class StringReplaceBasic {
  public String replace(String input, String s, String t) {
    char[] array = input.toCharArray();
    if (s.length() >= t.length()) {
      return replaceShorter(array, s, t);
    }
    return replaceLonger(array, s, t);
  }

  private String replaceShorter(char[] array, String s, String t) {
    // case 1: if a[f..f+2] == pattern, a[s] = each char at t, s += t.length, f += s.length
    // case 2: if a[f..f+2] != pattern, a[s] = a[f], s++, f++
    int slow = 0;
    int fast = 0;
    while (fast <= array.length - 1) {
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

  private String replaceLonger(char[] array, String s, String t) {
    // case 1: if a[f..f-2] == pattern, a[s] = each char at t, s -= t.length, f -= s.length
    // case 2: if a[f..f-2] != pattern, a[s] = a[f], s--, f--
    List<Integer> matches = getAllMatches(array, s); // get all matched pattern 
    char[] res = new char[array.length + matches.size() * (t.length() - s.length())];
    int slow = res.length - 1;
    int fast = array.length - 1;
    int last = matches.size() - 1; // marked the last occurrence of the pattern

    while (fast >= 0) {
      if (last >= 0 && fast == matches.get(last)) {
        copySubstring(res, slow - t.length() + 1, t);
        slow -= t.length();
        fast -= s.length();
        last--;
      } else {
        res[slow--] = array[fast--];  // attention: copy to new array
      }
    }
    return new String(res);
  }

  private boolean equalSubstring(char[] array, int fromIdx, String s) {
    for (int i = 0; i < s.length(); i++) {
      if (array[fromIdx + i] != s.charAt(i)) {
        return false;
      }
    }
    return true;
  }

  private void copySubstring(char[] array, int fromIdx, String t) {
    for (int i = 0; i < t.length(); i++) {
      array[fromIdx + i] = t.charAt(i);
    }
  }

  private List<Integer> getAllMatches(char[] array, String s) {
    // matches store end position of all pattern occurences
    List<Integer> matches = new ArrayList<>();
    int i = 0;
    while (i <= array.length - s.length()) {
      if (equalSubstring(array, i, s)) {
        // we record the matches substring's end index instead of start index
        matches.add(i + s.length() - 1);  // add one end index
        i += s.length();
      } else {
        i++;
      }
    }
    return matches;
  }
}
