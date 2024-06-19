package DP;

/**
 * 42. Trapping Rain Water
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it can trap after raining.
 *
 * Input: height = [4,2,0,3,2,5]
 * Output: 9
 */
public class MaxWaterTrapped {
  // Optimization on Space: Two Pointers (leftMax + rightMax) + waterSum
  // Time: O(N)
  // Space: O(1) Only constant space for l, r, lmax, rmax
  public int maxTrapped(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }
    int waterSum = 0;
    int l = 0;
    int r = array.length - 1;
    int lmax = array[l];
    int rmax = array[r];
    while (l < r) {
      if (array[l] <= array[r]) {
        // 木桶原理：water trapped 取决于短板：左边界
        waterSum += Math.max(0, lmax - array[l]);
        lmax = Math.max(lmax, array[l]);
        l++;
      } else {
        // 木桶原理：water trapped 取决于短板：右边界
        waterSum += Math.max(0, rmax - array[r]);
        rmax = Math.max(rmax, array[r]);
        r--;
      }
    }
    return waterSum; 
  }
  /**
   * index            0 1 2 3 4 5
   * Input: height = [4,2,0,3,2,5], Output: 9
   * l: 0, r: 5, arr[l]: 0, arr[r]: 5, lmax: 4, rmax: 5, sum: 0, maxWaterSum: 0
   * l: 1, r: 5, arr[l]: 1, arr[r]: 5, lmax: 4, rmax: 5, sum: 2, maxWaterSum: 2
   * l: 2, r: 5, arr[l]: 2, arr[r]: 5, lmax: 4, rmax: 5, sum: 4, maxWaterSum: 6
   * l: 3, r: 5, arr[l]: 3, arr[r]: 5, lmax: 4, rmax: 5, sum: 1, maxWaterSum: 7
   * l: 4, r: 5, arr[l]: 4, arr[r]: 5, lmax: 4, rmax: 5, sum: 2, maxWaterSum: 9
   */
  
  // Naive Idea: leftMax Arr + rightMax Arr + water Arr (DP on wasterSum)
  // Time: O(N)
  // Space: O(3N)
  public int maxTrapped(int[] array) {
    // assume array is not null, and
    // has no negative values
    if (array.length == 0) {
      return 0;
    }

    int[] leftMax = new int[array.length];
    int lmax = 0;
    for (int i = 0; i < array.length; i++) {
      lmax = Math.max(lmax, array[i]);
      leftMax[i] = lmax;
    }

    int[] rightMax = new int[array.length];
    int rmax = 0;
    for (int i = array.length - 1; i >= 0; i--) {
      rmax = Math.max(rmax, array[i]);
      rightMax[i] = rmax;
    }

    int[] water = new int[array.length];
    int waterSum = 0;
    for (int i = 0; i < array.length; i++) {
      water[i] = Math.min(leftMax[i], rightMax[i]) - array[i];
      waterSum += water[i];
    }

    return waterSum;
  }
}
