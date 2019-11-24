/**
 * Given an integer array of size N - 1, containing all the numbers from 1 to N except one, find the missing number. 
 * Assumption: input array is not null
 */
public class MissingNumberI {
  // Method 1: use hash set
  // Time: O(n), Space O(n)
  public int missingI(int[] array) {
    int n = array.length + 1;
    Set<Integer> set = new HashSet<Integer>();
    for (int num : array) {
      set.add(num);
    }
    for (int i = 1; i < n; i++) {
      if (!set.contains(i)) {
        return i;
      }
    }
    return n;
  }
  
  // Method 2: use sum
  // Time: O(n), Space O(1)
  public int missingII(int[] array) {
    int n = array.length + 1;
    long targetSum = (n + 0L) * (n + 1) / 2;
    long actualSum = 0L;
    for (int num : array) {
      actualSum += num;
    }
    return (int) (targetSum - actualSum);
  }
  
  // Method 3: bit operations
  // Time: O(n), Space O(1)
  public int missingIII(int[] array) {
    int n = array.length + 1;
    int xor = 0;
    // xor 1 to n 
    for (int i = 1; i <= n; i++) {
      xor ^= i;
    }
    // after this operation, all number from 1 to n
    // are pair xor'ed except for the missing number
    // since x ^ x = 0, the remaining number is result
    for (int num : array) {
      xor ^= num;
    }
    return xor;
  }
  
  // Method 4: swap to the original position based on index
  // Time: O(n), Space O(1)
  public int missingIV(int[] array) {
    int n = array.length + 1;
    // while array[i] is not i + 1, swap array[i] to its 
    // corresponding position for the number x,
    // the corresponding position is x - 1
    for (int i = 0; i < array.length; i++) {
      while (array[i] != i + 1 && array[i] != n) {
        swap(array, i, array[i] - 1);
      }
    }
    // if the missing number is in range of [1, n - 1]
    // then we can find it by checking the index i
    // where array[i] != i + 1
    for (int i = 0; i < array.length; i++) {
      if (array[i] != i + 1) {
        return i + 1;
      }
    }
    // if all the numbers of [1, n - 1] in position
    // the missing number is n
    return array.length + 1;
  }
  
  private void swap(int[] array, int left, int right) {
    int tmp = array[left];
    array[left] = array[right];
    array[right] = tmp;
  }
}
