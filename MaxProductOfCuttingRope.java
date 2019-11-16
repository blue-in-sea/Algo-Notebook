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
   * Time: O(n!) for n meters rope
   * Space: O(n) for stack calls
   */
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
   * 
   * M[i] represents 储存前面的下刀，有几个地方能下最后一刀
   * M[i,j] = MAX{ M[i,k] * M[k,j] } 
   *
   * Time: O(n!) = ½[(n-2)(n-3)(n-4)...1]
   * Space: O(1) 
   */
  
  
  // Space: O(n) to O(1) by only store the currMax 
  public int maxProduct(int length) {
    int n = length;
    int[] M = new int[n + 1];

    // base case
    M[0] = 0;
    M[1] = 0; 

    // sub-solution: n meters
    for (int i = 2; i <= n; i ++) {
      int currMax = 0;
      for (int j = 1; j <= i; j++) {
        currMax = Math.max(currMax, Math.max(j, M[j]) * (i - j));
      } 
      
      M[i] = currMax;
    }
    
    return M[n];
  } 
  
  /** 
   * Method 3: Dynamic Programming（左大段 + 右小段）Optimal!!
   * 
   * M[i] represents [within the range from the beginning index to i-th index]
   * the max length of the ascending subarray. [must include the i-th element]
   *
   * M[i] = M[i - 1] + 1     a[i] > a[i - 1] 
   *      = 1                otherwise
   * Time: O(n) for linear scan, Space: O(1)
   */
  public int maxProduct(int length) {
  }
  
  
  
  
  
  
}
