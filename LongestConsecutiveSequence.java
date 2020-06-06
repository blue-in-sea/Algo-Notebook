public class LongestConsecutiveSequence {
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
