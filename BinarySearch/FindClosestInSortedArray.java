/**
 * Given a target integer T and an integer array A sorted in ascending order, find the index i in A such that A[i] is
 * closest to T.
 *
 * Assumptions
 * There can be duplicate elements in the array, and we can return any of the indices with same value.
 * Examples
 *
 * A = {1, 2, 3}, T = 2, return 1
 * A = {1, 4, 6}, T = 3, return 1
 * A = {1, 4, 6}, T = 5, return 1 or 2
 * A = {1, 3, 3, 4}, T = 2, return 0 or 1 or 2
 */
public class FindClosestInSortedArray {
    public int closest(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        
        int l = 0, r = array.length - 1;
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (array[m] == target) {
                return m;
            } else if (array[m] < target) {
                l = m;
            } else {
                r = m;
            }
        }
        
        if (Math.abs(array[l] - target) <= Math.abs(array[r] - target)) {
            return l;
        } else {
            return r;
        }
    }
}
