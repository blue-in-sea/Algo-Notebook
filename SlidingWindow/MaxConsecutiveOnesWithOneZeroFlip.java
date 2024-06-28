/**
 * 487. Max Consecutive Ones II
 * Given a binary array nums, return the maximum number of consecutive 1's in the array if you can flip at most one 0.
 *
 * Input: nums = [1,0,1,1,0]
 * Output: 4
 * Explanation: 
 * - If we flip the first zero, nums becomes [1,1,1,1,0] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1] and we have 3 consecutive ones.
 * The max number of consecutive ones is 4.
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 4
 * Explanation: 
 * - If we flip the first zero, nums becomes [1,1,1,1,0,1] and we have 4 consecutive ones.
 * - If we flip the second zero, nums becomes [1,0,1,1,1,1] and we have 4 consecutive ones.
 * The max number of consecutive ones is 4.
 */
class MaxConsecutiveOnesWithOneZeroFlip {
    /**
     * Sliding Window: 同向双指针 
     * Finding the max number of consecutive 1's in the array with at most 1 zero flip
     *
     * Time: O(n)
     * Space: O(1)
     */     
    public int findMaxConsecutiveOnes(int[] nums) {
        int max = 0;
        int l = 0;
        int r = 0;
        int cntZero = 0;

        // While our window is in bounds
        while (r < nums.length) {

            // Increase numZeroes if the rightmost element is 0
            if (nums[r] == 0) {
                cntZero++;
            }

            //If our window is invalid, contract our window
            while (cntZero == 2) {
                if (nums[l] == 0) {
                    cntZero--;
                }
                l++;
            }

            // Update our longest sequence answer
            max = Math.max(max, r - l + 1);

            // Expand our window
            r++;
        }
        return max;
    }
}
