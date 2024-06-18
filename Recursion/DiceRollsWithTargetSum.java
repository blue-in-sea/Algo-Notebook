package Recursion;
/**
 * You have n dice, and each dice has k faces numbered from 1 to k.
 * Given three integers n, k, and target, return the number of possible ways (out of the kn total ways) to roll the dice,
 * so the sum of the face-up numbers equals target. Since the answer may be too large, return it modulo 109 + 7.
 *
 * Input: n = 1, k = 6, target = 3
 * Output: 1
 * Explanation: You throw one die with 6 faces.
 * There is only one way to get a sum of 3.
 *
 * Input: n = 2, k = 6, target = 7
 * Output: 6
 * Explanation: You throw two dice, each with 6 faces.
 * There are 6 ways to get a sum of 7: 1+6, 2+5, 3+4, 4+3, 5+2, 6+1.
 *
 * Input: n = 30, k = 30, target = 500
 * Output: 222616187
 * Explanation: The answer must be returned modulo 109 + 7.
 */
public class DiceRollsWithTargetSum {
    // n dices, each dice has k faces number from 1 to k
    //
    // level = n, k = 3(枝叶)
    // level               [dice_index, currsum]
    //                                [0,0]
    // dice#1    [1,1]                [1,2]                  [1,3]
    // dice#2 [2,2][2,3][2,4]  [2,3]* [2,4]* [2,5]    [2,4]* [2,5]* [2,6]
    //
    // dice#n [n,n]...

    final int MOD = 1000000007;

    public int numRollsToTarget(int n, int k, int target) {
        Integer[][] memo = new Integer[n + 1][target + 1];
        return dfs(memo, 0, n, 0, target, k);
    }

    // Brutal force: Recursion with no trim
    // Time: O(k^n)
    // Go through each possible combinations and count the # of ways where result = target. To do this
    // we iterate from 1 to k for each dice, store it as a curr_var and keep a curr_sum, recursively move to the next dice.
    // This will produces all the k^n possibilities/combinations

    // Top-Down Dynamic Programming: Recursion + Memo
    // Time: O(n * t * k) where n dices, t is the target sum, k is the faces number => (n * t) state * k possible faces
    //                                                                                  memo[][]       for-loop
    // Space: O(n * t) constant time = O(n * t)          + O(n)
    //                                 size of memo[][]  +  # of stack calls by n levels
    // De-dup the repeated sub-problem by memoize the result of each sub-problem: [index, currsum] with *
    // Each time we calculate the result from the same set of param, we can do lookup in constant time
    private int dfs(Integer[][] memo, int level /* dice_index */, int n, int currsum, int target, int k) {
        // base case
        // 1. if all dice exhausted
        if (level == n) {
            return currsum == target ? 1 : 0; // 1 - find a way; 0 - no way
        }

        // memo the # of ways !!! don't need to calculte again
        if(memo[level][currsum] != null) {
            return memo[level][currsum];
        }

        int ways = 0;
        // from 1 to k
        for (int i = 1; i <= Math.min(k, target - currsum); i++) {
            ways = (ways + dfs(memo, level + 1, n, currsum + i, target, k)) % MOD;
        }

        memo[level][currsum] = ways;
        return memo[level][currsum] = ways;
    }


    // Bottom-Up Dynamic Programming: interview !!!
    // Iterate over the states by starting from the base case and ending at the initial query
}
