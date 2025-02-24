/**
 * 329. Longest Increasing Path in a Matrix
 * Given an m x n integers matrix, return the length of the longest increasing path in matrix.
 *
 * From each cell, you can either move in four directions: left, right, up, or down. You may not move diagonally or
 * move outside the boundary (i.e., wrap-around is not allowed).
 *
 * Input: matrix = [
 *   [9, 9, 4],
 *   [6, 6, 8],
 *   [2, 1, 1]
 * ]
 * Output: 4
 * Explanation: The longest increasing path is [1, 2, 6, 9].
 *
 * Input: matrix = [
 *    [3, 4, 5],
 *    [3, 2, 6],
 *    [2, 2, 1]]
 * Output: 4
 * Explanation: The longest increasing path is [3, 4, 5, 6]. Moving diagonally is not allowed.
 *
 * Input: matrix = [[1]]
 * Output: 1
 * 
 * Constraints: m == matrix.length; n == matrix[i].length
 * 1 <= m, n <= 200; 0 <= matrix[i][j] <= 231 - 1
 */
class LongestIncreasingPathInAMatrix {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int row;
    int col;

    // Algo: DFS with memoization
    // DFS computes the longest increasing path starting from a given cell (x, y).
    // If the result for (x, y) is already computed (stored in memo[x][y]), it returns the stored value.

    // Time: O(mn) given the (m x n) matrix 
    // * Each cell (i, j) in the matrix is visited once.
    // * For each cell, the dfs function is called, but due to memoization, each cell's result is computed only once.

    // Space: O(mn) 
    // * size of memo array O(mn) + size of recursion stack calls O(mn)
    public int longestIncreasingPath(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        if (row == 1 && col == 1) {
            return 1;
        }

        int max = 1;
        int[][] memo = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (memo[i][j] == 0) {
                    max = Math.max(max, dfs(matrix, memo, i, j));
                }
            }
        }

        return max;
    }

    public int dfs(int[][] matrix, int[][] memo, int x, int y) {
        if (memo[x][y] > 0) {
            return memo[x][y];
        }

        int cur = 1;

        for (int[] dir : DIRS) {
            int nextX = x + dir[0];
            int nextY = y + dir[1];

            if (inBound(nextX, nextY) && matrix[x][y] < matrix[nextX][nextY]) {
                cur = Math.max(cur, dfs(matrix, memo, nextX, nextY) + 1);
            }
        }

        memo[x][y] = cur;
        return cur;
    }

    private boolean inBound(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }
}

/**
 * matrix = [
 *   [9, 9, 4],
 *   [6, 6, 8],
 *   [2, 1, 1]
 * ]
 * 
 * 1. Initialization:
 * row = 3, col = 3 (matrix dimensions).
 * memo is initialized as a 3x3 array filled with zeros.
 *
 * 2. Iterate Over Cells:
 * The algorithm iterates over each cell in the matrix and calls dfs if memo[i][j] == 0.
 *
 * 3. DFS Calls:
 * Let's focus on the cell (2, 1) (value = 1), which is part of the longest path [1, 2, 6, 9].
 *
 * DFS for (2, 1):
 * * Current value = 1.
 * * Explore neighbors:
 * * * (1, 1) (value = 6): Valid (6 > 1). Recursively call dfs on (1, 1).
 * * * (2, 0) (value = 2): Valid (2 > 1). Recursively call dfs on (2, 0).
 * * Other neighbors are either out of bounds or have values ≤ 1.
 *
 * DFS for (1, 1):
 * * Current value = 6.
 * * Explore neighbors:
 * * * (0, 1) (value = 9): Valid (9 > 6). Recursively call dfs on (0, 1).
 * * * Other neighbors are either out of bounds or have values ≤ 6.
 *
 * DFS for (0, 1):
 * * Current value = 9.
 * * No valid neighbors (all neighbors have values ≤ 9).
 * * Return 1 (path length = 1).
 * Back to (1, 1):
 * * The result from (0, 1) is 1.
 * * The longest path from (1, 1) is 1 + 1 = 2.
 *
 * Back to (2, 1):
 * * The result from (1, 1) is 2.
 * 
 * The result from (2, 0) is computed similarly and also contributes to the path length.
 * The longest path from (2, 1) is max(2, ...) + 1 = 3.
 *
 * 4. Final Result:
 * The maximum path length found across all cells is 4 (from the path [1, 2, 6, 9]).
 */
// soln: https://leetcode.com/problems/longest-increasing-path-in-a-matrix/solutions/2052444/4-approaches-bfs-memorization-dfs-dp-topo-sorting/?envType=problem-list-v2&envId=topological-sort

