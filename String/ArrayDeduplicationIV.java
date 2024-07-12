/**
 * Array Deduplication IV (Zuma Game) 
 * Given an unsorted integer array, remove adjacent duplicate elements repeatedly, from left to right.
 * For each group of elements with the same value do not keep any of them.
 *
 * {1, 2, 3, 3, 3, 2, 2} → {1, 2, 2, 2} → {1}, return {1}
 */
/**
 * Solution: [0 … slow] is the partial result to return, Fast used to discover the new element 
 * slow: all elements to the left side of slow (including slow) are the stack region to return 
 * fast:  the current index of linear scan
 * 
 * Initialize: s = 0, f = 1, flag = false (if there is any dup-element)
 *         
 * For each step
 * case 1	a[f] == a[s], set flag = true 
 * case 2 if flag, a[s] = a[f] the set flag = false              delete the adjacent duplicates, reset flag
 * case 3 no dup, a[++s] = a[f]                                  copy the unduplicate 
 *
 * At the end, if flag, return a[s+1]
 *             else     return a[s]
 */
public class ArrayDeduplicationIV {
    public int[] dedup(int[] array) {
        // corner case
        if (array == null || array.length <= 1) {
            return array;
        }
        int end = 0;
        // use flag to check if there is any dup-element
        boolean flag = false;
        for (int i = 1; i < array.length; i++) {
            if (array[i] == array[end]) {
                // if there is duplicate, set flag to true
                // and do nothing else
                flag = true;
            } else if (flag == true) {
                // we replace array[end] with array[i] since next
                // and continue to check if array[i] contains dup
                array[end] = array[i];
                // reset flag to false
                flag = false;
            } else {
                array[++end] = array[i];
            }
        }
        return Arrays.copyOf(array, flag ? end : end + 1);
    }
}
