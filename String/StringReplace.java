/**
 * String Replace (LaiCode 172)
 * Given an original string input, and two strings S and T, replace all occurrences of S in input with T.
 *
 * Assume: input S and T are not null, S is not empty string
 *
 * input = "appledogapple", S = "apple", T = "cat", input becomes "catdogcat"
 * input = "dodododo", S = "dod", T = "a", input becomes "aoao"
 */
public class StringReplace {
  // Method 1: not using any String/StringBuilder utility,
  // and using char[] to do it in-place.
  public String replace(String input, String s, String t) {
    // Assumptions: input, s, t are not null, s is not empty
    char[] array = input.toCharArray();
    if (s.length() >= t.length()) {
      return replaceShorter(array, s, t);
    }
    return replaceLonger(array, s, t);
  }

  // case 1: if a[f .. f+2] == pattern, a[s] = this char in the replacement, s += t.length(), f += s.length()
  // case 2: if a[f .. f+2] != pattern, a[s] = a[f], s++, f++
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

  // case 1: if a[f .. f+2] == pattern, a[s] = this char in the replacement, s -= t.length(), f -= s.length()
  // case 2: if a[f .. f+2] != pattern, a[s] = a[f], s--, f--
  private String replaceLonger(char[] array, String s, String t) {
    // Notice: we will need a long array in this case, and if the requirement
    // is "in place", usually you can assume you are given a longer enough
    // char array already, the original input string resides on the part of 
    // char array that starts from index 0
    // In this solution, we actually allocate a larger array on demand, and 
    // the purpose of the function is how to do it in-place.

    // get all the matches end positions in the input string
    List<Integer> matches = getAllMatches(array, s); // delta
    // expand the new length
    char[] res = new char[array.length + matches.size() * (t.length() - s.length())];
    // fast and slow pointer move from right to left direction
    // slow: the position when traversing the new length
    // fast: the position when traversing the old length
    // lastIndex: the rightmost matching position (end index of each pattern)
    int lastIndex = matches.size() - 1;
    int slow = res.length - 1;
    int fast = array.length - 1;

    while (fast >= 0) {
      // only if we still have some match, and slow is in the position of 
      // rightmost matching position, we can copy t at slow
      if (lastIndex >= 0 && fast == matches.get(lastIndex)) {
        copySubstring(res, slow - t.length() + 1, t);
        slow -= t.length();
        fast -= s.length();
        lastIndex--;
      } else {
        res[slow--] = array[fast--];
      }
    }

    return new String(res);
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

  // get all the matches of end positions in the input
  private List<Integer> getAllMatches(char[] array, String s) {
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

  // ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  
  // Method 2: using Java's StringBuilder utility and String indexOf()
  // not using the discussed algo
  public String replace(String input, String s, String t) {
    // Assumptions: input, s, t are not null, s is not empty
    StringBuilder sb = new StringBuilder();
    // we check if there exist a substring same as s
    // in the substring of input start from index
    int fromIdx = 0;
    int matchIdx = input.indexOf(s, fromIdx);
    while (matchIdx != -1) {
      sb.append(input, fromIdx, matchIdx).append(t);
      // next time we need to start from matchIdx + s.length()
      // to find if we have later matches.
      fromIdx = matchIdx + s.length();
      matchIdx = input.indexOf(s, fromIdx);
    }
    sb.append(input, fromIdx, input.length());
    return sb.toString();
  }
}
