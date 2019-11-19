/**
 * 1.2.2 同向双指针1: Minimum size subarray sum (L460)
 * 同向双指针 l & r
 * 每次删除左指针左边的数字 只要当前sum小于s，右指针继续向右移动 时间复杂度O(N)
 */
public class MinimumSizeSubarraySum {
    /**
     * @param nums: an array of integers
     * @param s: An integer
     * @return: an integer representing the minimum size of subarray
     *
     * Time: O(N) for r moves n steps, l moves n steps, total add up to 2N
     * Space: O(1) 
     */
  public int minimumSize(int[] nums, int s) {
        int sum = 0;
        int res = Integer.MAX_VALUE;

        for (int l = 0, r = 0; r < nums.length; r++) {
            sum += nums[r];

            while (sum >= s) {
                res = Math.min(res, r - l + 1); // min length
                sum -= nums[l++];
            }
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }
}
