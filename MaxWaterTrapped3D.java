public class MaxWaterTrapped3D {
  public int maxTrapped(int[][] matrix) {
    // assume matrix is not null, has size M * N
    // M > 0 & N > 0, all the values are non-negative integers
    int rows = matrix.length;
    int cols = matrix[0].length;
    if (rows < 3 || cols < 3) {
      return 0;
    }
    // Best-First-Search, minHeap maintains all border cells
    // on the "closed area" and we always find the one with lowest
    // height to see if any of its neighbors can trap any water
    PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>();
    boolean[][] visited = new boolean[rows][cols];

    // put all the border cells of the matrix at the beginning
    processBorder(matrix, visited, minHeap, rows, cols);

    int waterSum = 0;
    while (!minHeap.isEmpty()) {
      Pair cur = minHeap.poll();
      // get all possible neighbor cells
      List<Pair> neighbors = allNeighbors(cur, matrix, visited);

      for (Pair nei : neighbors) {
        if (visited[nei.x][nei.y]) {
          continue;
        }
        // adjust the neighbor cell's height to the current water level.
        // if necessary, mark the neighbor cell as visited, and offer 
        // the neighbor cell into minHeap 
        visited[nei.x][nei.y] = true;
        // how much water can be trapped at the neighbor cell
        waterSum += Math.max(cur.height - nei.height, 0);
        nei.height = Math.max(cur.height, nei.height);
        minHeap.offer(nei);
      }
    }
    return waterSum;
  }

    // the border cells are the starting points of the whole BFS process
  private void processBorder(int[][] matrix, boolean[][] visited, PriorityQueue<Pair> minHeap, int rows, int cols) { 
    for (int j = 0; j < cols; j++) {
      minHeap.offer(new Pair(0, j, matrix[0][j]));                // left border 
      minHeap.offer(new Pair(rows - 1, j, matrix[rows - 1][j]));  // right border
      visited[0][j] = true;
      visited[rows - 1][j] = true;
    }
      
    for (int i = 1; i < rows - 1; i++) {                          // top border
      minHeap.offer(new Pair(i, 0, matrix[i][0]));                // down border
      minHeap.offer(new Pair(i, cols - 1, matrix[i][cols - 1]));
      visited[i][0] = true;
      visited[i][cols - 1] = true;
    }
  }

  private List<Pair> allNeighbors(Pair cur, int[][] matrix, boolean[][] visited) {
    List<Pair> neis = new ArrayList<>();
    
    if (cur.x + 1 < matrix.length) { 
      neis.add(new Pair(cur.x + 1, cur.y, matrix[cur.x + 1][cur.y])); // 右
    }
    if (cur.x - 1 >= 0) {
      neis.add(new Pair(cur.x - 1, cur.y, matrix[cur.x - 1][cur.y])); // 左
    }
    if (cur.y + 1 < matrix[0].length) {
      neis.add(new Pair(cur.x, cur.y + 1, matrix[cur.x][cur.y + 1])); // 上
    }
    if (cur.y - 1 >= 0) {
      neis.add(new Pair(cur.x, cur.y - 1, matrix[cur.x][cur.y - 1])); // 下
    }
    return neis;
  }

  static class Pair implements Comparable<Pair> {
    int x; // row index
    int y; // col inde
    int height;
    
    public Pair(int x, int y, int height) {
      this.x = x;
      this.y = y;
      this.height = height;
    }
    
    @Override
    public int compareTo(Pair another) {
      if (this.height == another.height) {
        return 0;
      }
      return this.height < another.height ? -1 : 1;
    }
  }
}
