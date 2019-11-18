public class MaxWaterTrappedI {
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
}
