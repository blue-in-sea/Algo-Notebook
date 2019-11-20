public class KthSmallestInSortedMatrix {
  /**
    * @param matrix: a matrix of integers
    * @param k: An integer
    * @return: the kth smallest number in the matrix
    */
  class Cell {
    public int x, y, val;
    public Cell(int x, int y, int val) {
      this.x = x;
      this.y = y;
      this.val = val;
    }
  }
  
  class CellComparator implements Comparator<Cell> {
    @Override 
    public int compare(Cell c1, Cell c2) {
        return c1.val - c2.val;
    }
  }

  public int kthSmallest(int[][] matrix, int k) {
    int rows = matrix.length;
    int cols = matrix[0].length;

    PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new CellComparator());
    boolean[][] visited = new boolean[rows][cols];

    // all generated cells will be marked to true in the visited
    minHeap.offer(new Cell(0, 0, matrix[0][0]));
    visited[0][0] = true;

    for (int i = 0; i < k - 1; i++) {
      Cell c = minHeap.poll();
      // the neighbor cell will be insert back to minHeap only if
      // 1. it is still in the boundary
      // 2. it has never been generated before, since no cell allows to be generated twice 
      if (c.x + 1 < rows && !visited[c.x + 1][c.y]) {
        minHeap.offer(new Cell(c.x + 1, c.y, matrix[c.x + 1][c.y]));
        visited[c.x + 1][c.y] = true;
      }
      if (c.y + 1 < cols && !visited[c.x][c.y + 1]) {
        minHeap.offer(new Cell(c.x, c.y + 1, matrix[c.x][c.y + 1]));
        visited[c.x][c.y + 1] = true;
      }
    }

    return minHeap.peek().val;
  }
}
