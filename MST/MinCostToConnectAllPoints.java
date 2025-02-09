/**
 * 1584. Min Cost to Connect All Points
 * You are given an array points representing integer coordinates of some points on a 2D-plane, where points[i]=[xi, yi].
 *
 * The cost of connecting two points [xi, yi] and [xj, yj] is the manhattan distance between
 * them: |xi - xj| + |yi - yj|, where |val| denotes the absolute value of val.
 *
 * Return the minimum cost to make all points connected. All points are connected if there is exactly one simple path
 * between any two points.
 *
 * Input: points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * Output: 20
 * We can connect the points as shown above to get the minimum cost of 20.
 * Notice that there is a unique path between every pair of points.
 *
 * Input: points = [[3,12],[-2,5],[-4,1]]
 * Output: 
 * 
 * Constraints: 1 <= points.length <= 1000; -106 <= xi, yi <= 106; All pairs (xi, yi) are distinct.
 */
class MinCostToConnectAllPoints {
  
    // https://leetcode.com/problems/min-cost-to-connect-all-points/solutions/6391130/mst-prim-s-kruskal-s-min-heap-simple-and-easy-all-approaches/?envType=problem-list-v2&envId=minimum-spanning-tree
  
    // Time: O(N^2 log N) where while-lopp use N^2 to process all the edges, and pq operation takes (log N)
    // Space: O(N^2) size of pq
  
    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        boolean[] visited = new boolean[n]; 

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1])); // Min-Heap: [node, weight]
        pq.add(new int[]{0, 0}); // Start from node 0 with cost 0

        int mstCost = 0;
        int edgesUsed = 0;

        while (!pq.isEmpty() && edgesUsed < n) {
            int[] curr = pq.poll();

            int node = curr[0];
            int weight = curr[1];

            if (visited[node]) continue; 

            visited[node] = true; 
            mstCost += weight;
            edgesUsed++;

            // Add all edges from this node to the priority queue
            for (int nei = 0; nei < n; nei++) {
                if (!visited[nei]) {
                    int dist = distance(points, node, nei);
                    pq.add(new int[]{nei, dist});
                }
            }
        }

        return mstCost;
    }

    private int distance(int[][] points, int node, int nei) {
        return Math.abs(points[node][0] - points[nei][0]) + Math.abs(points[node][1] - points[nei][1]);
    }
}
