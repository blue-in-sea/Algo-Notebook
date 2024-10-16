

class NumberOfIslandsDFS {
    private static final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private boolean[][] marked;
    private int rows;
    private int cols;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        rows = grid.length;
        cols = grid[0].length;
        marked = new boolean[rows][cols];

        int cnt = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (!marked[i][j] && grid[i][j] == '1') {
                    cnt++;
                    dfs(i, j, grid);
                }
            }
        }

        return cnt;
    }

    private void dfs(int i, int j, char[][] grid) {
        marked[i][j] = true;

        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];

            // if in-bound, has value of 1, not marked, then it's an island 
            if (check(x, y) && grid[x][y] == '1' && !marked[x][y]) {
                dfs(x, y, grid);      
            }
        }
    }

    private boolean check(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }
}
