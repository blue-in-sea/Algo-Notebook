public class CourseScheduleII {
  public int[] findOrder(int numCourses, int[][] prerequisites) {
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

    // convert ArrayList to int[] 
    if (result.size() == numCourses) {
      int[] arr = new int[numCourses];
      for (int i = 0; i < numCourses; i++) {
        arr[i] = result.get(i);
      }
      return arr;
    }
    
    return new int[]{};
  }

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
}
