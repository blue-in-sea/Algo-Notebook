import java.util.*;
/**
 * 1489. Find Critical and Pseudo-Critical Edges in Minimum Spanning Tree
 *
 * Given a weighted undirected connected graph with n vertices numbered from 0 to n - 1, and an array edges where
 * edges[i] = [ai, bi, weighti] represents a bidirectional and weighted edge between nodes ai and bi. A minimum
 * spanning tree (MST) is a subset of the graph's edges that connects all vertices without cycles and with the
 * minimum possible total edge weight.
 *
 * Find all the critical and pseudo-critical edges in the given graph's minimum spanning tree (MST). An MST edge
 * whose deletion from the graph would cause the MST weight to increase is called a critical edge. On the other hand,
 * a pseudo-critical edge is that which can appear in some MSTs but not all.
 *
 * Input: n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * Output: [[0,1],[2,3,4,5]]
 * Explanation: The figure above describes the graph.
 * 
 * Constraints: 2 <= n <= 100； 1 <= edges.length <= min(200, n * (n - 1) / 2)； edges[i].length == 3
 * 0 <= ai < bi < n; 1 <= weighti <= 1000; All pairs (ai, bi) are distinct.
 */
class FindCriticalAndPseudo-CriticalEdgesinMinimumSpanningTree {
    /**
     * Approach
     * 1. Compute the MST Weight:
     * Use Kruskal's Algorithm or Prim's Algorithm to compute the total weight of the MST for the given graph.
     *
     * 2. Identify Critical Edges:
     * For each edge, remove it from the graph and recompute the MST weight.
     * If the MST weight increases after removing the edge, it is a critical edge.
     *
     * 3. Identify Pseudo-Critical Edges:
     * For each edge, force it to be included in the MST and recompute the MST weight.
     * If the MST weight remains the same as the original MST weight, the edge is a pseudo-critical edge.
     *
     * 4. Return Results:
     * Collect the indices of critical and pseudo-critical edges and return them.
     */

    /**
     * let V = n given by input; E = number of egdes
     *
     * Time: O(E^2 + ElogE)
     * Space: O(V + E)
     *
     * 1. Preprocessing Edges O(E)
     * Adding index to edges => edgesWithIndex
     *
     * 2. Edge Sorting: O(ElogE)
     * The edges are sorted by weight to facilitate Kruskal's Algorithm.
     *
     * 3. Original MST Weight: O(ElogE)
     * The computeMSTWeight method calculates the MST weight using Kruskal's Algorithm withUnion-Find.
     * Kruskal’s algorithm runs in O(ElogE) due to sorting and performing Union-Find operations.
     *
     * 4. Identifying Critical and Pseudo-Critical Edges **
     * a) Critical Edges: O(E^2)
     * For each edge, the method checks if removing it increases the MST weight. If so, the edge is critical.
     * b) Pseudo-Critical Edges: O(E^2)
     * For each edge, the method checks if forcing it into the MST keeps the MST weight unchanged. If so, the edge is pseudo-critical.
     *
     * 5. Final Result Construction O(E)
     *
     * Note: Union-Find: used to manage connected components and detect cycles efficiently.
     */
    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        // Step 1: Add edge indices to the edges for tracking
        int m = edges.length;
        int[][] edgesWithIndex = new int[m][4];
        for (int i = 0; i < m; i++) {
            edgesWithIndex[i][0] = edges[i][0];
            edgesWithIndex[i][1] = edges[i][1];
            edgesWithIndex[i][2] = edges[i][2];
            edgesWithIndex[i][3] = i; // Store the original index
        }

        // Step 2: Sort edges by weight
        Arrays.sort(edgesWithIndex, Comparator.comparingInt(a -> a[2]));

        // Step 3: Compute the original MST weight
        int originalMSTWeight = computeMSTWeight(n, edgesWithIndex, -1, -1);

        // Step 4: Identify critical and pseudo-critical edges
        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudoCritical = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            // Check if the edge is critical
            int mstWithoutEdge = computeMSTWeight(n, edgesWithIndex, i, -1);
            if (mstWithoutEdge > originalMSTWeight) {
                critical.add(edgesWithIndex[i][3]);
                continue;
            }

            // Check if the edge is pseudo-critical
            int mstWithEdge = computeMSTWeight(n, edgesWithIndex, -1, i);
            if (mstWithEdge == originalMSTWeight) {
                pseudoCritical.add(edgesWithIndex[i][3]);
            }
        }

        // Step 5: Return the result
        List<List<Integer>> result = new ArrayList<>();
        result.add(critical);
        result.add(pseudoCritical);
        return result;
    }

    private int computeMSTWeight(int n, int[][] edges, int excludeEdge, int includeEdge) {
        UnionFind uf = new UnionFind(n);
        int mstWeight = 0;

        // Force include the specified edge
        if (includeEdge != -1) {
            int[] edge = edges[includeEdge];
            uf.union(edge[0], edge[1]);
            mstWeight += edge[2];
        }

        // Add edges to the MST
        for (int i = 0; i < edges.length; i++) {
            if (i == excludeEdge) continue; // Skip the excluded edge
            int[] edge = edges[i];
            if (uf.find(edge[0]) != uf.find(edge[1])) {
                uf.union(edge[0], edge[1]);
                mstWeight += edge[2];
            }
        }

        // Check if all nodes are connected
        if (uf.getCount() != 1) {
            return Integer.MAX_VALUE; // Not a valid MST
        }

        return mstWeight;
    }

    // Union-Find data structure
    private static class UnionFind {
        private int[] parent;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
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
                    rank[rootX]++;
                }
                count--;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
