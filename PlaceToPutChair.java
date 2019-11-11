public class PlaceToPutChairI {
  private static final char EQUIP = 'E';
  private static final char OB = 'O';

  public List<Integer> putChair(char[][] gym) {
    int m = gym.length;
    int n = gym[0].length;

    // record the sum of shortest path cost 
    // from each cell to all the 'E' cells
    int[][] cost = new int[m][n];

    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        
        // 只计算以E cell为起点，到其他所有cells的距离之和，
        // 然后把这些距离，累加到empty cell（比如坐标为 i, j）上，即 cost[i][j] 上
        // 那么到了最后，cost[i][j] 就是从所有E cells到当前的 empy cell [i, j] 的距离之和 
        if (gym[i][j] == EQUIP) {
          // use BFS to calculate the shortest path cost from each of the equipments
          // to all the other reachable cells,
          // and add the cost to each corresponding cell.
          // The addCost method will return null if there is any other 'E' cell
          // that is not reachable from the current 'E' cell,
          // if so, there won't be any valid place to put a chair
          if (!addCost(cost, gym, i, j)) {
            return null;
          }
        }
      }
    }
    // find the cell with the smallest sum of shorted path costs
    // to all the 'E' cells
    List<Integer> result = null;
    for (int i = 0; i < m; i++) {
      for (int j = 0; j < n; j++) {
        
        if (gym[i][j] != EQUIP && gym[i][j] != OB) { // this means an empty 'C' cell
          if (result == null) {
            result = Arrays.asList(i, j);
          } else if (cost[i][j] < cost[result.get(0)][result.get(1)]) {
            result.set(0, i);
            result.set(1, j);
          }
        }
      }
    }
    return result == null ? Arrays.asList(-1, -1) : result;  // if not found, return [-1, -1] !!
  }
  
  private boolean addCost(int[][] cost, char[][] gym, int i, int j) {
    // use a boolean matrix to make sure each cell will be visited no more than once
    boolean[][] visited = new boolean[gym.length][gym[0].length];
    // Breadth-first search, record the current path cost
    int pathCost = 1;
    
    Queue<Pair> queue = new LinkedList<>();
    visited[i][j] = true;
    queue.offer(new Pair(i, j));
    
    while (!queue.isEmpty()) {
      int size = queue.size();
      
      for (int l = 0; l < size; l++) {
        Pair cur = queue.poll();
        List<Pair> neis = getNeis(cur, gym);
        
        for (Pair nei : neis) {
          if (!visited[nei.i][nei.j]) {
            visited[nei.i][nei.j] = true;
            cost[nei.i][nei.j] += pathCost; // key update !!!
            queue.offer(nei);
          }
        }
      }
      // add the path cost by 1 for each level
      pathCost++; // inside the while
    }

    // if there exist another 'E' cell that is not reachable from the starting
    // 'E' cell, we return false
    for (int l = 0; l < gym.length; l++) {
      for (int m = 0; m < gym[0].length; m++) {
        if (!visited[l][m] && gym[l][m] == EQUIP) {
          return false;
        }
      }
    }
    return true;
  }

  private List<Pair> getNeis(Pair cur, char[][] gym) {
    int x = cur.i;
    int y = cur.j;
    int m = gym.length;
    int n = gym[0].length;

    List<Pair> neis = new ArrayList<>();

    if (x + 1 < m && gym[x + 1][y] != OB) {
      neis.add(new Pair(x + 1, y));
    }
    if (y + 1 < n && gym[x][y + 1] != OB) {
      neis.add(new Pair(x, y + 1));
    }
    if (x - 1 >= 0 && gym[x - 1][y] != OB) {
      neis.add(new Pair(x - 1, y));
    }
    if (y - 1 >= 0 && gym[x][y - 1] != OB) {
      neis.add(new Pair(x, y - 1));
    }
    return neis;
  }

  static class Pair {
    int i;
    int j;

    Pair(int i, int j) {
      this.i = i;
      this.j = j;
    }
  }
}
