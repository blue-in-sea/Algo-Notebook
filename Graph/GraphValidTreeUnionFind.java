package Graph;

public class GraphValidTreeUnionFind {
  /**
   * Method 3: Union Find
   */
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
