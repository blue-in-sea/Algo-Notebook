import java.util.*;
/**
 * 934. Shortest Bridge
 *
 * You are given an n x n binary matrix grid where 1 represents land and 0 represents water.
 * An island is a 4-directionally connected group of 1's not connected to any other 1's. There are exactly two
 * islands in grid.
 *
 * You may change 0's to 1's to connect the two islands to form one island.
 * Return the smallest number of 0's you must flip to connect the two islands.
 *
 * Input: grid = [[0,1],[1,0]]
 * Output: 1
 *
 * Input: grid = [[0,1,0],[0,0,0],[0,0,1]]
 * Output: 2
 *
 * Input: grid = [[1,1,1,1,1],[1,0,0,0,1],[1,0,1,0,1],[1,0,0,0,1],[1,1,1,1,1]]
 * Output: 1
 *
 * Constraints: n == grid.length == grid[i].length; 2 <= n <= 100;
 * grid[i][j] is either 0 or 1; There are exactly two islands in grid.
 */
class ShortestBridge {
    /**
     * Algo
     * 1. Identify the First Island:
     *    DFS or BFS find the connected component
     * 2. Expand the First Island:
     *    Use BFS to expand the first island layer by layer until it reaches the second island.
     * 3. Count the Step: The number of layers expanded before reaching the second island
     *    will be the minimum number of 0's that need to be flipped.
     *
     * Time: find the connected component O(n^2) + Expands the first island layer by layer O(n^2)
     * Space: DFS run at most n^2 stack calls + BFS queue stores at most n^2 cells 
     */
    // Time: O(n^2)
    // Space: O(n^2)
    private int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int shortestBridge(int[][] grid) {
        int n = grid.length;
        boolean[][] visited = new boolean[n][n];
        Queue<Pair> queue = new LinkedList<>();

        boolean found = false;

        // Step 1: Find the first island and mark all its cells
        for (int i = 0; i < n && !found; i++) {
            for (int j = 0; j < n && !found; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, visited, grid, queue);
                    // bfsMarkIsland(i, j, visited, grid, queue);
                    found = true;
                }
            }
        }

        // 2. BFS to expand the first island until reaching the second island
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair cell = queue.poll();
                for (int[] dir : DIRS) {
                    int x = cell.x + dir[0];
                    int y = cell.y + dir[1];

                    if (inBound(x, y, grid) && !visited[x][y]) {
                        if (grid[x][y] == 1) {
                            return step;
                        }
                        visited[x][y] = true;
                        queue.offer(new Pair(x, y));
                    }
                }
            }
            step++;
        }

        return -1; // Should not reach here as per problem constraints
    }

    // dfs: identify the first island, add them to the queue
    private void dfs(int i, int j, boolean[][] visited, int[][] grid, Queue<Pair> queue) {
        visited[i][j] = true;
        queue.offer(new Pair(i, j));

        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (inBound(x, y, grid) && grid[x][y] == 1 && !visited[x][y]) {
                dfs(x, y, visited, grid, queue);
            }
        }
    }

    private void bfsMarkIsland(int i, int j, boolean[][] visited, int[][] grid, Queue<Pair> queue) {
        Queue<Pair> fistIslandQueue = new LinkedList<>();
        fistIslandQueue.offer(new Pair(i, j));
        visited[i][j] = true;

        while (!fistIslandQueue.isEmpty()) {
            Pair cell = fistIslandQueue.poll();
            queue.offer(cell);

            for (int[] dir : DIRS) {
                int x = cell.x + dir[0];
                int y = cell.y + dir[1];

                if (inBound(x, y, grid) && grid[x][y] == 1 && !visited[x][y]) {
                    visited[x][y] = true;
                    fistIslandQueue.offer(new Pair(x, y));
                }
            }
        }
    }

    private boolean inBound(int x, int y, int[][] grid) {
        int n = grid.length;
        return (x >= 0 && x < n && y >= 0 && y < n);
    }

    class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
