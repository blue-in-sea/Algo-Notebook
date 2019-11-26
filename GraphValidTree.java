public class GraphValidTree {
  
  // Method 2: DFS
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
    if (hasCircle(adjList, 0, visited, -1)) {
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

  private boolean hasCircle(List<List<Integer>> adjList, int u, boolean[] visited, int parent) {
    visited[u] = true;

    for (int i = 0; i < adjList.get(u).size(); i++) {
      int v = adjList.get(u).get(i);

      if ((visited[v] && parent != v) || (!visited[v] && (!visited[v] && hasCircle(adjList, v, visited, u)))) {
        return true;
      }
    }

    return false;
  }

  // Method 3: Union Find
  public boolean validTree(int n, int[][] edges) {
    // intialize n isolate islands
    int[] nums = new int[n];
    Arrays.fill(nums, -1);

    // perform union find
    for (int i = 0; i < edges.length; i++) {
      int x = find(nums, edges[i][0]);
      int y = find(nums, edges[i][1]);

      // if two vertices happen to be in the same set
      // then there's a cycle
      if (x == y) return false;

      // union
      nums[x] = y;
    }

    return edges.length == n - 1;
  }

  private int find(int[] nums, int i) {
    if (nums[i] == -1) return i;
    return find(nums, nums[i]);
  }
}
