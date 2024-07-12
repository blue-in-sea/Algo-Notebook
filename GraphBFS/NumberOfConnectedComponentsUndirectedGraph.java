/**
 * 323. Number of Connected Components in an Undirected Graph
 * Input: A graph of n nodes with a list of edges,
 * Output: the number of connected components in the graph
 *
 * Input: n = 5, edges = [[0,1],[1,2],[3,4]]
 *     0 - 1             3
 *        |    and      |
 *        2             4
 * Output: 2
 * 
 * 
 * Input: n = 5, edges = [[0,1],[1,2],[2,3],[3,4]]
 *     0 - 1      3
 *        |   /   |
 *        2       4
 * Output: 1
 */
class NumberOfConnectedComponentsUndirectedGraph {
    // E = Number of edges, V = Number of vertices
    // Time: O(V + E) 
    //       Building the adjacency list will take O(E) since we iterate over the list of edges once
    //       Graph traversal we visited every vertex once; for each vertex, we vised all its edges 
    // Space: O(V) 
    //        Size of graph 

    // BFS
    public int countComponents(int n, int[][] edges) {
        if (n <= 1) return n;

        Map<Integer, List<Integer>> graph = buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        int component = 0;
        for (int i = 0; i < n; i++) {
            if (visited.add(i)) {
                component++;
                bfs(graph, queue, visited, i);
            }
        }

        return component++;
    }

    private void bfs(Map<Integer, List<Integer>> graph, Queue<Integer> queue, Set<Integer> visited, int start) {
        queue.offer(start);
        
        while (!queue.isEmpty()) {
            int cur = queue.poll();

            for (int nei : graph.get(cur)) {
                if (visited.add(nei)) {
                    queue.offer(nei);
                }
            }
        }
    }

    // ***********************************************************************************************
    // DFS
    public int countComponents(int n, int[][] edges) {
        if (n <= 1) return n;

        Map<Integer, List<Integer>> graph = buildGraph(n, edges);
        Set<Integer> visited = new HashSet<>();

        int component = 0;
        for (int i = 0; i < n; i++) {
            if (visited.add(i)) {
                component++;
                dfs(graph, visited, i);
            }
        }

        return component++;
    }

    private void dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int curr) {
        for (int nei : graph.get(curr)) {
            if (visited.add(nei)) {
                dfs(graph, visited, nei);
            }
        }
    }
    
    // ***********************************************************************************************
    // Build the undirected (unconnect) graph
    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // intialize vertices
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }
        // add edge
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }
}
