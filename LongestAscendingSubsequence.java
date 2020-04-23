public class LongestAscendingSubsequence {
  // Method 2: DP + Binary Search
  // Time: O(nlogn), Space: O(n)
  public int longest2(int[] array) {
    // Assume array is not null
    if (array.length == 0) {
      return 0;
    }
    // tbl[i] = the smallest ending value of all the 
    // ascending subsequences with length i
    int[] tbl = new int[array.length + 1];
    int res = 1;  // at the beginning, only a[i] itself

    tbl[1] = array[0]; 
    for (int i = 0; i < array.length; i++) {
      // Key: find the best (or longest) ascending subsequence, which
      // can concatenate array[i] to form a new one.
      // Binary search of the "largest smaller value"
      int index = find(tbl, 1, res, array[i]);
      // two cases: 
      // 1. we can possible form a longer ascending subsequence than 
      // whatever we have before, if array[i] is larger than all 
      // values in tbl.
      // 2. we may update tbl[index + 1] because we find a better ascending
      // subsequence with len = index + 1 (the ending value is smaller or 
      // equal than)
      if (index == res) {
        tbl[++res] = array[i];
      } else {
        tbl[index + 1] = array[i];
      }
    }

    return res;
  }

  // find the largest smaller ele than target
  private int find(int[] tbl, int left, int right, int target) {
    while (left + 1 < right) {
      int mid = left + (right - left) / 2;
      
      if (tbl[mid] >= target) {
        right = mid - 1;
      } else {
        left = mid;
      }
    }

    for (int j = right; j >= left; j--) {
      if (tbl[j] < target) {
        return j;
      }
    }

    return 0;
  }
}
