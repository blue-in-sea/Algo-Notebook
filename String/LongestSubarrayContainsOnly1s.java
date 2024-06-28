public class LongestSubarrayContainsOnly1s {
  // Sliding Window 同相双指针（for-loop）
  // Time: O(N), Space: O(1)
  public int longestConsecutiveOnes(int[] nums, int k) {
    int cntZero = 0;
    int res = 0;

    for (int l = 0, r = 0; l < nums.length; l++) {
      while (r < nums.length) {
        if (nums[r] == 1) {
          res = Math.max(res, r - l + 1);
          r++;
        } else if (cntZero < k) {
          cntZero++;
          res = Math.max(res, r - l + 1);
          r++;
        } else {
          break;
        }
      }   

      if (nums[l] == 0) {
        cntZero--;
      }
    }

    return res;
  }
  
  // While-loop 
  public int longestConsecutiveOnes(int[] nums, int k) {
    int cnt = 0;
    int res = 0;

    int l = 0;  // left-bound of window 
    int r = 0;  // right-bound of window 
    while (r < nums.length) {
      if (nums[r] == 1) {
        res = Math.max(res, ++r - l);
      } else if (cnt < k) {
        cnt++;
        res = Math.max(res, ++r - l);
      } else if (nums[l++] == 0) {
        cnt--;
      }
    }

    return res;
  }
}
