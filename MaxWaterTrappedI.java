public class MaxWaterTrappedI {
  // Optimization on Space: Two Pointers (leftMax + rightMax)
  // Time: O(N)
  // Space: O(1)
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
        waterSum += Math.max(0, lmax - array[l]);
        lmax = Math.max(lmax, array[l]);
        l++;
      } else {
        waterSum += Math.max(0, rmax - array[r]);
        rmax = Math.max(rmax, array[r]);
        r--;
      }
    }
    return waterSum; 
  }
  
  // Naive Idea: leftMax Arr + rightMax Arr + water Arr 
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
