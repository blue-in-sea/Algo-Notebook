/**
 * This Code is for Demo only, no test case AC
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
  /**
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
  
  /**
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
/**
 * Time Complexity Analysis Decomposite
 * Each DFS calls itself (does not consider next level recursion): O(E/V + 1)
 * Each DFS markes one visied: O(1)
 * Each Node is only allowed to visited onece: O(V)
 * Total: O((E/V + 1) * V) = O(V + E)
 *
 * Key!! Try to find the invariant in the loop 
 *    !! 寻找(循环)不变量
 * 
 * Given V: (Range of E can be various, thus both V & E play a part
 *           into our Graph Traversal's complexity analysis)
 * E min -> 0
 * E     -> O(V)
 * E max -> O(V^2)
 * 
 * Only tree, we could use "n", since E = V + 1 is fixed in DAG
 */

