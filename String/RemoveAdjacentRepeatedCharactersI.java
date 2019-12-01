public class RemoveAdjacentRepeatedCharactersI {
  // slow and fast pointer: same as Array Duplication I
  public String deDup(String input) {
    if (input == null || input.length() == 0) {
      return input;
    }
    char[] array = input.toCharArray();
    int slow = 1;
    for (int fast = 1; fast < array.length; fast++) {
      // case 1: if a[f] == a[s-1], skip (duplicates)
      // case 2: if a[f] != a[s-1], a[s] = a[f], s++, f++
      if (array[fast] != array[slow - 1]) {
        array[slow++] = array[fast];
      }
    }
    return new String(array, 0, slow);
  }
}
