/**
 * 1135. Connecting Cities With Minimum Cost
 * https://leetcode.com/problems/connecting-cities-with-minimum-cost/description/
 *
 * There are n cities labeled from 1 to n. You are given the integer n and an array connections where connections[i]
 * = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.
 *
 * Return the minimum cost to connect all the n cities such that there is at least one path between each pair of
 * cities. The cost is the sum of the connections' costs used.
 * If it is impossible to connect all the n cities, return -1. 
 * 
 * Example
 * Input: n = 3, connections = [[1,2,5],[1,3,6],[2,3,1]] 
 * Output: 6
 *
 * Input: n = 4, connections = [[1,2,3],[3,4,4]]
 * Output: -1
 */
class ConnectingCitiesWithMinimumCost {
   /**
     * Prim's algorithm to find the minimum spanning tree
     * 
     * 1. Build Graph: O(V+E)
     * Constructs an adjacency list representation of the graph.
     * Each node stores a list of its neighbors along with the edge weights.
     *
     * 2. Prim's Algorithm: O(ElogE)  
     * Uses a Priority Queue (Min-Heap) to always select the smallest edge 
     * connecting a vertex in the MST to a vertex outside the MST.
     *
     * Keeps track of visited vertices to avoid cycles.
     */
    
    // Time: O(V+ElogE) 
    // Space: O(V+E) size of graph
    public int minimumCost(int n, int[][] connections) {
        
        Map<Integer, List<int[]>> graph = buildGraph(n, connections);

        // Prim's algorithm using a Priority Queue
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        boolean[] visited = new boolean[n + 1];
        int totalCost = 0;
        int edgesUsed = 0;

        // Start from any city, here starting from city 1
        pq.offer(new int[]{1, 0});

        while (!pq.isEmpty() && edgesUsed < n) {
            int[] curr = pq.poll();

            int city = curr[0];
            int cost = curr[1];

            if (visited[city]) continue;

            // Mark the city as visited
            visited[city] = true;
            totalCost += cost;
            edgesUsed++;

            // Add all edges from the current city to the queue
            for (int[] neighbor : graph.get(city)) {
                if (!visited[neighbor[0]]) {
                    pq.offer(new int[]{neighbor[0], neighbor[1]});
                }
            }
        }

        // If we haven't used n edges, it means we couldn't connect all cities
        return edgesUsed == n ? totalCost : -1;
    }

    // Build undirected graph
    // Key: node, Value: [dest, cost]
    private Map<Integer, List<int[]>> buildGraph(int n, int[][] connections) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int i = 0; i < connections.length; i++) {
            int u = connections[i][0];
            int v = connections[i][1];
            int c = connections[i][2];
            graph.get(u).add(new int[]{v, c});
            graph.get(v).add(new int[]{u, c});
        }
        return graph;
    }
}

