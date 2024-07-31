/**
 * 119. Largest And Smallest
 * Use the least number of comparisons to get the largest and smallest number in the given integer array. Return the 
 * largest number and the smallest number.
 *
 * Assume The given array is not null and has length of at least 1
 *
 * {2, 1, 5, 4, 3}, the largest number is 5 and smallest number is 1. return [5, 1].
 */
public class LargestAndSmallest {
  public int[] largestAndSmallest(int[] array) {
    // Assume array is not null and has length of at least 1
    int n = array.length;
    // inidices (x, n - 1 - x) will be paired up, the larger numbers of 
    // each pair will be put on the left side, so after the comparisons;
    // The left half of the array are the larger values for each pairs,
    // the right half of the array are the smaller values for each pairs.
    for (int i = 0; i < n / 2; i++) {
      if (array[i] < array[n - 1 - i]) {
        swap(array, i, n - 1 - i);
      }
    }
    // The largest value should be the largest of the left half
    // The smallest value should be the smallest of the right half
    return new int[] {largest(array, 0, (n - 1) / 2), smallest(array, n / 2, n - 1)};
  }

  private int largest(int[] array, int left, int right) {
    int largest = array[left];
    for (int i = left + 1; i <= right; i++) {
      largest = Math.max(array[i], largest);
    }
    return largest;
  }

  private int smallest(int[] array, int left, int right) {
    int smallest = array[left];
    for (int i = left + 1; i <= right; i++) {
      smallest = Math.min(array[i], smallest);
    }
    return smallest;
  }

  private void swap(int[] array, int a, int b) {
    int tmp = array[a];
    array[a] = array[b];
    array[b] = tmp;
  }
}


