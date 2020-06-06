class MinimumSizeSubarraySum {
    // Sliding Window: 同向双指针
    // Time: O(n), Space: O(1)
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int l = 0, r = 0, sum = 0, min = Integer.MAX_VALUE;
        while (r < nums.length) {
            sum += nums[r++];

            while (sum >= s) {
                min = Math.min(min, r - l);
                sum -= nums[l++];
            }
        }

        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
