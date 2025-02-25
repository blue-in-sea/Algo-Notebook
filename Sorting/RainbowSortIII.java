/**
 * Rainbow Sort III - K Colors (Laicode 400)
 * Given an array of balls with k different colors denoted by numbers 1- k, sort the balls.
 *
 * k=1, {1} is sorted to {1}
 * k=3, {1, 3, 2, 1, 2} is sorted to {1, 1, 2, 2, 3}
 * k=5, {3, 1, 5, 5, 1, 4, 2} is sorted to {1, 1, 2, 3, 4, 5, 5}
 * Assumptions
 *
 * The input array is not null.
 * k is guaranteed to be >= 1.
 * k << logn.
 */
public class RainbowSortIII {
    /**
     * Rainbow Sort:
     * Dutch National Flag Problem
     *
     * For the 1st-section
     * [0, l): 0
     * [l, m): 1
     * [m, r]: unchecked element
     * [r, len - 1]: (2...k)
     *
     * recursively sort left (0, 1, (2...k))
     * recursively sort right ((0...k-2), k-1, k)
     */

    // Time: O(n log k),
    // Space: O(log k) (due to recursion stack).
    public int[] rainbowSortIII(int[] array, int k) {
        if (array == null || array.length <= 1) {
            return array;
        }
        rainbowSort(array, 0, array.length - 1, 1, k);
        return array;
    }

    private void rainbowSort(int[] colors, int left, int right, int start, int end) {
        if (start == end) {
            return;
        }

        if (left >= right) {
            return;
        }

        int mid = start + (end - start) / 2;
        int l = left, r = right;
        while (l <= r) {
            if (colors[l] <= mid) {
                l++;
            } else if (colors[r] > mid) {
                r--;
            } else {
                swap(colors, l++, r--);
            }
        }

        rainbowSort(colors, left, r, start, mid);
        rainbowSort(colors, l, right, mid + 1, end);
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
