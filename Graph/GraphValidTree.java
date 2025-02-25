/**
 * 261. Graph Valid Tree
 * Given a graph of n nodes labeled from 0 to n - 1. And, given an integer n and a list of edges where
 * edges[i] = [ai, bi] indicates that there is an undirected edge between nodes ai and bi in the graph.
 *
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 *                      0
 *                 1    2    3
 *                 4
 *
 * Input: n = 5, edges = [[0,1],[0,2],[0,3],[1,4]]
 * Output: true
 *
 *
 */
/**
 * Tree is a directed acyclic graph in which
 * 1. any two vertices are connected by exactly one path
 * 2. has a root and all vertices connected
 * (so) any connected graph without simple cycles is a tree! => checkCycle()
 */
class GraphValidTree {
    /**
     * Key: Tree - A connected acyclic undirected graph
     * In graph theory, a tree is an undirected graph in which any two vertices are connected by exactly one path
     */
    
     // 1. 只在neighbor没有被遍历过时才放入queue中, 这样的话, 在遍历出队列时，如果遇到元素被二次访问就说明有cycle,
     // 2. 最后遍历visited，确认每一个元素都被遍历到，才是valid tree（没有落单的节点）
    
    // BFS
    // Time: O(V + E), Space: O(V)
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = buildGraph(n, edges);

        Set<Integer> set = new HashSet<>();

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);

        while (!queue.isEmpty()) {
            // expand and add to set
            Integer curr = queue.poll();
            if (!set.add(curr)) {
                return false;
            }

            // generate 
            for (Integer nei : graph.get(curr)) {
                if (!set.contains(nei)) {
                    queue.offer(nei);
                }
            }
        } 

        if (set.size() != n) {
            return false;
        }

        // if found no cycle, we need the default return value to be true - clarification
        return true;
    }

    // ==========================================================================
    // Helper
    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // initialize the graph nodes
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        // build the edges 
        for (int[] edge : edges) {
            // for undirected graph
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        return graph;
    }
    
    // ==========================================================================
    // DFS Approach Thinking: 类似BFS, 只是在recursive放入parent和当前节点 cur 时, 
    // 选择cur != parent才进行下一层hasCycle的递归, 这样如果出现二次访, 就说明了有cycle. 
    // Time: O(V + E)
    // Space: O(V) recursion stacks call
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = buildGraph(n, edges);

        boolean[] visited = new boolean[n];


        // make sure there is no cycle
        if (!dfs(graph, 0, visited, -1)) {
            return false;
        }


        // make sure all vertices are connected
        for (boolean marked : visited) {
            if (!marked) {
                return false;
            }
        }

        // if found no cycle, we need the default return value to be true - clarification
        return true;
    }

    // return true - if has no cycle 
    private boolean dfs(Map<Integer, List<Integer>> graph, int curr, boolean[] visited, int parent) {
        // base case: if the current node being visited, found a cycle
        if (visited[curr]) {
            return false;
        }

        visited[curr] = true;

        for (int nei : graph.get(curr)) {
            if (nei != parent && !dfs(graph, nei, visited, curr)) {
                return false;
            }
        }

        return true;
    }
}
