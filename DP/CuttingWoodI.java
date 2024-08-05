/**
 * 137. Cutting Wood I
 * There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined
 * in an int array A. The positions are guaranteed to be in ascending order in the range of [1, L - 1]. The cost of 
 * each cut is the length of the stick segment being cut. Determine the minimum total cost to cut the stick into the 
 * defined pieces.
 *
 * L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)
 */
public class CuttingWoodI {
    // minCost[i][j] = min cost of cutting part[j, i]
    // base case: minCost[i][0] = 0
    //            minCost[j][i] = 0 if j + 1 = i
    // induction:
    // for k -> (j, i)
    //                 不切       切
    //    minCost[j][i] = min( dp[j][i], dp[j][k] + dp[k][i])

    // 每次记得加上左边的 cost (0, j)

    // Time: O(n^3), Space: O(n^2)
    public int minCost(int[] cuts, int length) {
        // Assume cuts is not null, all cuts are valid
        // Assume length >= 0

        // 1. init helper to store the cost of left part 
        int[] helper = new int[cuts.length + 2];
        helper[0] = 0;          // leftmost
        helper[helper.length - 1] = length; // rightmost

        // 2. fill helper with initial costs 
        for(int i = 0; i < cuts.length; i++) {
            helper[i + 1] = cuts[i];
        }

        // 3. DP 
        // minCost[i][j] = min cost of cutting part[j, i]
        int[][] minCost = new int[helper.length][helper.length];
        for (int i = 1; i < helper.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (j + 1 == i) {
                    minCost[j][i] = 0;
                } else {
                    minCost[j][i] = Integer.MAX_VALUE;

                    for (int k = j + 1; k <= i - 1; k++) {
                        minCost[j][i] = Math.min(minCost[j][i], minCost[j][k] + minCost[k][i]);
                    }

                    minCost[j][i] += helper[i] - helper[j];
                }
            }
        }

        return minCost[0][helper.length - 1]; // min cost of cutting part[0, n]
    }
 
  
  public int minCost(int[] cuts, int length) {
    // Assume cuts is not null, all cuts are valid
    // Assume length >= 0
    
    // 1. init helper to store the cost of left part 
    int[] helper = new int[cuts.length + 2];
    helper[0] = 0;          // leftmost
    helper[helper.length - 1] = length; // rightmost

    // 2. fill helper with initial costs 
    for(int i = 0; i < cuts.length; i++) {
      helper[i + 1] = cuts[i];
    }

    // 3. DP 
    // minCost[i][j] = min cost of cutting part[j, i]
    int[][] minCost = new int[helper.length][helper.length];
    for (int i = 1; i < helper.length; i++) {
      for (int j = i - 1; j >= 0; j--) {
        if (j + 1 == i) {
          minCost[j][i] = 0;
        } else {
          minCost[j][i] = Integer.MAX_VALUE;

          for (int k = j + 1; k <= i - 1; k++) {
            minCost[j][i] = Math.min(minCost[j][i], minCost[j][k] + minCost[k][i]);
          }

          minCost[j][i] += helper[i] - helper[j];
        }
      }
    }

    return minCost[0][helper.length - 1]; // min cost of cutting part[0, n]
  }
}
