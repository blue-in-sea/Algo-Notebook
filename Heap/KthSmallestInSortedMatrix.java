/*
int[][] matrix = {
    {1, 5, 9},
    {10, 11, 13},
    {12, 13, 15}
};
int k = 8; output expect the 8th smallest element is 13.
*/

//         minHeap             Binary Search
// T       O(klogk)            O(nlog(max-min))
// S       O(n^2 + k)          O(1)

// when K << n^2 -> Heap 
// when K ~ n^2  -> Binary Search since the complexity does not depends on K

/*
Method 1: Priority Queue (minHeap) 

  For each of the k-1 extractions, it runs 
      1 extraction O(logk)
      2 insertions, O(logk) each 
      
Time: O(klogk)
Space: O(n^2 + k) due to visited + heap size 
*/
public class KthSmallestInSortedMatrix {
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
    // PriorityQueue<Cell> minHeap = new PriorityQueue<>((c1, c2) -> c1.val - c2.val); 
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

/*
Method 2: Binary Search + Cnt Smalle or Equal
Time: O(nlog(max - min)) or O(nlog(range)) 
    1. binary search takes log(max - min), where max is the largest ele & min is the smallest ele from the matrix
    2. cnt element takes O(n)
Space: O(1) constant time
*/
class KthSmallestInSortedMatrixBinarySearch {
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length;
        int l = matrix[0][0];
        int r = matrix[n - 1][n - 1];

        while (l < r) {
            int m = l + (r - l) / 2;
            int cnt = cntLessOrEqual(matrix, m);

            if (cnt < k) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        return l;
    }

    private int cntLessOrEqual(int[][] matrix, int target) {
        int n = matrix.length;
        int cnt = 0;
        int row = 0; 
        int col = n - 1;

        while (row < n && col >= 0) {
            if (matrix[row][col] <= target) {
                cnt += col + 1;
                row++;
            } else {
                col--;
            }
        }

        return cnt;
    }
}

