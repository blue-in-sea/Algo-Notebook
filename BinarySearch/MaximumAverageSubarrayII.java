public class MaximumAverageSubarrayII {
    /**
     * @param nums: an array with positive and negative numbers
     * @param k: an integer
     * @return: the maximum average
     */
    public double maxAverage(int[] nums, int k) {
        // 二分平均值
        double l = nums[0];
        double r = nums[0];
        for (int i = 0; i < nums.length; i++) {
            l = Math.min(nums[i], l);
            r = Math.max(nums[i], r);
        }
        
        while (l + 1e-5 < r) {
            double mid = l + (r - l) / 2;
            if (canFind(nums, mid, k)) {
                l = mid;
            } else {
                r = mid;
            }
        }
        
        return l;
    }
    
    private boolean canFind(int[] nums, double avg, int k) {
        double rightSum = 0;
        double leftSum = 0;
        double minLeftSum = 0;
        
        for (int i = 0; i < k; i++) {
            rightSum += (nums[i] - avg);
        }
        
        for (int i = k; i <= nums.length; i++) {
            if (rightSum - minLeftSum >= 0) {
                return true;
            }
            if (i < nums.length) {
                rightSum += (nums[i] - avg);
                leftSum += (nums[i - k] - avg);
                minLeftSum = Math.min(minLeftSum, leftSum);
            }
        }
        
        return false;
    }
}
