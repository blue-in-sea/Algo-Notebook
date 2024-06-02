/**
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [start_i, end_i], merge all overlapping intervals, and return an 
 * array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * Input: intervals = [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlap, merge them into [1,6].
 * Example 2:
 *
 * Input: intervals = [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 */

class MergeIntervals {
    // Time: O(NlogN) sorting
    // Space: No extra space beside sorting & result list
    public int[][] merge(int[][] intervals) {
        // sort by start of the interval
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0])); 
        List<int[]> res = new ArrayList<>();

        for (int[] interval : intervals) {
            // at the beginning
            if (res.size() == 0) {
                res.add(interval);
            }
            // do not have any overlay
            if (res.getLast()[1] < interval[0]) {
                res.add(interval);
            }
            // otherwise, merge the interval has the largest end 
            res.getLast()[1] = Math.max(res.getLast()[1], interval[1]);
        }
        
        return res.toArray(new int[res.size()][]);
    }
}
