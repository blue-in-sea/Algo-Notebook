/**
 * Data Str. min heap
 * 1.0 K Smallest In Unsorted Array 
 * 1.1 Kth Smallest Number In Sorted Matrix
 * 1.2 Kth Smallest Sum In Two Sorted Arrays
 */
public class KthSmallestSumInTwoSortedArrays {
    class Cell {
    public int x, y, sum;
    public Cell(int x, int y, int sum) {
      this.x = x;
      this.y = y;
      this.sum = sum;
    }
  }
  
  class CellComparator implements Comparator<Cell> {
    @Override 
    public int compare(Cell c1, Cell c2) {
      return c1.sum - c2.sum;  // mim heap
    }
  }
  public int kthSum(int[] A, int[] B, int k) {
    // assume The two given arrays are not null 
    // and at least one of them is not empty
    // 1 <= K <= total lengths of the two sorted arrays
    PriorityQueue<Cell> minHeap = new PriorityQueue<Cell>(k, new CellComparator());
    boolean[][] visited = new boolean[A.length][B.length];

    minHeap.offer(new Cell(0, 0, A[0] + B[0]));
    visited[0][0] = true;

    for (int i = 0; i < k - 1; i++) {
      Cell c = minHeap.poll();
      // the next cell will be insert back to minHeap only if
      // 1. it is still in the boundary
      // 2. it has never been generated before, since no cell allows to be generated twice
      if (c.x + 1 < A.length && !visited[c.x + 1][c.y]) {
        minHeap.offer(new Cell(c.x + 1, c.y, A[c.x + 1] + B[c.y]));
        visited[c.x + 1][c.y] = true;
      }
      if (c.y + 1 < B.length && !visited[c.x][c.y + 1]) {
        minHeap.offer(new Cell(c.x, c.y + 1, A[c.x] + B[c.y + 1]));
        visited[c.x][c.y + 1] = true;
      } 
    }

    return minHeap.peek().sum;
  }
}
