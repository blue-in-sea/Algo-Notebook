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
 */

public class ConfusingNumberAll {
    public List<Long> allConfusingNumber(int N) {
        List<Long> res = new ArrayList<>();
        int[] arr = {0,1,6,8,9};
        int[] map = {0,1,9,8,6};
        dfs(N, arr, map, res, 0, 0, 1);
        return res;
    }

    private void dfs(int N, int[] arr, int[] map, List<Long> res,
                     long currNum, long rotatedNum, int multiplier) {
        if (currNum > N) {
            return;
        }

        if (currNum != rotatedNum) {
            res.add(currNum);
        }

        for (int i = 0; i < arr.length; i++) {
            if (currNum == 0 && arr[i] == 0) {
                continue;
            }

            dfs(N, arr, map, res, currNum * 10 + arr[i], map[i] * multiplier + rotatedNum, multiplier * 10);
        }
    }
}

