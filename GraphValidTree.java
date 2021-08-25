class GraphValidTree {
    // BFS
    // Time: O(V + E)
    // Space: O(V) for tree as undirected cyclical graph in which any two vertices are 
    // connected by exactly one path. 
    // In other words, any connected graph without simple cycles is a tree
    public boolean validTree(int n, int[][] edges) {
        Map<Integer, List<Integer>> graph = buildGraph(n, edges);

        Set<Integer> set = new HashSet<>();

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(0);
        // 只在neighbor没有被遍历过时才放入queue中, 这样的话,
        // 在遍历出队列时，如果遇到元素被二次访问就说明有cycle,
        // 最后遍历visited，确认每一个元素都被遍历到，才是valid tree（没有落单的节点）
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
  
  
}