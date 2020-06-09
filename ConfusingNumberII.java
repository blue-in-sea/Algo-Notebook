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
 * Given a positive integer N, return how many confusing numbers are between 1 and N inclusive.
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
