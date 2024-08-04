package DP;

/**
 * 300. Longest Increasing Subsequence
 * Given an integer array nums, return the length of the longest strictly increasing 
 * subsequence
 * 
 * Input: nums = [10,9,2,5,3,7,101,18]
 * Output: 4 where    [2,  3,7,101] - The longest increasing subsequence
 *
 * Input: nums = [0,1,0,3,2,3]
 * Output: 4     [0,1,    2,3]      - The longest increasing subsequence
 *
 * Input: nums = [7,7,7,7,7,7,7]
 * Output: 1     [7]                - The longest increasing subsequence
 */
public class LongestAscendingSubsequence {
  // Method 1: DP Linear Scan 对于每一个ending，回头看
  // dp[i] represent the longest increasing subsequence ending at arr[i]
  // Time: O(n^2) = 1 + 2 + 3 + 4 + ... + n(n+1)/2, Space: O(n)
  public int longest (int[] array) {
    // Assume array is not null
    if (array.length == 0) {
      return 0;
    }
    // M[i] = the len of the longest subsequence
    // ending at the index i (including i)
    int[] M = new int[array.length];
    int res = 1;  // global max

    for (int i = 0; i < array.length; i++) {
      // initialize M[i] as 1, since the shortest one has len 1
      // just as array[i] itself
      M[i] = 1;
      for (int j = 0; j < i; j++) {
        // only when array[j] < array[i], it is possible to use the
        // longest ascending subsequence ending index j and array[i]
        // to form a new ascending subsequence 
        if (array[j] < array[i]) {
          M[i] = Math.max(M[j] + 1, M[i]);
        }
        // possibly update the global longest one
        res = Math.max(M[i], res);
      }
    }

    return res;
  }

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
