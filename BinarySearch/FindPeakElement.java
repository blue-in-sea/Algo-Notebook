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
}
