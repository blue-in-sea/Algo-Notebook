package DP;

public class LongestConsecutiveSequence {
  // Method 1: use HashMap
  public int longestConsecutive(int[] array) {
    int res = 0;
    Map<Integer, Integer> map = new HashMap<>();
    for (int num : array) {
      if (map.containsKey(num)) continue;

      int left = map.getOrDefault(num - 1, 0);
      int right = map.getOrDefault(num + 1, 0);
      int sum = left + right + 1;
      res = Math.max(res, sum);
      // update the boundary(s) of the sequence
      // if none, will do thing 
      if (left > 0) map.put(num - left, sum);
      if (right > 0) map.put(num + right, sum);

      map.put(num, sum);
    }
    return res;
  }
  
  // Method 2: using hashset
  public int longestConsecutive(int[] array) {
    Set<Integer> set = new HashSet<>();
    for (int num : array) {
      set.add(num);
    }

    int res = 0;
    for (int num : set) {
      if (set.contains(num - 1)) continue;

      // 找到一个 subsequence 的起始点
      int currNum = num;
      int currLen = 1;

      while (set.contains(currNum + 1)) {
        currNum += 1;
        currLen += 1;
      }

      res = Math.max(res, currLen);
    }
    return res;
  }
}
