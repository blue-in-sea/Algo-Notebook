/**
 * Rainbow Sort II - 4 Colors (Laicode 399)
 * Given an array of balls, where the color of the balls can only be Red, Green, Blue or Black, sort the balls such that
 * all balls with same color are grouped together and from left to right the order is Red->Green->Blue->Black.
 *
 * (Red is denoted by 0, Green is denoted by 1, Blue is denoted by 2 and Black is denoted by 3).
 *
 * {0} is sorted to {0}
 * {1, 0} is sorted to {0, 1}
 * {1, 3, 1, 2, 0} is sorted to {0, 1, 1, 2, 3}
 */
public class RainbowSortII {
    /**
     * Rainbow Sort:
     * Dutch National Flag Problem
     *
     * For the left range
     * [0, l): red
     * [l, m): green
     * [m, r]: unchecked element
     * [r, len - 1]: (blue+black)
     *
     * recursively sort left (red, green, (blue&black))
     * recursively sort right ((red+green), blue, black)
     */
    public int[] rainbowSortII(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        rainbowSort(array, 0, array.length - 1, 0, 3);
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
