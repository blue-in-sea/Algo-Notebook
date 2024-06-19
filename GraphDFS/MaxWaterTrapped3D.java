package GraphDFS;

/**
 * Given a non-negative integer 2D array representing the heights of bars in a matrix. 
 * Suppose each bar has length and width of 1. Find the largest amount of water that can be trapped in the matrix. 
 * The water can flow into a neighboring bar if the neighboring bar's height is smaller than the water's height. 
 * Each bar has 4 neighboring bars to the left, right, up and down side.
 * 
 * Assumptions: 
 * The given matrix is not null and has size of M * N, where M > 0 and N > 0, all the values are non-negative integers in the matrix.
 * Examples:
 * { { 2, 3, 4, 2 },
 *   { 3, 1, 2, 3 },
 *  { 4, 3, 5, 4 } }
 * the amount of water can be trapped is 3, 
 * at position (1, 1) there is 2 units of water trapped,
 * at position (1, 2) there is 1 unit of water trapped.   
 */

public class MaxWaterTrapped3D {
  /** 
   * Data Str: min heap 
   * (heap stores <i, j> of the nodes in the matrix, sorted from min to max based on node's height)
   * 
   * Algo:
   * 1) first, put all border nodes into the heap, every node's value is the water-level height
   * !! lowest node's value represents the lowest height of entire matrix
   * 2) second, pop min from heap, the node popped has the lowest height
   * 3) third, generate all neighbors of the node
   * !! 每个neighbor的水位的最低可能值，就是当前node的水位 !!
   *
   * The entire process starts from the 4-borders of the matrix, and gradually define the higher's the water-level height
   */
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
        
        // key!! how much water can be trapped at the neighbor cell
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
      minHeap.offer(new Pair(0, j, matrix[0][j]));                   // leftBorder 
      minHeap.offer(new Pair(rows - 1, j, matrix[rows - 1][j]));     // rightBorder
      visited[0][j] = true;
      visited[rows - 1][j] = true;
    }
      
    for (int i = 1; i < rows - 1; i++) {                             // topBorder
      minHeap.offer(new Pair(i, 0, matrix[i][0]));                   // downBorder
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

/**
 * For every point Pi <xi, yi, zi> on the border, set the water level to zi
 * For every point Pj not on the border, set the water level to INFINITY
 * Insert all Pi into MIN_HEAP = (set of all active points)
 * 
 * While MIN_HEAP is not empty:           // Best-First-Search (BFS2)
 *      P = MIN_HEAP.pop();               // Select the active P with the minimum level
 *      SUM += Level(P) - Height(P)
 *       
 *      For every point Q adjacent to P:
 *          If Level(Q) is INFINITY:      // dedup
 *          Level(Q) = max( Height(Q), Level(P) ) // !! key
 *          Offer Q into MIN_HEAP
 */
