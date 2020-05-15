public class FirstOccurrence {
  public int firstOccur(int[] array, int target) {
    if (array == null || array.length == 0) {
      return -1;
    }
    int left = 0;
    int right = array.length - 1;

    while (left < right) {
      int mid = left + (right - left) / 2;
      if (array[mid] == target) {
        right = mid;  // since we ask for 1st occurrence
      } else if (array[mid] < target) {
        left = mid + 1;
      } else {
        right = mid;
      }
    }
    
    if (array[left] == target) {
      return left;
    } 
    return -1;
  }
}
