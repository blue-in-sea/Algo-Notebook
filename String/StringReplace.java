public class StringReplace {
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
