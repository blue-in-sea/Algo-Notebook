/**
 * 1200. Minimum Absolute Difference
 * Given an array of distinct int, find all pairs of elements with the minimum absolute difference of any two elements.
 *
 * Return a list of pairs in ascending order(with respect to pairs), each pair [a, b] follows
 *
 * a, b are from arr
 * a < b
 * b - a equals to the minimum absolute difference of any two elements in arr
 *
 * Input: arr = [4,2,1,3]
 * Output: [[1,2],[2,3],[3,4]]
 * Explanation: The minimum absolute difference is 1. List all pairs with difference equal to 1 in ascending order.
 * Example 2:
 *
 * Input: arr = [1,3,6,10,15]
 * Output: [[1,3]]
 *
 * Input: arr = [3,8,-10,23,19,-4,-14,27]
 * Output: [[-14,-10],[19,23],[23,27]]
 */
class MinimumAbsoluteDifference {
    // Sort and find minDiff
    // Find the pairs with minDiff
    // Time: O(nlogn) for sorting
    // Space: O(logn) or O(n) for sorting
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(arr);

        int min = Integer.MAX_VALUE;
        for (int i = 1; i < arr.length; i++) {
            min = Math.min(arr[i] - arr[i - 1], min);
        }

        for (int i = 1; i < arr.length; i++) {
            List<Integer> pairs = new ArrayList<>();
            if (arr[i] - arr[i - 1] == min) {
                pairs.add(arr[i - 1]);
                pairs.add(arr[i]);
                res.add(pairs);
            }
        }

        return res;
    }
}
