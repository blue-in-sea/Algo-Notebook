/**
 * Array Deduplication I (LaiCode 115)
 * Given a sorted integer array, remove duplicate elements. For each group of elements with the same value keep only one of them. 
 * Do this in-place, using the left side of the original array and maintain the relative order of the elements of the array. 
 * 
 * Return the array after deduplication.
 * 
 * {1, 2, 2, 3, 3, 3} → {1, 2, 3}
 * {1,2,3,4,4,5} → {1, 2, 3, 4, 5}
 */

/** 
 * Method: in-place via two pointers (f + s)
 * s: all elements to the left side of s (excluding s) are results that are processed
 * f: the current index when linear scan
 * return array[s-1]
 * 
 * initialize s = f = 1
 * case 1: if a[f] == a[s-1], skip (duplicates)
 * case 2: if a[f] != a[s-1], a[s] = a[f], s++, f++
 */
public class ArrayDeduplicationI {
  /**
   * Given a !! sorted !! integer array, remove duplicate elements. 
   * For each group of elements with the same value, keep only one of them. 
   */
  public int[] dedup(int[] array) {
    if (array == null || array.length <= 1) {
      return array;
    }
    int slow = 1;
    for (int fast = 1; fast < array.length; fast++) {
      if (array[slow - 1] != array[fast]) {
        array[slow] = array[fast];
        slow++;
      }
    }
    return Arrays.copyOf(array, slow);
  }
  
  /**
   * Remove adjacent, repeated characters in a given string, leaving only one character 
   * for each group of such characters.
   */
  public String deDup(String input) {
    if (input == null || input.length() <= 1) {
      return input;
    }
    char[] array = input.toCharArray();
    int slow = 1;
    for (int fast = 1; fast < array.length; fast++) {
      if (array[fast] != array[slow - 1]) {
        array[slow++] = array[fast];
      }
    }
    return new String(array, 0, slow);
  }
}
