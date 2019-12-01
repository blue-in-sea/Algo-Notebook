public class RemoveAdjacentRepeatedCharactersIII {
  // slow and fast pointer: same as Array Duplication III
  public String deDup(String input) {
    if (input == null || input.length() <= 1) {
      return input;
    }
    char[] array = input.toCharArray();
    int slow = 0;
    int fast = 0;
    while (fast < array.length) {
      int next_fast = fast;
      while (next_fast < array.length && array[next_fast] == array[fast]) {
        next_fast++;
      }
      if (next_fast - fast == 1) {
        array[slow] = array[fast];
        slow++;
        fast++;
      }
      fast = next_fast;
    }
    return new String(array, 0, slow);
  }
}
