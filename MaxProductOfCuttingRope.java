public class MaxProductOfCuttingRope {
  public int maxProduct(int length) {
    int n = length;
    int[] M = new int[n + 1];

    // base case
    M[0] = 0;
    M[1] = 0; 

    // sub-solution: n meters
    for (int i = 2; i <= n; i ++) {
      int currMax = 0;
      // 储存前面的下刀，有几个地方能下最后一刀 （左大段 + 右小段）
      for (int j = 1; j <= i; j++) {
        currMax = Math.max(currMax, Math.max(j, M[j]) * (i - j));
      /*
        currMax = Math.max(currMax, 
                  Math.max(j, M[j]) * Math.max((i - j), M[i - j]));
      */
      } // end inner-for
      
      M[i] = currMax;
    } // end outter-for
    
    return M[n];
  } // end maxProduct()
}
