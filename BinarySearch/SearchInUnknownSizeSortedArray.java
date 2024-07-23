/**
 * 702. Search in a Sorted Array of Unknown Size
 * You have a sorted array of unique elements and an unknown size. You do not have an access to the array but you can
 * use the ArrayReader interface to access it. You can call ArrayReader.get(i) that:
 *
 * returns the value at the ith index (0-indexed) of the secret array (i.e., secret[i]), or
 * returns 231 - 1 if the i is out of the boundary of the array.
 * You are also given an integer target.
 *
 * Return the index k of the hidden array where secret[k] == target or return -1 otherwise.
 *
 * Input: secret = [-1,0,3,5,9,12], target = 9
 * Output: 4
 * Explanation: 9 exists in secret and its index is 4.
 * Example 2:
 *
 * Input: secret = [-1,0,3,5,9,12], target = 2
 * Output: -1
 * Explanation: 2 does not exist in secret so return -1.
 */
/**
 * // This is ArrayReader's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface ArrayReader {
 *     public int get(int index) {}
 * }
 */
class SearchInUnknownSizeSortedArray {
    // Time: O(nlogn)
    public int search(ArrayReader reader, int target) {
        if (reader == null) return -1;

        int left = 0, right = 1;
        while (reader.get(right) < target) {
            left = right;
            right = right * 2;
        }

        return binarySearch(reader, target, left, right);
    }

    private int binarySearch(ArrayReader reader, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (reader.get(mid) == target) {
                return mid;
            } else if (reader.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }
}
