// Array Duplication III - Keep none for each dup in a sorted array
// index 0  1  2  3  4  5
// input  1  1  2  2  3  3
//           s
//                       f
//                                  next_f
/** 
 * s: all elements to the left side of slow (excluding s) are results that are processed
 * f: the current index being processed 
 *   (all elements to the right side of the fast pointer have not been processed)
 * return array[s-1]
 * 
 * initialize s = f = next_f = 0
 * 1) next_f = f keep doing next_f++ util a[next_f] != a[f]
 * 2) otherwise
 *    case 1: a[next_f] - a[f] == 1, s++, f++ or f = next_f  (element appear only once, so keep one)
 *    case 2: a[next_f] - a[f]   > 1, f = next_f             (element appear more than one, keep none)
 */
public class ArrayDeduplicationIII {
  /**
   * Given a sorted integer array, remove duplicate elements. 
   * For each group of elements with the same value do not keep any of them. 
   *
   * Do this in-place, using the left side of the original array 
   * and maintain the relative order of the elements of the array. Return the array after deduplication.
   */
  public int[] dedup(int[] array) {
    // assume array is not null
    if (array.length <= 1) {
      return array;
    }
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
      }
      fast = next_fast;
    }
    return Arrays.copyOf(array, slow);
  }
  
  /**
   * Remove adjacent, repeated characters in a given string, leaving only no character 
   * for each group of such characters. The characters in the string are sorted in ascending order.
   */  
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
        fast++; // with or without, either works
      }
      fast = next_fast;
    }
    return new String(array, 0, slow);
  }
}
