public class GraphValidTreeBFS {
  /**
   * BFS Approach 1: 只在neighbor没有被遍历过时才放入queue中
   * 这样，在遍历出队列时，如果遇到元素被二次访问就说明有cycle,
   * 最后遍历visited[]，确认每一个元素都被遍历到，才是valid tree（没有落单的节点）
   */
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

      for (Integer nei : map.get(cur)) {
        if (!visited[nei]) {
          queue.offer(nei);
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
}
