package GraphBFS;

public class NumberOfConnectedComponentsUndirectedGraph {
  /**
   * BFS
   */
    public int countComponents(int n, int[][] edges) {
    // corner case 
    if (n <= 1) return n;

    // initializa (adjacency list) graph map
    Map<Integer, List<Integer>> map = new HashMap<>();

    // intialize vertices
    for (int i = 0; i < n; i++) {
      map.put(i, new ArrayList<>());
    }

    // add edge
    for (int[] edge : edges) {
      map.get(edge[0]).add(edge[1]);
      map.get(edge[1]).add(edge[0]);
    }
    
    // DFS
    int component = 0;
    Set<Integer> visited = new HashSet<>();

    Queue<Integer> queue = new LinkedList<>();

    for (int i = 0; i < n; i++) {
      if (visited.add(i)) {
        component++;
        queue.offer(i);
        bfs(queue, visited, map);
      }
    }

    return component++;
  }

  private void bfs(Queue<Integer> queue, Set<Integer> visited, Map<Integer, List<Integer>> map) {
    while (!queue.isEmpty()) {
      int cur = queue.poll();
      
      for (int nei : map.get(cur)) {
        if (visited.add(nei)) {
          queue.offer(nei);
        }
      }
    } 
  }

  /**
   * DFS
   */
  public int countComponents(int n, int[][] edges) {
    // corner case 
    if (n <= 1) return n;

    // initializa (adjacency list) graph map
    Map<Integer, List<Integer>> map = new HashMap<>();

    // intialize vertices
    for (int i = 0; i < n; i++) {
      map.put(i, new ArrayList<>());
    }

    // add edge
    for (int[] edge : edges) {
      map.get(edge[0]).add(edge[1]);
      map.get(edge[1]).add(edge[0]);
    }
    
    // DFS
    int component = 0;
    Set<Integer> visited = new HashSet<>();
    for (int i = 0; i < n; i++) {
      if (visited.add(i)) {
        component++;
        dfs(i, visited, map);
      }
    }

    return component++;
  }

  private void dfs(int cur, Set<Integer> visited, Map<Integer, List<Integer>> map) {
    for (int nei : map.get(cur)) {
      if (visited.add(nei)) {
        dfs(nei, visited, map);
      }
    }
  }
}
