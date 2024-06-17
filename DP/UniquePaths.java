package DP;

/**
 * 62. Unique Paths
 * There is a robot on an m x n grid. The robot is initially located at the top-left corner (i.e., grid[0][0]). The
 * robot tries to move to the bottom-right corner (i.e., grid[m - 1][n - 1]). The robot can only move either down or
 * right at any point in time.
 *
 * Given the two integers m and n, return the number of possible unique paths that the robot can take to reach the
 * bottom-right corner.
 *
 * Example 1
 * [[s, 0, 0, 0, 0, 0, 0],
 *  [0, 0, 0, 0, 0, 0, 0],
 *  [0, 0, 0, 0, 0, 0, e]]
 * Input: m = 3, n = 7
 * Output: 28
 * 
 * Example 2
 * [[s, 0], 
 *  [0, 0], 
 *  [0, e]]
 * Input: m = 3, n = 2
 * Output: 3
 * Explanation: From the top-left corner, there are a total of 3 ways to reach the bottom-right corner:
 * 1. Right -> Down -> Down
 * 2. Down -> Down -> Right
 * 3. Down -> Right -> Down
 */

class UniquePaths {
    // Time: O(NM)
    // Space: O(NM)

    public int uniquePaths(int m, int n) {
        int[][] d = new int[m][n];

        for (int[] arr : d) {
            Arrays.fill(arr, 1);
        }
        for (int col = 1; col < m; ++col) {
            for (int row = 1; row < n; ++row) {
                d[col][row] = d[col - 1][row] + d[col][row - 1];
            }
        }
        return d[m - 1][n - 1];
    }

    
}
