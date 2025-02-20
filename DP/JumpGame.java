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
    // boolean dp[i] represents can jump from i-th element to the target

    // index  0  1  2  3  4
    // Arr  [ 2, 3, 1, 1, 4 ]
    //        <-
    // dp   [ T, T, T, T, T ]

    // ========================================================================
    // Method1: 从起始点 dp 到终点 (linear scan 回头看）
    // base case: 
    //      dp[0] = true
    // induction rule: 
    //     dp[i] = true if (there exists one) j such that dp[j] == true && dp[j] + j >= i for j => [0, i)
    //          = false, otherwise 
    // return dp[n - 1]
    // Time: O(n^2), Space: O(n)
    public boolean canJump(int[] array) {
        // Assume array is not null and not empty 
        boolean[] dp = new boolean[array.length];

        dp[0] = true;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && array[j] + j >= i) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[array.length - 1];
    }

    // ========================================================================
    // Method2: 从终点 dp 到起始点 (linear scan 回头看）
    // base case: 
    //      dp[n - 1] = true
    // induction rule: 
    //     dp[i] = true if (there exists one) k such that dp[k] == true where k => [i, i + a[i]]
    //             let k = (j + i) such that dp[j + i] == true where j => [1, a[i]]
    //          = false, otherwise 
    // return dp[0]
    // Time: O(n^2), Space: O(n)
    public boolean canJump(int[] array) {
        // Assume array is not null and not empty 
        int n = array.length;
        if (n == 0) return true;

        boolean[] dp = new boolean[n];
        dp[n - 1] = true;
        for (int i = n - 1; i >= 0; i--) {
            if (array[i] + i >= n) {
                // check the endpoint if directly reachable at index i
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
