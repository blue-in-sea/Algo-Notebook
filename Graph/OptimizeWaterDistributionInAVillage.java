// https://leetcode.com/problems/optimize-water-distribution-in-a-village/
// MST (see https://leetcode.com/problems/connecting-cities-with-minimum-cost/description/)
class OptimizeWaterDistributionInAVillage {
    public int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
         Map<Integer, List<int[]>> graph = buildGraph(n, wells, pipes);

        // Prim's algorithm using a Priority Queue
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n + 1];
        int totalCost = 0;
        int edgesUsed = 0;

        // Starting from 1st house
        for (int i = 0; i < wells.length; ++i) {
            int w = i + 1;
            int c = wells[i];
            pq.offer(new int[]{w, c});
        }

        while (!pq.isEmpty() && edgesUsed < n) {
            int[] curr = pq.poll();

            int city = curr[0];
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
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < wells.length; ++i) {
            int w = i + 1;
            int c = wells[i];
            graph.get(1).add(new int[]{w, c});
        }
        for (int i = 0; i < pipes.length; i++) {
            int u = pipes[i][0];
            int v = pipes[i][1];
            int c = pipes[i][2];
            graph.get(u).add(new int[]{v, c});
            graph.get(v).add(new int[]{u, c});
        }
        return graph;
    }
}
