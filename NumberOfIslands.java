public class NumberOfIslands {
  private class Pair {
    int x;
    int y;
    Pair(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
  private final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
  public int numIslands(char[][] grid) {
    if (grid == null || grid.length == 0) {
      return 0;
    }
    int rows = grid.length;
    int cols = grid[0].length;
    
    boolean[][] marked = new boolean[rows][cols];
    Queue<Pair> queue = new LinkedList<>();

    int count = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (!marked[i][j] && grid[i][j] == '1') {
          count++;
          queue.offer(new Pair(i, j));
          marked[i][j] = true;
          bfs(queue, marked, grid);
        }
      }
    }

    return count;
  }

  private void bfs(Queue<Pair> queue, boolean[][] marked, char[][] grid) {
    while (!queue.isEmpty()) {
      Pair cur = queue.poll();
      for (int[] dir : DIRS) {
        int x = cur.x + dir[0];  // x move in four directions
        int y = cur.y + dir[1];  // y move in four directions

        if (!inBound(x, y, grid) || grid[x][y] == '0' || marked[x][y]) continue;
        
        queue.offer(new Pair(x, y));
        marked[x][y] = true;
      }
    }
  }

  private boolean inBound(int x, int y, char[][] grid) {
    int rows = grid.length;
    int cols = grid[0].length;

    return (x >= 0 && x < rows && y >= 0 && y < cols);
  }
}
