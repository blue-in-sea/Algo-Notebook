public class CuttingWoodI {
 
  
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
