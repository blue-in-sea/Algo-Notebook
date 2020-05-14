public class SubarraySumEqualsK {
  // Methdod 1: Sliding Window
  // Find subarraySum(i, j) == target, increment the cnt
  // Time: O(n), Space: O(1)
  public int numOfSubarraySumToK(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int cnt = 0;
    for (int i = 0; i < nums.length; i++) {
      int sum = nums[i];
      if (sum == k) cnt++;

      for (int j = i + 1; j < nums.length; j++) {
        sum += nums[j];
        if (sum == k) cnt++;
      }
    }

    return cnt;
  }
  
  // Methdod 2: DP to store the pre_sum
  // DP[i] represents the pre_sum including a[i]
  public int numOfSubarraySumToK(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int cnt = 0;
    int[] dp = new int[nums.length];
    dp[0] = nums[0];

    for (int i = 1; i < nums.length; i++) {
      dp[i] = dp[i - 1] + nums[i];
    }

    for (int i = 0; i < nums.length; i++) {
      if (dp[i] == k) cnt++;

      for (int j = i + 1; j < nums.length; j++) {
        if (dp[j] - dp[i] == k) cnt++;
      }
    }

    return cnt;
  }
  
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
