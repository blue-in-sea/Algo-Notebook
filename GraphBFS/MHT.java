/**
 * 310. Minimum Height Trees
 * A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any
 * connected graph without simple cycles is a tree.
 *
 * Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates
 * that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree
 * as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees,
 * those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).
 *
 * Return a list of all MHTs' root labels. You can return the answer in any order.
 *
 * The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.
 *
 * Input: n = 4, edges = [[1,0],[1,2],[1,3]]
 * Output: [1]
 * Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
 *
 * Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
 * Output: [3,4]
 *
 * Constraints:
 * 1 <= n <= 2 * 104
 * edges.length == n - 1
 * 0 <= ai, bi < n
 * ai != bi
 * All the pairs (ai, bi) are distinct.
 * The given input is guaranteed to be a tree and there will be no repeated edges.
 */
class MHT {
        /**
     * The problem requires us to find the root(s) of the Minimum Height Trees (MHTs) in a given undirected tree. The
     * height of a tree is defined as the number of edges on the longest path from the root to any leaf. The goal is
     * to find all such roots that minimize this height.
     *
     * Algorithm Explanation
     * Intuition: The MHT roots are the centroids of the tree. A centroid is a node that, when removed, splits the
     * tree into smaller subtrees with as balanced sizes as possible. For a tree, there can be at most two centroids.
     *
     * Key: all leaves node has indegree 1 =>
     *      or has nei size of 1 (1 edge points to parent)
     *
     * Approach:
     * Step 1: Build an adjacency list to represent the tree.
     * Step 2: Initialize a queue with all leaf nodes (nodes with degree 1).
     * Step 3: Perform a BFS-like layer-by-layer removal of leaf nodes. At each step, remove the current layer of
     *         leaves and add the new leaves formed after removal to the queue.
     * Step 4: The last remaining nodes in the queue are the centroids, which are the roots of the MHTs.
     */
  
    // Time: O(n) for visiting all nodes to build the graph 
    // Space: O(n) for queue size 
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n == 1) return Collections.singletonList(0);

        // Step 1: Build adjacency list
        List<Set<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // Step 2: Initialize queue with leaf nodes
        Queue<Integer> leaves = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() == 1) {
                leaves.offer(i);
            }
        }

        // Step 3: Remove leaves layer by layer
        int remainingNodes = n;
        while (remainingNodes > 2) {
            int size = leaves.size();
            remainingNodes -= size;
            for (int i = 0; i < size; i++) {
                int leaf = leaves.poll();
                int neighbor = adj.get(leaf).iterator().next();
                adj.get(neighbor).remove(leaf);
                if (adj.get(neighbor).size() == 1) {
                    leaves.offer(neighbor);
                }
            }
        }

        // Step 4: The remaining nodes are the centroids
        return new ArrayList<>(leaves);
    }
}
