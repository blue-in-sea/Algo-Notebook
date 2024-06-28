/**
 * 485. Max Consecutive Ones
 * Given a binary array nums, return the maximum number of consecutive 1's in the array.
 * 
 * Input: nums = [1,1,0,1,1,1]
 * Output: 3
 * Explanation: The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
 *
 * Input: nums = [1,0,1,1,0,1]
 * Output: 2
 */
class MaxConsecutiveOnes {
  public int findMaxConsecutiveOnes(int[] nums) {
    int count = 0;
    int maxCount = 0;
    for(int i = 0; i < nums.length; i++) {
      if(nums[i] == 1) {
        // Increment the count of 1's by one.
        count += 1;
      } else {
        // Find the maximum till now.
        maxCount = Math.max(maxCount, count);
        // Reset count of 1 (reset the window)
        count = 0;
      }
    }
    return Math.max(maxCount, count);
  }
}
