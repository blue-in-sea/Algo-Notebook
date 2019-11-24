/**
 * Key: given node & edges, build map that stores a direct grap
 *      then BFS the graph to check whether it contains circle or violate the requirement 
 * 
 * Algo: 1. convert nodes and edges into map <node, adjList>
 *       2. second map to calculate the indegree of each nodes
 *       3. BFS, put all indegree == 0 nodes into queue, also put them into bfs-result list
 *       4. return: check whether (# of nodes in the list == courses to be taken) if yes, return true
 *(Step2-4 本质: 因为拓扑排序节点入度为0时才会存入result, 所以如果存在环，那么有向图的拓扑排序节点个数就会小于图的节点个数)
 */

public class CourseSchedule {
  /**
   * @param numCourses: a total of n courses
   * @param prerequisites: a list of prerequisite pairs
   * @return: true if can finish all courses or false
   */
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    if (numCourses == 0 || prerequisites.length == 0) {
      return true;
    }

    // build courseMap
    Map<Integer, List<Integer>> courseMap = buildMap(numCourses, prerequisites); 

    // calculate each course's indegree / 统计各个节点的入度, 查节点在不在 key 里 (在, indegree++)
    // <K, V> : <node : its indegree>
    Map <Integer, Integer> indegreeMap = new HashMap<>();
    for (int i = 0; i < numCourses; i++) {
      for (Integer neighbor : courseMap.get(i)) {
        if (indegreeMap.containsKey(neighbor)) {
          indegreeMap.put(neighbor, indegreeMap.get(neighbor) + 1);
        } else {
          indegreeMap.put(neighbor, 1);
        }
      }
    }

    // 把所有入度为0的点，放到BFS专用的队列中
    Queue<Integer> queue = new LinkedList<>();
    // 初始化拓扑序列为空
    ArrayList<Integer> result = new ArrayList<>();

    for (int i = 0; i < numCourses; i++) {
      if (!indegreeMap.containsKey(i)) {
        queue.offer(i);
        result.add(i);
      }
    }

    // 每次从队列中(queue)拿出一个点放到拓扑序列(BFS list)里，并将该点指向的所有点的入度减1
    while (!queue.isEmpty()) {
      Integer course = queue.poll();  // 如果修掉这门课
      for (Integer neighbor : courseMap.get(course)) {
        indegreeMap.put(neighbor, indegreeMap.get(neighbor) - 1); // 有多少高阶课的 prerequisites 减一了
        // 减去1之后入度变为0的点，也放入队列
        if (indegreeMap.get(neighbor) == 0) {
          queue.offer(neighbor);
          result.add(neighbor);
        }
      }
    }

    // 判断拓扑排序数(bfs list)与图的节点数(num of courses required)是否相同
    return result.size() == numCourses; // yes, return true
  }

  //创建 courseMap 有向图, 方向为先修课程指向当前课程 pre -> cur
  private Map<Integer, List<Integer>> buildMap(int numCourses, int[][] prerequisites) {
    Map<Integer, List<Integer>> map = new HashMap<>();
    for (int i = 0; i < numCourses; i++) {
      map.put(i, new ArrayList<>());
    }
    // <K, V> : <Prerequisites, for the courses list>
    for (int i = 0; i < prerequisites.length; i++) {
      int cur = prerequisites[i][0];
      int pre = prerequisites[i][1];
      map.get(pre).add(cur);
    }
    return map;
  }
  
  // Approach 2: 裸拓扑排序
  public boolean canFinish(int numCourses, int[][] prerequisites) {
    List[] edges = new ArrayList[numCourses];
    int[] degree = new int[numCourses];

    for (int i = 0;i < numCourses; i++) {
      edges[i] = new ArrayList<Integer>();
    }

    for (int i = 0; i < prerequisites.length; i++) {
      degree[prerequisites[i][0]]++;  // inDegree
      edges[prerequisites[i][1]].add(prerequisites[i][0]);  // build graph
    }

    Queue<Integer> queue = new LinkedList<>();
    for (int i = 0; i < degree.length; i++) {
      if (degree[i] == 0) {
        queue.add(i);  // put no prerequisite class into our queue 
      }
    }

    int cnt = 0;
    while (!queue.isEmpty()) {
      int course = (int)queue.poll();
      cnt++;  // cnt will increase one unit whenever a course's indegree reset to 0
      int n = edges[course].size();
      for (int i = 0; i < n; i++) {
        int pointer = (int)edges[course].get(i);
        degree[pointer]--;
        if (degree[pointer] == 0) {
          queue.offer(pointer);
        }
      }
    }
    
    return cnt == numCourses;
  }
}
