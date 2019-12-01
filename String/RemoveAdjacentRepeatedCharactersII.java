public class RemoveAdjacentRepeatedCharactersII {
  // slow and fast pointer: same as Array Duplication II
  public String deDup(String input) {
    if (input == null || input.length() <= 1) {
      return input;
    }
    char[] array = input.toCharArray();
    int slow = 2;
    for (int fast = 2; fast < array.length; fast++) {
      // case 1: if a[f] == a[s-2], skip (duplicates)
      // case 2: if a[f] != a[s-2], a[s] = a[f], s++, f++
      if (array[fast] == array[slow - 2]) {
        continue;
      }
      array[slow++] = array[fast];
    }
    return new String(array, 0, slow);
  }
}
