/**
 * 827. Making A Large Island
 *
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 *
 * Input: grid = [
 *      [1, x],
 *      [0, 1]   ]
 * Output: 3
 * Explanation: Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
 *
 * Input: grid = [
 *      [1, 1],
 *      [x, 1]   ]
 * Output: 4
 * Explanation: Change the 0 to 1 and make the island bigger, only one island with area = 4.
 *
 * Input: grid = [
 *      [1, 1],
 *      [1, 1]   ]
 * Output: 4
 * Explanation: Can't change any 0 to 1, only one island with area = 4.
 *
 * Constraints:
 * n == grid.length
 * n == grid[i].length
 * 1 <= n <= 500
 * grid[i][j] is either 0 or 1.
 */
class MarkingALargeIsland {
    private int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}}; 
    int n;

    // 1. Assign Unique IDs to Islands
    // 2. Find the Maximum Island Size
    // 3. Handle Edge Cases
    // * If there are no 0s in the grid, the largest island size is simply the size of the largest existing island
    // * If the grid is entirely 0s, the largest island size is 1 (after converting one 0 to 1)

    // Time: O(n^2)
    // Space: O(n^2)
    public int largestIsland(int[][] grid) {
        n = grid.length;
        int islandId = 2; // Start assigning IDs from 2 (since 0 and 1 are already used)
        Map<Integer, Integer> islandSizes = new HashMap<>(); // Map to store island sizes

        // Step 1: Assign unique IDs to islands and store their sizes
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int size = dfs(grid, i, j, islandId);
                    islandSizes.put(islandId, size);
                    islandId++;
                }
            }
        }

        // Step 2: Find the maximum island size after converting one 0 to 1
        int maxIslandSize = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> neighboringIslands = new HashSet<>();
                    int currentSize = 1; // Start with the current cell being converted to 1

                    // Check all 4-directional neighbors
                    for (int[] dir : directions) {
                        int x = i + dir[0];
                        int y = j + dir[1];

                        if (inBound(x, y) && grid[x][y] != 0) {
                            neighboringIslands.add(grid[x][y]);
                        }
                    }

                    // Sum the sizes of all unique neighboring islands
                    for (int id : neighboringIslands) {
                        currentSize += islandSizes.get(id);
                    }

                    maxIslandSize = Math.max(maxIslandSize, currentSize);
                }
            }
        }

        // If there are no 0s in the grid, return the size of the largest island
        if (maxIslandSize == 0) {
            return n * n; // All cells are 1s
        }

        return maxIslandSize;
    }

    // DFS to assign IDs and calculate island sizes
    private int dfs(int[][] grid, int i, int j, int islandId) {
        if (!inBound(i, j) || grid[i][j] != 1) {
            return 0;
        }

        grid[i][j] = islandId; // Assign the island ID
        int size = 1;

        // Explore all 4-directional neighbors
        for (int[] dir : directions) {
            int x = i + dir[0];
            int y = j + dir[1];

            size += dfs(grid, x, y, islandId);
        }

        return size;
    }

    private boolean inBound(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < n;
    }
}
