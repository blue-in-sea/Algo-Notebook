// Array Deduplication II - Keep two elements for each dup in a sorted array
// {1, 2, 2, 3, 3, 3} → {1, 2, 2, 3, 3}
/**
 * Solution: [0 … slow - 1] is the partial result to return, Fast pointer used to discover new element
 * slow: all elements to the left side of slow (excluding slow) are results that are processed
 * fast: the current index being processed 
 * (all elements to the right side of the fast pointer has not been processed)
 *
 * Initialize: s = f = 2
 * For each step
 * case 1	a[f] == a[s-2], f++			                       (found the repeat element, skip)
 * case 2 a[f] != a[s-2], then a[s] = a[f], s++, f++	   (replace itself or different element, then move)
 */
public class ArrayDuplicationII {
    // Time: O(n), Space O(1)
    public int[] dedup(int[] array) {
        // assume array is not null
        if (array.length <= 2) return array;

        int slow = 2;
        for (int fast = 2; fast < array.length; fast++) {
            if (array[fast] == array[slow - 2]) {
                continue;
            }
            array[slow++] = array[fast];
        }
        return Arrays.copyOf(array, slow);
    }
}
