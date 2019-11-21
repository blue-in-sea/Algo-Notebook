public class FindPeakElement {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int left = 1, right = A.length - 1; // 1.答案在之间，2.不会出界 
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
      
            //如果中间的数比后一位数大的话，peek点肯定在mid左边或是mid。
            if (A[mid] > A[mid + 1]) {
                right = mid;
            } else {
                left = mid;
            }
        }
        // while 结束时 left 和 right 相邻
        if (A[left] < A[right]) {
            return right;
        } else {
            return left;
        }
        // 谁大 return 谁
    }
}
