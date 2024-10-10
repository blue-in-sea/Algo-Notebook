/**
 * 56. Bipartite
 * Determine if an undirected graph is bipartite. A bipartite graph is one in which the nodes can be divided into two
 * groups such that no nodes have direct edges to other nodes in the same group.
 *
 * 1  --   2
 *     /   
 * 3  --   4
 *
 * is bipartite (1, 3 in group 1 and 2, 4 in group 2).
 *
 * 1  --   2
 *     /   |
 * 3  --   4
 *
 * is not bipartite.
 *
 * Assumptions
 *
 * The graph is represented by a list of nodes, and the list of nodes is not null.
 */
public class Bipartite {
    public boolean isBipartite(List<GraphNode> graph) {
        // use 0 and 1 denote two different groups
        // the map maintains for each node which group it belongs to
        HashMap<GraphNode, Integer> visited = new HashMap<GraphNode, Integer>();
        // the graph can be represented as a list of nodes (if it not guaranted
        // to be connected). We have to do BFS from each of nodes.
        for (GraphNode node : graph) {
            if (!BFS(node, visited)) {
                return false;
            }
        }
        return true;
    }

    private boolean BFS(GraphNode node, HashMap<GraphNode, Integer> visited) {
        // if this node has been traversed, no need to do BFS again
        if (visited.containsKey(node)) {
            return true;
        }
        Queue<GraphNode> queue = new LinkedList<GraphNode>();
        queue.offer(node);
        // start breadth-first from the node, since the node has not been visied
        // we can assign it to any of the group, e.g., group 0
        visited.put(node, 0);
        while (!queue.isEmpty()) {
            GraphNode cur = queue.poll();
            // the group assigned for cur node
            int curGroup = visited.get(cur);
            int neiGroup = curGroup == 0 ? 1 : 0;
            for (GraphNode nei : cur.neighbors) {
                // if the neighbor has noot been visited, just put it in the queue
                // and choose the correct group
                if (!visited.containsKey(nei)) {
                    visited.put(nei, neiGroup);
                    queue.offer(nei);
                } else if (visited.get(nei) != neiGroup){
                    // only if the neighbor has not been traversed 
                    // match to the desire one, return false.
                    return false;
                }
                // if the neighbor has been traversed and the group
                // matches to the desire one, we do not need do anything
            }
        }
        return true;
    }
}
/**
 * public class GraphNode {
 *   public int key;
 *   public List<GraphNode> neighbors;
 *   public GraphNode(int key) {
 *     this.key = key;
 *     this.neighbors = new ArrayList<GraphNode>();
 *   }
 * }
 */
