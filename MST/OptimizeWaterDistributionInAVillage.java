/**
 * 1168. Optimize Water Distribution in a Village
 *
 * There are n houses in a village. We want to supply water for all the houses by building wells and laying pipes.
 *
 * For each house i, we can either build a well inside it directly with cost wells[i - 1] (note the -1 due to
 * 0-indexing), or pipe in water from another well to it.
 *
 * The costs to lay pipes between houses are given by the array pipes where each pipes[j] = [house1j, house2j, costj]
 * represents the cost to connect house1j and house2j together using a pipe. Connections are bidirectional,
 * and there could be multiple valid connections between the same two houses with different costs.
 *
 * Return the minimum total cost to supply water to all houses.
 *
 * Example 1:
 * Input: n = 3, wells = [1,2,2], pipes = [[1,2,1],[2,3,1]]
 * Output: 3
 * Explanation: The image shows the costs of connecting houses using pipes.
 * The best strategy is to build a well in the first house with cost 1 and
 * 
 * Constraints:
 * 2 <= n <= 104
 * wells.length == n
 * 0 <= wells[i] <= 105
 * 1 <= pipes.length <= 104
 * pipes[j].length == 3
 * 1 <= house1j, house2j <= n
 * 0 <= costj <= 105
 * house1j != house2j
 */
class OptimizeWaterDistributionInAVillage {
    // Time: O(V+E + ElogE) = O(V + ElogE) 
    // Space: O(V+E)
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
         Map<Integer, List<int[]>> graph = buildGraph(n, wells, pipes);

        // Prim's algorithm using a Priority Queue [dest, cost]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n + 1];
        int totalCost = 0;
        int edgesUsed = 0;

        // Adding all virtual edges to Priority Queue 
        for (int i = 0; i < wells.length; ++i) {
            int w = i + 1;
            int c = wells[i];
            pq.offer(new int[]{w, c});
        }

        while (!pq.isEmpty() && edgesUsed < n) {
            int[] curr = pq.poll();

            int city = curr[0]; // dest
            int cost = curr[1];

            if (visited[city]) continue;

            // Mark the house as visited
            visited[city] = true;
            totalCost += cost;
            edgesUsed++;

            // Add all edges from the current house to the queue
            for (int[] neighbor : graph.get(city)) {
                if (!visited[neighbor[0]]) {
                    if (!visited[neighbor[0]]) {
                        pq.offer(new int[]{neighbor[0], neighbor[1]});
                    }
                }
            }
        }

        // If we haven't used n edges, it means we couldn't connect all house
        return edgesUsed == n ? totalCost : -1;
    }

    // Build undirected graph
    // Key: node, Value: [dest, cost]
    private Map<Integer, List<int[]>> buildGraph(int n, int[] wells, int[][] pipes) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 0; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < wells.length; i++) {
            int w = i + 1;
            int c = wells[i];
            graph.get(0).add(new int[]{w, c});
        }
        
        for (int i = 0; i < pipes.length; i++) {
            int u = pipes[i][0];
            int v = pipes[i][1];
            int c = pipes[i][2];
            graph.get(u).add(new int[]{v, c});
            graph.get(v).add(new int[]{u, c});
        }

        printGraph(graph);
        return graph;
    }

    private void printGraph(Map<Integer, List<int[]>> graph) {
        for (Map.Entry<Integer, List<int[]>> entry : graph.entrySet()) {
            System.out.print("key: " + entry.getKey());
            System.out.print(": ");
            printValue(entry.getValue());
            System.out.println();
        }
    }

    private void printValue(List<int[]> value) {
        System.out.print("<" );
        for (int[] arr : value) {
            printArr(arr);
        }
        System.out.print(">" );
    }

    private void printArr(int[] arr) {
        System.out.print("[" );
        for (int a : arr) {
            System.out.print(a + " "); 
        }
        System.out.print("]" );
    }
}
