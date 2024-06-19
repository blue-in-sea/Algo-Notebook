/**
 * 133. Clone Graph
 * * DFS & BFS  for 1 connected graph
 * * DFS & BFS  for multiple graph
 *
 * Time: O(V + E) for V is # of nodes, E is # edges
 * Space: O(V) for stack calls & lookup map & copy graph
 */
class DeepCopyUndirectedGraph {
    // ======= 1. DFS for connected graph  ======= (all nodes connected in 1 graph)
    Map<Node, Node> lookup = new HashMap<>();

    public Node cloneGraph(Node node) {
        // corner case & base case
        if (node == null) return null;
        // base case: dedup 如果已经 visited 过了 return 给 parent 当前的 node
        if (lookup.containsKey(node)) return lookup.get(node);

        Node copy = new Node(node.val);
        lookup.put(node, copy);
        for (Node nei : node.neighbors) {
            copy.neighbors.add(cloneGraph(nei));
        }
        return copy;
    }
    /**
     * class Node {
     *     int val;
     *     public List<Integer> children;
     *     public Node(int val) {
     *         this.val = val;
     *         this.children = new ArrayList<Node>();
     *     }
     * }
     */

    // ======= 2. BFS for connected graph  =======
    public Node cloneGraph(Node node) {
        if (node == null) {
            return null;
        }

        Queue<Node> queue = new ArrayDeque<>();
        Map<Node, Node> visited = new HashMap<>();

        queue.offer(node);
        visited.put(node, new Node(node.val, new ArrayList()));

        while(!queue.isEmpty()) {
            Node curr = queue.poll();

            for (Node nei : curr.neighbors) {
                if (!visited.containsKey(nei)) {
                    queue.offer(nei);
                    visited.put(nei, new Node(nei.val, new ArrayList()));
                }

                visited.get(curr).neighbors.add(visited.get(nei));
            }
        }

        return visited.get(node);
    }

    // *****  Below if for Unconnected Graph ****** (more than 1 graphs given)

    // ======= 3. DFS for unconnected graph =======
    public List<GraphNode> copy(List<GraphNode> graph) {
        if (graph == null) {
            return null;
        }
        Map<GraphNode, GraphNode> map = new HashMap<>();
        for (GraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node, new GraphNode(node.key));
                dfs(node, map);
            }
        }
        return new ArrayList<GraphNode>(map.values());
    }

    private void dfs(GraphNode seed, Map<GraphNode, GraphNode> map) {
        GraphNode copy = map.get(seed);
        for (GraphNode nei : seed.neighbors) {
            if (!map.containsKey(nei)) {
                map.put(nei, new GraphNode(nei.key));
                dfs(nei, map);
            }
            copy.neighbors.add(map.get(nei));
        }
    }
    
    // ======= 4. BFS for unconnected graph =======
    public List<GraphNode> copy(List<GraphNode> graph) {
        if (graph == null) {
            return null;
        }
        List<GraphNode> result = new ArrayList<>(); // copy graph 
        Deque<GraphNode> queue = new ArrayDeque<>();
        Map<GraphNode, GraphNode> map = new HashMap<>();
        for (GraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node, new GraphNode(node.key));
                queue.offer(node);
                bfs(map, queue);
            }
            result.add(map.get(node));
        }
        return result;
    }

    private void bfs(Map<GraphNode, GraphNode> map, Deque<GraphNode> queue) {
        while (!queue.isEmpty()) {
            GraphNode curr = queue.poll();

            for (GraphNode nei : curr.neighbors) {
                if (!map.containsKey(nei)) {
                    map.put(nei, new GraphNode(nei.key));
                    queue.offer(nei);
                }
                // make the connection on copied graph
                map.get(curr).neighbors.add(map.get(nei));
            }
        }
    }
}


