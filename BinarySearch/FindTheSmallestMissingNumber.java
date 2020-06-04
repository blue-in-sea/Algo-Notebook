public class FindTheSmallestMissingNumber {
  public int findFirstMissingElement(int[] array) {
    if (array == null || array.length == 0) {
      return 0;
    }

    int left = 0;
    int right = array.length - 1;
        
    while (left + 1 < right) {
      int mida = left + (right - left) / 2;

      if (array[mid] == mid) {
        left = mid;
      } else {
        right = mid;
      }
    }
    
    if (array[left] != left) {
      return left;
    } else {
      if (right == array.length - 1 && array[right] == right) {
        return array.length;
      } 
      return right;
    }
  }
}
