/**
 * Some of the digits can be rotated by 180 degrees to form new digits.
 *
 * e.g. When 0, 1, 6, 8, 9 are rotated 180 degrees,
 * they become 0, 1, 9, 8, 6 respectively.
 *
 * When 2, 3, 4, 5 and 7 are rotated 180 degrees,
 * they become invalid.
 *
 * A confusing number is a number that when rotated 180 degrees becomes a
 * different number with each digit valid.
 * (Note that the rotated number can be greater than the original number.)
 *
 * Given a positive integer N, return ALL the confusing numbers between 1 and N inclusive.
 *
 * Input: n = 20
 * Output: 6
 * Explanation: The confusing numbers are [6,9,10,16,18,19].
 * 6 converts to 9.
 * 9 converts to 6.
 * 10 converts to 01 which is just 1.
 * 16 converts to 91.
 * 18 converts to 81.
 * 19 converts to 61.
 *
 * Input: n = 100
 * Output: 19
 * Explanation: The confusing numbers are [6,9,10,16,18,19,60,61,66,68,80,81,86,89,90,91,98,99,100].
 */
public class ConfusingNumberII {
    public static int confusingNumberII(int N) {
        int[] arr = {0,1,6,8,9};
        int[] map = {0,1,9,8,6};

        int[] cnt = new int[1];
        cnt[0] = 0;
        dfs(N, arr, map, cnt, 0, 0, 1);
        return cnt[0];
    }

    private static void dfs(int N, int[] arr, int[] map, int[] cnt, long num, long rotatedNum, int base) {
        if (num > N) {
            return;
        }

        if (num != rotatedNum) {
            cnt[0]++;
        }

        for (int i = 0; i < arr.length; i++) {
            if (num == 0 && arr[i] == 0) {
                continue;
            }

            dfs(N, arr, map, cnt, num * 10 + arr[i], map[i] * base + rotatedNum, base * 10);
        }
    }
}