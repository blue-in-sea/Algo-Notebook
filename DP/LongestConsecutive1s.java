/**
 * 103. Longest Consecutive 1s (Array)
 * Given an array containing only 0s and 1s, find the length of the longest subarray of consecutive 1s.
 * The given array is not null
 *
 * {0, 1, 0, 1, 1, 1, 0}, the longest consecutive 1s is 3.
 */
public class LongestConsecutive1s {
    public int longest(int[] array) {
        // Assume array is not null
        int res = 0;
        int cur = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 1) {
                if (i == 0 || array[i - 1] == 0) {
                    cur = 1;
                } else {
                    cur++;
                }
            }
            res = Math.max(res, cur);
        }
        return res;
    }
}
