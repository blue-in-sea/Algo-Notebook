/**
 * Given a rope with positive integer-length n, how to cut the rope into m integer-length parts with 
 * length p[0], p[1], ...,p[m-1], in order to get the maximal product of p[0]*p[1]* ... *p[m-1]? 
 * m is determined by you and must be greater than 0 (at least one cut must be made). 
 * Return the max product you can have.
 *
 * Assumptions 
 * n >= 2
 *
 * Examples
 * n = 12, the max product is 3 * 3 * 3 * 3 = 81
 * (cut the rope into 4 pieces with length of each is 3).
 */

public class MaxProductOfCuttingRope {
  /** 
   * Method 1: DFS (Recursion) + Backtracking
   *
   * (只考虑右边最后一刀) if n = 5
   *                                      maxP(5)
   *          /                    |                    |                    \
   * max{maxP(4), 4} * 1   max{maxP(3), 2} * 2   max{maxP(2), 2} * 3   max{maxP(1), 1} * 4
   *     / | \                  /  \                    |                   0
   *    ......                 ......                 ......
   * 
   * Time: O(n!) for n meters rope (Brutal Force)
   * Space: O(n) for stack calls
   */
  
  /** 
   * Method 3: Dynamic Programming（左大段 + 右小段) Interview !!
   * M[i] represents the max product of (p[0], p[1], ...,p[m-1])
   * 右小段: 右边最后一刀绳子本身的长度：如上图 !!
   * Time: O(n^2) 
   * Space: O(n)
   */
  public int maxProduct(int length) {
    int n = length;
    int[] M = new int[n + 1];
    M[0] = 0; // place holder
    M[1] = 0;
    for (int i = 2; i <= n; i ++) {
      // 不对称，所以枚举到i，不能剪枝
      for (int j = 1; j < i; j++) {
                       //不切  //切
        M[i] = Math.max(M[i], Math.max(M[j], j) * (i - j)); // trick to calculate 
                                   // 左大段      // 右小段
      }
    }
    return M[n]; // M: [0, 0, 1, 2, 4, 6, 9, 12, 18, 27, 36, 54, 81]
  } 
  
  public int maxProduct(int length) {
    int n = length;
    
    // base case: no cut made 
    if (n <= 1) {
      return 0;
    }
    
    int max = 0;
    for (int i = 1; i < n; i++) {
      int best = Math.max(maxProduct(n - i), n - i);
      max = Math.max(i * best, max);
    }
    
    return max;
  }

  /** 
   * Method 2: Dynamic Programming（左大段 + 右大段）
   * M[i] represents the max product of (p[0], p[1], ...,p[m-1])
   * Time: O(n^2) 
   * Space: O(n)
   */
  public int maxProduct(int length) {
    int n = length;
    int[] M = new int[n + 1];
    M[0] = 0; // place holder
    M[1] = 0;
    for (int i = 2; i <= n; i ++) {
      //因为对称，所以枚举到i / 2即可，剪枝
      for (int j = 1; j <= i / 2; j++) {
         //不切：继承之前的长度  //切
        M[i] = Math.max(M[i], Math.max(M[j], j) * Math.max(M[i - j], i - j)); // memo recursion 
                                   // 左大段               // 右大段
      }
    }
    return M[n]; // [0, 0, 1, 2, 4, 6, 9, 12, 18, 27, 36, 54, 81]
  } 
}
