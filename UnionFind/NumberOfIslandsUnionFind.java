/**
 * 200. Number of Islands (Union Find)
 * Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of
 * islands.
 *
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may
 * assume all four edges of the grid are all surrounded by water.
 *
 * Input: grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * Output: 1
 *
 * Input: grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * Output: 3
 *
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] is '0' or '1'.
 */
/**
 * Implementation Details
 * Data Structures:
 * parent[]: An array where parent[i] stores the root of the set containing cell i.
 * rank[]: An array where rank[i] stores the depth of the tree rooted at cell i.
 * count: A variable to track the number of islands.
 *
 * Find Operation:
 * Recursively find the root of a cell while applying path compression to flatten the tree.
 *
 * Union Operation:
 * Find the roots of the two cells
 * If the roots are different, merge the sets using union by rank:
 * Attach the smaller tree to the root of the larger tree.
 * Update the rank of the new root if necessary.
 * Decrement the island count.
 *
 * Grid Indexing: Use a unique identifier for each cell
 * i * cols + j, where i is the row index and j is the column index.
 */
class Solution {
    // Time: O(M × N × α(M × N)) for visiting all cells with each union/find operations  
    // Space: O(M × N) for the parent and rank arrays
    private static final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private int[] parent;
    private int[] rank;
    private int count;

    private int rows;
    private int cols;

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        rows = grid.length;
        cols = grid[0].length;
        
        // Initialize Union-Find data structure
        parent = new int[rows * cols];
        rank = new int[rows * cols];
        count = 0;

        // Initialize parent and rank arrays
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    int id = i * cols + j;
                    parent[id] = id; // Each cell is its own parent initially
                    rank[id] = 1;   // Rank is 1 initially
                    count++;         // Increment island count
                }
            }
        }

        // Traverse the grid and perform union operations
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    int currentId = i * cols + j;
                    traverse(i, j, grid, currentId);
                }
            }
        }

        return count;
    }

    private void traverse(int i, int j, char[][] grid, int currentId) {
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];

            // if in-bound, has value of 1, then it's an island 
            if (check(x, y) && grid[x][y] == '1') {
                int neighborId = x * cols + y;
                union(currentId, neighborId);      
            }
        }
    }


    private boolean check(int x, int y) {
        return x >= 0 && x < rows && y >= 0 && y < cols;
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else {
                parent[rootY] = rootX;
                rank[rootX] += 1;
            }
            count--; // Decrement island count after union
        }
    }
}
