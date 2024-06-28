/**
 * 1004. Max Consecutive Ones III
 * Given a binary array nums and an integer k, return the maximum number of consecutive 1's in the array if you can 
 * flip at most k 0's.
 *
 * Input: nums = [1,1,1,0,0,0,1,1,1,1,0], k = 2
 * Output: 6
 * Explanation: [1,1,1,0,0,1,1,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * 
 *
 * Input: nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], k = 3
 * Output: 10
 * Explanation: [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * Bolded numbers were flipped from 0 to 1. The longest subarray is underlined.
 * 
 * Similar to 
 * 1. Max Consecutive Ones 
 * 2. Max Consecutive Ones with most 1 zero flip
 */
class MaxConsecutiveOnesWithKZeroFlip {
    /**
     * Sliding Window: 同向双指针 
     * Finding the max number of consecutive 1's in the array with at most 1 zero flip
     *
     * Time: O(n)
     * Space: O(1)
     */  
    public int longestOnes(int[] nums, int k) {
        int max = 0;
        int cntZero = 0;
        
        int l = 0; 
        for (int r = 0; r < nums.length; r++) {
            // Increase numZeroes if the rightmost element is 0
            if (nums[r] == 0) {
                cntZero++;
            }

            //If our window is invalid, contract our window
            while (cntZero > k) {
                if (nums[l] == 0) {
                    cntZero--;
                }
                l++;
            }

            // Update our longest sequence answer
            max = Math.max(max, r - l + 1);
        }
        return max;
    }
}
