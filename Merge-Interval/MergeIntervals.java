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
