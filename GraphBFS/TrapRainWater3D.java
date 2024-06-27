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
public class TrapRainWater3D {
    /**
     * For every point Pi <xi, yi, zi> on the border, set the water level to zi
     * For every point Pj not on the border, set the water level to INFINITY
     *
     * Insert all Pi into MIN_HEAP = (set of all active points) as start boundary 
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

    // Time Complexity of BFS + minHeap: O( V + ElogV ) vs. Regular BFS (V + E)
    // Space Complexity: O(V)
    public int trapRainWater(int[][] heightMap) {
        // assume matrix is not null, has size M * N
        // M > 0 & N > 0, all the values are non-negative integers
        int M = heightMap.length;      // M - rows
        int N = heightMap[0].length;   // N - cols
        if (M < 3 || N < 3) return 0;

        // Best-First-Search, minHeap maintains all border cells
        // on the "closed area" and we always find the one with lowest
        // height to see if any of its neighbors can trap any water
        PriorityQueue<Pair> minHeap = new PriorityQueue<Pair>((x, y) -> x.h - y.h);
        boolean[][] visited = new boolean[M][N];
        
        // put all the border cells as the start point of BFS
        processBorder(heightMap, visited, minHeap, M, N);

        int waterSum = 0;
        while (!minHeap.isEmpty()) {
            Pair curr = minHeap.poll();

            // get all possible neighbor cells
            List<Pair> neis = findAllNeis(curr, heightMap, visited);

            for (Pair nei : neis) {
                if (visited[nei.x][nei.y]) continue;
                // adjust the nei cell's height to the current water level
                // if necessary, mark the neighbor cell as visited,
                // and offer the neighbor cell into minHeap 
                visited[nei.x][nei.y] = true;

                // key!! how muchh water can be trapped at the this nei cell
                waterSum += Math.max(curr.h - nei.h, 0);
                nei.h = Math.max(curr.h, nei.h);

                // offer into queue as the new border cell
                minHeap.offer(nei);
            }
        }
        return waterSum;
    }

    private final int[][] DIRS = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};

    private List<Pair> findAllNeis(Pair curr, int[][] heightMap, boolean[][] visited) {
        int M = heightMap.length;      // M - rows
        int N = heightMap[0].length;   // N - cols

        List<Pair> neis = new ArrayList<>();

        for (int[] dir : DIRS) {
            int x = curr.x + dir[0];
            int y = curr.y + dir[1];
            if (x >= 0 && x < M && y >= 0 && y < N) {
                neis.add(new Pair(x, y, heightMap[x][y])); 
            }
        }

        return neis;
    }

    // the border cells are the starting points of the whole BFS process
    private void processBorder(int[][] heightMap, boolean[][] visited, PriorityQueue<Pair> minHeap, int rows, int cols) {
        for (int j = 0; j < cols; j++) {
            minHeap.offer(new Pair(0, j, heightMap[0][j]));                 // topBorder 
            minHeap.offer(new Pair(rows - 1, j, heightMap[rows - 1][j]));   // downBorder 
            visited[0][j] = true;
            visited[rows - 1][j] = true;
        }
        for (int i = 0; i < rows - 1; i++) {
            minHeap.offer(new Pair(i, 0, heightMap[i][0]));                   // leftBorder
            minHeap.offer(new Pair(i, cols - 1, heightMap[i][cols - 1]));     // rightBorder
            visited[i][0] = true;
            visited[i][cols - 1] = true;
        }
    }

    class Pair {
        int x;
        int y;
        int h;

        public Pair(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }
}

/**
 *     static class Pair implements Comparable<Pair> {
 *         int x;
 *         int y;
 *         int h;
 *
 *         public Pair(int x, int y, int h) {
 *             this.x = x;
 *             this.y = y;
 *             this.h = h;
 *         }
 *
 *         @Override
 *         public int compareTo(Pair another) {
 *             if (this.h == another.h) {
 *                 return 0;
 *             }
 *             return this.h < another.h ? -1 : 1; // minHeap
 *         }
 *     }
 */
