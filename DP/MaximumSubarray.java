package DP;

class MaximumSubarray {
    // Time: O(N) wehere N is the len of the array
    // Space: O(1)
    public int maxSubArray(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int maxsum = nums[0];
        int cursum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            cursum = Math.max(nums[i] + cursum, nums[i]);
            maxsum = Math.max(cursum, maxsum);
        }
        return maxsum;
    }
}
