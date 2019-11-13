/* *
 * Each Graph Node has a field visited to mark if is visited. 
 * Traverse all nodes in Graph<ListNode> graph (adjacency list).
 */

class GraphNode {
  public int value;
  public List<GraphNode> nei;
  boolean visited;
  
  public GraphNode(int value) {
    this.value = value;
    nei = new ArrayList<>();
    visited = false;
  }
}

public class GraphTraversalComplexity {
  /* *
   * DFS Traversal: Complexity Analysis 
   * Time: O(V * (1 + E/V)) = O(V + E)
   * Spcae: O(V) 
   */
  public void traverse(Graph<ListNode> graph) {
    for (GraphNode n : graph) {
      if (!n.visited) {
        dfs(n);
      }
    }
  }
  
  /* *
   * V := Number of Nodes 
   * E := Number of Edges 
   */
  priavte dfs(GraphNode node) { // O(V)
    node.visited = true; // O(1)
    for (GraphNode n : node.nei) { // O(E/V)
      if (!n.visited) {
        dfs(n);
      }
    }
  }

}

/* *
 * Given V: # of vertices on the graph 
 * E min -> 0
 * E     -> O(V)
 * E max -> O(V^2)
 */

