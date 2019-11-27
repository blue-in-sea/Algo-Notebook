public class GraphValidTreeBFS {
  /**
   * BFS Approach Thinking: 只在neighbor没有被遍历过时才放入queue中
   * 这样，在遍历出队列时，如果遇到元素被二次访问就说明有cycle,
   * 最后遍历visited[]，确认每一个元素都被遍历到，才是valid tree（没有落单的节点）
   */
  // version 1: boolean array
  // Time: O(V + E) for building graph takes O(E), and BFS takes O(V + E)
  // Space: O(V) for boolean array
  public boolean validTree(int n, int[][] edges) {
    // initializa (adjacency list) graph map
    Map<Integer, List<Integer>> map = new HashMap<>(n);

    // intialize vertices
    for (int i = 0; i < n; i++) {
      map.put(i, new ArrayList<Integer>());
    }

    // add edges : “a tree is an undirected graph in which any two vertices are 
    // connected by exactly one path. In other words, any connected graph without 
    // simple cycles is a tree.”
    for (int[] edge : edges){
      map.get(edge[0]).add(edge[1]);
      map.get(edge[1]).add(edge[0]);
    }

    boolean[] visited = new boolean[n];
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(0);

    while (!queue.isEmpty()) {
      Integer cur = queue.poll();

      if (visited[cur]) {
        return false;
      }
      visited[cur] = true;

      for (Integer nei : map.get(cur)) {  // expand
        if (!visited[nei]) {
          queue.offer(nei);              // generate
        }
      }
    }

    for (boolean marked : visited) {
      if (!marked) {
        return false;
      }
    }
    
    // if found no circle, we need a default return value for the program 
    return true;
  }
  
  // version 2: hash set
  // Time: O(V + E) for building graph takes O(E), and BFS takes O(V + E), and set contains and add take constant O(1) 
  //                (but might hash collision become O(V)), worst case BFS part will become O(V + VE) = O(VE) !!)
  // Space: O(V) for HashSet
  public boolean validTree(int n, int[][] edges) {
    // corner case !!
    if (edges.length == 0) {
      return n == 1;
    }
    // initializa (adjacency list) graph map
    Map<Integer, List<Integer>> map = new HashMap<>(n);

    // intialize vertices
    for (int i = 0; i < n; i++) {
      map.put(i, new ArrayList<Integer>());
    }

    // add edges : “a tree is an undirected graph in which any two vertices are 
    // connected by exactly one path. In other words, any connected graph without 
    // simple cycles is a tree.”
    for (int[] edge : edges){
      map.get(edge[0]).add(edge[1]);
      map.get(edge[1]).add(edge[0]);
    }

    Set<Integer> visited = new HashSet<>();
    Queue<Integer> queue = new LinkedList<>();
    queue.offer(edges[0][0]);
    // marked as visited when expanding 
    // corner case: seed (or egde), or root is invalid!!!

    while (!queue.isEmpty()) {
      int cur = queue.poll();

      if (!visited.add(cur)) {
        return false;
      }

      for (int nei : map.get(cur)) {    // expand
        if (!visited.contains(nei)) { 
          queue.offer(nei);             // generated
        }
      }
    }
    // if found no circle, we need a default return value for the program 
    return visited.size() == n;
  }
}
