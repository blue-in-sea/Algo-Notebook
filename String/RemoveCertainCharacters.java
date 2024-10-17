/**
 * 395. Remove Certain Characters
 * Remove given characters in input string, the relative order of other characters should be remained. 
 * Return the new string after deletion.
 *
 * Assumptions
 * The given input string is not null.
 * The characters to be removed is given by another string, it is guaranteed to be not null.
 *
 * input = "abcd", t = "ab", delete all instances of 'a' and 'b', the result is "cd".
 */
public class RemoveCertainCharacters {
  // Method: in-place
  // Time: O(n), Space: O(1)
  public String remove(String input, String t) {
    // Assume input and t are not null
    char[] array = input.toCharArray();
    Set<Character> set = buildTSet(t);

    // slow: [0, slow) contains the valid result
    // fast: [fast, array.length) is the area to explore

    int slow = 0;
    for (int fast = 0; fast < array.length; fast++) {
      if (!set.contains(array[fast])) {
        array[slow] = array[fast];
        slow++;
      }
    }
    return new String(array, 0, slow);
  }

  private Set<Character> buildTSet(String t) {
    Set<Character> set = new HashSet<>();
    for (char c : t.toCharArray()) {
      set.add(c);
    }
    return set;
  }
}
