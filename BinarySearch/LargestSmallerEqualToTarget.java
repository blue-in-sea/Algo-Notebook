/**
 * Find the idx of the largest smaller or equal element to the target
 */
public class LargestSmallerEqualToTarget {
    // Binary Search
    // 找在 target 的左区间尽可能的大，或者等于 target 的 first occurrence
    private int largestSmallerEqual(int[] array, int target) {
        int l = 0, r = array.length - 1;
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (array[mid] <= target) {
                l = mid;
            } else {
                r = mid;
            }
        }
        if (array[r] <= target) return r;
        if (array[l] <= target) return l;
        return -1;  // cannot find, all ele are larger than target
    }
}
