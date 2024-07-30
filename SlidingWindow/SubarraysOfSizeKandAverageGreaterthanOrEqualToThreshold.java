/**
 * 1343. Number of Sub-arrays of Size K and Average Greater than or Equal to Threshold
 * Given an array of integers arr and two integers k and threshold, return the number of sub-arrays of size k and 
 * average greater than or equal to threshold.
 *
 * Input: arr = [2,2,2,2,5,5,5,8], k = 3, threshold = 4
 * Output: 3
 * Explanation: Sub-arrays [2,5,5],[5,5,5] and [5,5,8] have averages 4, 5 and 6 respectively. All other sub-arrays of
 * size 3 have averages less than 4 (the threshold).
 *
 * Input: arr = [11,13,17,23,29,31,7,5,2,3], k = 3, threshold = 5
 * Output: 6
 * Explanation: The first 6 sub-arrays of size 3 have averages greater than 5. Note that averages are not integers.
 */
class SubarraysOfSizeKandAverageGreaterthanOrEqualToThreshold {
    // Slinding Windows 
    // Time: O(n), Space: O(1)
    public int numOfSubarrays(int[] arr, int k, int threshold) {
        int cnt = 0, sum = 0;
        int l = 0;
        for (int r = 0; r < arr.length; r++) {
            // subarray(i, j) mush have size of k
            while (r - l + 1 > k) {
                sum -= arr[l];
                l++;
            }
            // cnt number of valid res
            sum += arr[r];
            if (r - l + 1 == k && (sum / k) >= threshold) {
                cnt++;
            }
        }
        return cnt;
    } 
}
