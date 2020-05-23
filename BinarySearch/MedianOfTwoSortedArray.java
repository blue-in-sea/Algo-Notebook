class MedianOfTwoSortedArray {
    /**       left_part          |        right_part
      * A[0], A[1], ..., A[i-1]  |  A[i], A[i+1], ..., A[m-1]
      * B[0], B[1], ..., B[j-1]  |  B[j], B[j+1], ..., B[n-1]
      * 
      * 1) i + j == m - i + n - j
      * 2) find i such that B[j-1] <= A[i] and A[i-1] <= B[j] 
      *
      * Optimal: A[i - 1], B[j -1], A[i], B[j]
      * When i is found 
      * median = max(A[i-1], B[j-1]) (when m + n is odd)
      * median = (max(A[i-1], B[j-1]) + min(A[i], B[j]))/2 (when m + n is even)   
      *
      * Time: O(log(min(m,n))) 
      * Space: O(1) 
      */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m > n) return findMedianSortedArrays(nums2, nums1);
        int iMin = 0, iMax = m;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = (m + n + 1) / 2 - i;
            if (j > 0 && i < m && nums2[j - 1] > nums1[i]) {
                // case 1 : i is too small, we must increase it.
                iMin = i + 1;
            } else if (i > 0 &&  j < n && nums1[i - 1] > nums2[j]) {
                // case 2 : i is too big, we must decrease it.
                iMax = i - 1;
            } else {
                // case 3 : i is perfect, but we still need to process corner case
                int maxLeft = 0;
                if (i == 0) {
                    // corner 1 : A[i - 1] doesn't exist, then we don't need to check A[i-1] <= B[j].
                    maxLeft = nums2[j - 1];
                } else if (j == 0) {
                    // corner 2 : B[j - 1] doesn't exist.
                    maxLeft = nums1[i - 1];
                } else {
                    maxLeft = Math.max(nums1[i - 1], nums2[j - 1]);
                }
                // if m + n is odd
                if ((m + n) % 2 == 1) return maxLeft; 
                // if m + n is even
                int minRight = 0;
                if (i == m) {
                    // corner 3 : A[i] doesn't exist.
                    minRight = nums2[j];
                } else if (j == n){
                    // corner 3 : B[j] doesn't exist.
                    minRight = nums1[i];
                } else {
                    minRight = Math.min(nums2[j], nums1[i]);
                }

                return  (maxLeft + minRight) / 2.0; 
            }
        }

        return 0.0;
    }
}

