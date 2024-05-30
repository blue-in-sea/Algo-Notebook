public class MaxOverlapInterval {

    public static int getMax(int[][] intervals) {
        // sort by start of the interval
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int cnt = 0;
        int max = 0;

        for (int i = 0; i < intervals.length - 1; i++) {
            // at the beginning
            if (i == 0) {
                cnt++;
                max = Math.max(cnt, max);
            }

            // cur.end > next.start
            if (intervals[i][1] > intervals[i + 1][0]) {
                cnt++;
                max = Math.max(cnt, max);
            } else {
                cnt--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 7}, {7, 8}};
        System.out.println(getMax(intervals));

        int[][] intervals2 = {{1, 7}, {2, 6}, {9, 10}};
        System.out.println(getMax(intervals2));

        int[][] intervals3 = {{1, 7}, {2, 6}, {6, 8}, {9, 10}};
        System.out.println(getMax(intervals3));

        int[][] intervals4 = {{1, 7}, {2, 6}, {3, 4}, {6, 8}, {9, 10}};
        System.out.println(getMax(intervals4));
    }
}
