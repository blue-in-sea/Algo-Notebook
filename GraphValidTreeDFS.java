public class GraphValidTreeDFS {
  /**
   * DFS Approach Thinking: 类似BFS, 只是在recursive放入parent和当前节点 cur 时, 
   * 选择cur != parent才进行下一层hasCycle的递归, 这样如果出现二次访, 就说明了有cycle. 
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
}
