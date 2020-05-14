public class Solution {
  // Methdod 3: DP pre_sum + HashMap <K = pre_sum, V = Freq>
  // DP[i] represents the pre_sum including a[i]
  // Time: O(n), Space: O(n)
  public int numOfSubarraySumToK(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int sum = 0, cnt = 0;
    Map<Integer, Integer> map = new HashMap<>();
    map.put(0, 1);

    for (int i = 0; i < nums.length; i++) {
      sum += nums[i];

      if (map.containsKey(sum - k)) {
        cnt += map.get(sum - k);
      }
      map.put(sum , map.getOrDefault(sum, 0) + 1);
    }

    return cnt;
  }
}
