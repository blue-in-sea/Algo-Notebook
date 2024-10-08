/**
 * 202. Kth Smallest In Two Sorted Arrays (Hard)
 * Given two sorted arrays of integers, find the Kth smallest number.
 *
 * The two given arrays are not null and at least one of them is not empty
 * K >= 1, K <= total lengths of the two sorted arrays
 *
 * A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
 * A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
 */
public class KthSmallestSumInTwoSortedArrays {
    public int kth(int[] a, int[] b, int k) {
        // Assume a, b is not null, and at leaste one of them
        // is not empty, k <= a.len + b.len, k >= 1
        return kth(a, 0, b, 0, k);
    }

    // search the subarray of a starting from index aLeft, 
    // and the subarray of b starting from index aRight
    private int kth(int[] a, int aleft, int[] b, int bleft, int k) {
        // there base cases:
        // 1. we already eliminate all the ele in a
        // 2. we already eliminate all the ele in b
        // 3. when k is reduced to 1, don't miss this base case
        // (k >= 2) for binary search to be worked 
        if (aleft >= a.length) {
            return b[bleft + k - 1];
        }
        if (bleft >= b.length) {
            return a[aleft + k - 1];
        }
        if (k == 1) {
            return Math.min(a[aleft], b[bleft]);
        }
        // we compare the k/2-th element in a's subarray
        // and the k/2-th element in b's subarray
        // to determine which k/2 partition can be surely included
        // in the smallest k elements
        int amid = aleft + k/2 - 1;  // array index from 0
        int bmid = bleft + k/2 - 1;  // array index from 0 
        int aval = amid >= a.length ? Integer.MAX_VALUE : a[amid];
        int bval = bmid >= b.length ? Integer.MAX_VALUE : b[bmid];
        if (aval <= bval) {
            return kth(a, amid + 1, b, bleft, k - k / 2);
        } else {
            return kth(a, aleft, b, bmid + 1, k - k / 2);
        }
    }
}
