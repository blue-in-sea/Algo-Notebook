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
/**
 * Input n = 6
 * Output: 1
 * Explanation: The confusing numbers are [6]
 * 6 converts to 9
 *
 * Input n = 9
 * Output: 1
 * Explanation: The confusing numbers are [6, 9]
 * 6 converts to 9.
 * 9 converts to 6.
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

/**
 * N = 6, output[6]
 * (enter dfs) mul: 1    rot:0    cur: 0
 *       mul*=10    rot=rot+mul*map[i]   cur=cur*10+cur
 * i:1    a[i]:1    mul: 10     rot:1    cur: 1
 * i:0    a[i]:0    mul: 100    rot:1    cur: 10*
 * ------------------------------------------------
 * i:1    a[i]:1    mul: 100    rot:11    cur: 11*
 * ------------------------------------------------
 * i:2    a[i]:6    mul: 100    rot:91    cur: 16*
 * ------------------------------------------------
 * i:3    a[i]:8    mul: 100    rot:81    cur: 18*
 * ------------------------------------------------
 * i:4    a[i]:9    mul: 100    rot:61    cur: 19*
 * ------------------------------------------------
 * i:2    a[i]:6    mul: 10     rot:9    cur: 6           x
 * i:0    a[i]:0    mul: 100    rot:9    cur: 60*
 * ------------------------------------------------
 * i:1    a[i]:1    mul: 100    rot:19    cur: 61*
 * ------------------------------------------------
 * i:2    a[i]:6    mul: 100    rot:99    cur: 66*
 * ------------------------------------------------
 * i:3    a[i]:8    mul: 100    rot:89    cur: 68*
 * ------------------------------------------------
 * i:4    a[i]:9    mul: 100    rot:69    cur: 69*
 * ------------------------------------------------
 * i:3    a[i]:8    mul: 10     rot:8    cur: 8*
 * ------------------------------------------------
 * i:4    a[i]:9    mul: 10     rot:6    cur: 9*
 * ------------------------------------------------
 */

/**
 * N = 9, output[6, 9]
 * (enter dfs) mul: 1    rot:0    cur: 0
 *       mul*=10    rot=rot+mul*map[i]   cur=cur*10+cur
 *
 * i:1    a[i]:1    mul: 10     rot:1    cur: 1
 * i:0    a[i]:0    mul: 100    rot:1    cur: 10*
 * ------------------------------------------------
 * i:1    a[i]:1    mul: 100    rot:11    cur: 11*
 * ------------------------------------------------
 * i:2    a[i]:6    mul: 100    rot:91    cur: 16*
 * ------------------------------------------------
 * i:3    a[i]:8    mul: 100    rot:81    cur: 18*
 * ------------------------------------------------
 * i:4    a[i]:9    mul: 100    rot:61    cur: 19*
 * ------------------------------------------------
 * i:2    a[i]:6    mul: 10     rot:9    cur: 6            x
 * i:0    a[i]:0    mul: 100    rot:9    cur: 60*
 * ------------------------------------------------
 * i:1    a[i]:1    mul: 100    rot:19    cur: 61*
 * ------------------------------------------------
 * i:2    a[i]:6    mul: 100    rot:99    cur: 66*
 * ------------------------------------------------
 * i:3    a[i]:8    mul: 100    rot:89    cur: 68*
 * ------------------------------------------------
 * i:4    a[i]:9    mul: 100    rot:69    cur: 69*
 * ------------------------------------------------
 * i:3    a[i]:8    mul: 10     rot:8    cur: 8
 * i:0    a[i]:0    mul: 100    rot:8    cur: 80*
 * ------------------------------------------------
 * i:1    a[i]:1    mul: 100    rot:18    cur: 81*
 * ------------------------------------------------
 * i:2    a[i]:6    mul: 100    rot:98    cur: 86*
 * ------------------------------------------------
 * i:3    a[i]:8    mul: 100    rot:88    cur: 88*
 * ------------------------------------------------
 * i:4    a[i]:9    mul: 100    rot:68    cur: 89*
 * ------------------------------------------------
 * i:4    a[i]:9    mul: 10     rot:6    cur: 9          x
 * i:0    a[i]:0    mul: 100    rot:6    cur: 90*
 * ------------------------------------------------
 * i:1    a[i]:1    mul: 100    rot:16    cur: 91*
 * ------------------------------------------------
 * i:2    a[i]:6    mul: 100    rot:96    cur: 96*
 * ------------------------------------------------
 * i:3    a[i]:8    mul: 100    rot:86    cur: 98*
 * ------------------------------------------------
 * i:4    a[i]:9    mul: 100    rot:66    cur: 99*
 * ------------------------------------------------
 */
