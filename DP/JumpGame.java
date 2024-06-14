/**
 * You are given an integer array nums. You are initially positioned at the array's first index, and each element in 
 * the array represents your maximum jump length at that position. Return true if you can reach the last index, or 
 * false otherwise.
 *
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 *
 * Input: nums = [3,2,1,0,4]
 * Output: false
 * Explanation: You will always arrive at index 3 no matter what. Its maximum jump length is 0, which makes it 
 * impossible to reach the last index.
 */

class JumpGame {
    // DP: from end to start
    // Time: O(n^2), Space: O(n)

    public boolean canJump(int[] array) {
        // Assume the input is not null and input has length at leaste 1
        int len = array.length;
        if (len == 1) return true;

        // boolean dp[i] represents can jump from i-th element to the target
        // base case:
        //     dp[4] = true since a[4] is target itself
        // induction rule:
        //     dp[i] = true if (there exists one) j such that dp[j] == true
        //                     where i <= j <= i + a[i]
        //           = false otherwise

        // index  0  1  2  3  4
        // Arr  [ 2, 3, 1, 1, 4 ]
        //        <-
        // dp   [ T, T, T, T, T ]

        boolean[] dp = new boolean[len];
        // dp[len - 1] = true;  // base can skip, since default is true
        for (int i = len - 2; i >= 0; i--) {
            // from index i, it is already possible to jump to
            // the end of array
            if (i + array[i] >= len - 1) {
                dp[i] = true;
            } else {
                // for any of the reachable index from i
                // is reachable to the end of array
                for (int j = array[i]; j >= 1; j--) {
                    if (dp[j + i]) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[0];
    }
}
