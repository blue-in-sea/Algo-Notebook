/**
 * Laicode 636. Smallest Element Larger than Target
 * Given a target integer T and an integer array A sorted in ascending order, find the index of the smallest element 
 * in A that is larger than T or return -1 if there is no such index.
 *
 * A = {1, 2, 3}, T = 1, return 1
 * A = {1, 2, 3}, T = 3, return -1
 * A = {1, 2, 2, 2, 3}, T = 1, return 1
 *
 * Corner Case
 * What if A is null or A of zero length? We should return -1 in this case.
 */
public class Solution {
    // Binary Search 
    // Time: O(logn)
    // Space: O(1)
    public int smallestElementLargerThanTarget(int[] array, int target) {
        int l = 0, r = array.length - 1;
        while (l < r) {
            int m = l + (r - l) / 2;
            if (array[m] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        if (array[l] > target) {
            return l;
        }
    
        return -1;
    }

  // version 2: when l passed 1 index by r
  public int smallestElementLargerThanTarget(int[] array, int target) {
        if (array == null || array.length == 0) {
            return -1;
        }
        int l = 0, r = array.length - 1;
        while (l < r - 1) {
            int m = l + (r - l) / 2;
            if (array[m] <= target) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        if (array[l] > target) {
            return l;
        } else if (array[r] > target) {
            return r;
        }
        return -1;
    }
}
