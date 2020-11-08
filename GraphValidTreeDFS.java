public class GraphValidTreeDFS {
  /**
   * DFS Approach Thinking: 类似BFS, 只是在recursive放入parent和当前节点 cur 时, 
   * 选择cur != parent才进行下一层hasCycle的递归, 这样如果出现二次访, 就说明了有cycle. 
   *
   * Time: O(V + E)
   * Space: O(V) recursion stacks call 
   */
  // version 1: dfs helper default return as true, if found no cycle
  public boolean validTree(int n, int[][] edges) {
    // initialize (adjacency list) graph map 
    Map<Integer, List<Integer>> map = new HashMap<>();

    // intialize vertices
    for (int i = 0; i < n; i++) {
      map.put(i, new ArrayList<Integer>());
    }

    // add edges: undirected graph (each edge is a pair of nodes)
    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0], v = edges[i][1];
      map.get(u).add(v);
      map.get(v).add(u);
    }

    boolean[] visited = new boolean[n];

    // make sure there is no circle 
    if (!dfs(map, 0, visited, -1)) {
      return false;
    }

    // make sure all vertices are connected
    for(boolean marked : visited){
      if(!marked) {
        return false;
      }
    }

    return true;
  }
  
  private boolean dfs(Map<Integer, List<Integer>> map, int cur, boolean[] visited, int parent) {
    if(visited[cur]) {
      return false;
    }
    visited[cur] = true;

    for (int nei : map.get(cur)) {
      if(nei != parent && !dfs(map, nei, visited, cur)){
        return false;
      }
    }

    return true;
  }
  
  
  // version 2: dfs helper default return as false, if cannot prove for every node, they contains no cycle 
  public boolean validTree(int n, int[][] edges) {
    // initialize adjacency list
    List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

    // intialize vertices
    for (int i = 0;  i < n; i++) {
      adjList.add(i, new ArrayList<Integer>());
    }

    // add edges: undirected graph (each edge is a pair of nodes)
    for (int i = 0; i < edges.length; i++) {
      int u = edges[i][0], v = edges[i][1];
      adjList.get(u).add(v);
      adjList.get(v).add(u);
    }

    boolean[] visited = new boolean[n];

    // make sure there is no circle 
    if (hasCycle(adjList, 0, visited, -1)) {
      return false;
    }

    // make sure all vertices are connected
    for (int i = 0; i < n; i++) { 
      if (!visited[i]) {
        return false;
      }
    }

    return true;
  }

  private boolean hasCycle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
    visited[u] = true;

    for (int i = 0; i < adjList.get(u).size(); i++) {
      int v = adjList.get(u).get(i);

      if ((visited[v] && parent != v) || (!visited[v] && (!visited[v] && hasCycle(adjList, v, visited, u)))) {
        return true;
      }
    }

    return false;
  }
}


