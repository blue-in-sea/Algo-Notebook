/**
 * 215. Kth Largest Element in an Array
 * Given an integer array nums and an integer k, return the kth largest element in the array.
 * Note that it is the kth largest element in the sorted order, not the kth distinct element.
 *
 * Can you solve it without sorting?
 * 
 * Input: nums = [3,2,1,5,6,4], k = 2
 * Output: 5
 *
 * Input: nums = [3,2,3,1,2,4,5,5,6], k = 4
 * Output: 4
 * 
 * Constraints: 
 * 1 <= k <= nums.length <= 105, -104 <= nums[i] <= 104
 */
class CountingSortKthLargest {
    // Counting sort 
    // Time: O(n + k) where n array size and k as range = max - min + 1
    // Space: O(k) for cnt array
    public int findKthLargest(int[] array, int k) {
        // Assume 1 <= k <= nums.length

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int a : array) {
            min = Math.min(a, min);
            max = Math.max(a, max);
        }

        // init cnt array with range = max - min + 1
        int[] cnt = new int[max - min + 1];
        for (int a : array) {
            cnt[a - min]++;
        }

        int remain = k;
        // scan cnt array from end to start - findKthLargest
        for (int i = cnt.length - 1; i >= 0; i--) {
            remain -= cnt[i];
            if (remain <= 0) {
                return i + min;
            }
        }

        return -1;
    }
}
