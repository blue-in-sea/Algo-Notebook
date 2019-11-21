public class FindPeakElement {
    /**
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int left = 1, right = A.length - 2; // 1.答案在之间，2.不会出界 
        while (left + 1 < right) {
            int mid = left + (right - left) / 2;
      
            //如果中间的数比后一位数小的话，peek点肯定在mid右边包括mid
            if (A[mid] < A[mid + 1]) {
                left = mid;
            } else {
                right = mid;
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
    
    public int findPeak(int[] A) {
        if (A == null || A.length == 0) {
            return -1;
        }
        
        int left = 1, right = A.length - 2;
        while (left <= right) {
            int mid = left + (right - left) / 2;
     
            if (A[mid] < A[mid + 1]) {
                left = mid + 1;
            } else {
                if (A[mid] > A[mid - 1]) {
                    return mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        // while 结束时 left 和 right 相错一步
        return A[left] > A[right] ? left : right;
        // 谁大 return 谁
    }
}
