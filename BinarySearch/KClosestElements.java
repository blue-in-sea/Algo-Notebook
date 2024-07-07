/**
 * 658. Find K Closest Elements
 * Given a sorted integer array arr, two integers k and x, return the k closest integers to x in the array. The result should also be sorted in ascending order.
 *
 * An integer a is closer to x than an integer b if:
 * |a - x| < |b - x|, or
 * |a - x| == |b - x| and a < b
 *
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = 3
 * Output: [1,2,3,4]
 * Example 2:
 *
 * Input: arr = [1,2,3,4,5], k = 4, x = -1
 * Output: [1,2,3,4]
 */
class KClosestElements {
    // Algo: Binary Search To Find The Left Bound
    // Time: O(log(n−k) + k) 
    // Space: O(1)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // Initialize binary search bounds
        int l = 0, r = arr.length - k;
        
        // Binary search 
        while (l < r) {
            int mid = l + (r - l) / 2;

            if (x - arr[mid] > arr[mid + k] - x) {  // abs(x, a[m]) vs abs(x, a[m+k]) 比较两个端点的对于x绝对距离
                l = mid + 1;                        // 起点在后半部分
            } else {
                r = mid;                            // 起点在前半部分
            }
        }
        
        // copy result
        List<Integer> res = new ArrayList<Integer>();
        for (int i = l; i < l + k; i++) {
            res.add(arr[i]);
        }
        
        return res;
    }
}
