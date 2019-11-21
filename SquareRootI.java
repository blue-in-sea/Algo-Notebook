public class SquareRootI {
  public int sqrt(int x) {
    long res = 1;
    long left = 0, right = x;  // in order to pass <0>, left must be initialed at the lowest value, or res declared as 0, either works

    while (left <= right) {
      long mid = left + (right - left) / 2;
      if (mid * mid <= x) {
        res = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }

    return (int) res;
  }
  
  public int sqrt(int x) {
    // assume n is guaranteed to be >= 0
    long left = 1;
    long right = x;
    while (left + 1 < right) {
      long mid = left + (right - left) / 2;
      if (mid * mid <= x) {
        left = mid;
      } else {
        right = mid;
      }
    }

    if (right * right <= x) {
      return (int) right;  // can pass <0> case, since right = 0
    }   
    return (int) left;
  }
}
