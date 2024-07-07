/**
 * 162. Find Peak Element
 * A peak element is an element that is strictly greater than its neighbors.
 * 
 * Input: nums = [1,2,3,1]
 * Output: 2
 * Explanation: 3 is a peak element and your function should return the index number 2.
 * Example 2:
 *
 * Input: nums = [1,2,1,3,5,6,4]
 * Output: 5
 * Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.
 */
public class FindPeakElement {
    // Binary Search 
    // Time: O(logn)
    // Space: O(1)
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        
        while (l < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[mid + 1]) l = mid + 1;          //如果中间的数比后一位数小的话，peek点肯定在mid右边包括mid
            else r = mid;
        }
        
        return l;
        // while 结束时 left 和 right 停在同一个位置
    }

    // ****************************************************
    
    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;
        while (l + 1 < r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[mid + 1]) l = mid + 1; //如果中间的数比后一位数小的话，peek点肯定在mid右边包括mid
            else r = mid;
        }
        return nums[l] > nums[r] ? l : r;
        // while 结束时 left 和 right 相邻, 谁大 return 谁
    }

    // ****************************************************

    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return 0;
        }

        int l = 1, r = nums.length - 2; // 这里注意初始值
        while (l <= r) {
            int mid = (l + r) / 2;
            if (nums[mid] < nums[mid + 1]) {
                l = mid + 1; 
            } else {
                if (nums[mid] > nums[mid - 1]) {
                    return mid;  // found
                } else {
                    r = mid - 1;
                }

            }
        }
        return nums[l] > nums[r] ? l : r;
        // while 结束时 left 和 right 相错一步, 谁大 return 谁
    }
}
