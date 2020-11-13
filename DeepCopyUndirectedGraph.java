class Node {
    int val;
    List<Node> neighbors;

    public Node(int val) {
        this.val = val;
        neighbors = new ArrayList<Node>();
    }
}

class DeepCopyUndirectedGraph {
    Map<Node, Node> lookup = new HashMap<>();

    public Node cloneGraph(Node node) {
        // corner case & base case 
        if (node == null) return null;
        // base case: dedup 如果已经 visited 过了 return 给 parent ptr
        if (lookup.containsKey(node)) return lookup.get(node);

        Node copy = new Node(node.val);
        lookup.put(node, copy);
        for (Node nei : node.neighbors) {
            copy.neighbors.add(cloneGraph(nei));
        }
        return copy;

    }
    
    
    // ======= for unconnected graph =======
    public List<GraphNode> copy(List<GraphNode> graph) {
        if (graph == null) {
            return null;
        }
        Map<GraphNode, GraphNode> map = new HashMap<>();
        for (GraphNode node : graph) {
            if (!map.containsKey(node)) {
                map.put(node, new GraphNode(node.key));
                DFS(node, map);
            }
        }
        return new ArrayList<GraphNode>(map.values());
    }

    private void DFS(GraphNode seed, Map<GraphNode, GraphNode> map) {
        GraphNode copy = map.get(seed);
        for (GraphNode nei : seed.neighbors) {
            if (!map.containsKey(nei)) {
                map.put(nei, new GraphNode(nei.key));
                DFS(nei, map);
            }
            copy.neighbors.add(map.get(nei));
        }
    }
}


