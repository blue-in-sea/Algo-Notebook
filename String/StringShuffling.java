package String;

public class StringShuffling {
    public int[] reorder(int[] array) {
        // Assumption: array is not null
        if (array.length % 2 == 0) {
            reorderHelper(array, 0, array.length - 1);
        } else {
            // if array has odd number of elements, we ignore the last one
            // when we do the reordering
            reorderHelper(array, 0, array.length - 2);
        }
        return array;
    }

    private void reorderHelper(int[] array, int left, int right) {
        // how many element in this section
        int length = right - left + 1;
        // if the subarray has 2 or 0 elements, we can just return
        // as this would be the base case
        if (length <= 2) return;
        // calculate the critical mid points:
        // 0  1  | 2  3  | 4  5  |  6  7
        // lm: 2, m: 4, rm: 6
        // 0  1  | 2  3  4  | 5  6  | 7  8  9
        // lm: 2, m: 5, rm: 7
        int mid = left + length / 2;
        int lmid = left + length / 4;
        int rmid = left + length * 3 / 4;

        reverse(array, lmid, mid - 1);
        reverse(array, mid, rmid - 1);
        reverse(array, lmid, rmid - 1);

        // half of the left partition's size = lmid - left
        reorderHelper(array, left, left + (lmid - left) * 2 - 1);
        reorderHelper(array, left + (lmid - left) * 2, right);
    }

    private void reverse(int[] array, int i, int j) {
        while (i < j) swap(array, i++, j--);
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
