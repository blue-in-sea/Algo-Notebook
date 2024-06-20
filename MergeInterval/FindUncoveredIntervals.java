/**
 * Given a time period and a list of time intervals, find all uncovered intervals
 *
 * Input: Time intervals {[0, 2], [4,8]}, Time period [0, 10]
 * Output {[3, 3], [9, 10]}
 */
public class FindUncoveredIntervals {
    // Time: O(nlogn) on sorting
    // Space: O(1)
    public static List<int[]> findUncoveredIntervals(int[][] intervals, int[] period) {
        List<int[]> res = new ArrayList<>();
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        for (int i = 0; i < intervals.length; i++) {
            // if in the start, cur.start > period.start
            if (i == 0) {
                if (intervals[i][0] > period[0]) {
                    res.add(new int[]{period[0], intervals[i][0] - 1});
                }
            }
            else {
                // if prev.end < cur.start
                if (intervals[i - 1][1] < intervals[i][0]) {
                    res.add(new int[]{intervals[i - 1][1] + 1, intervals[i][0] - 1});
                }
                // if by the end, and cur.end < period.end
                if (i == (intervals.length - 1)) {
                    res.add(new int[]{intervals[i][1] + 1, period[1]});
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] intervals = {{4, 8}, {0, 2}};
        int[] period = {0, 10};

        List<int[]> res = findUncoveredIntervals(intervals, period); // expect [3,3], [9,10]
        res.forEach(e -> System.out.print(Arrays.toString(e)));
        System.out.println();


        int[][] intervals1 = {{9, 10}, {1, 2}, {5,6}};
        int[] period1 = {0, 11};
        List<int[]> res1 = findUncoveredIntervals(intervals1, period1); // expect [0,0],[3,4],[7,8],[11,11]
        res1.forEach(e -> System.out.print(Arrays.toString(e)));
        System.out.println();

        int[][] intervals2 = {{6, 7}, {1, 2}, {5,8}};
        int[] period2 = {0, 11};
        List<int[]> res2 = findUncoveredIntervals(intervals2, period2); // expect [0,0],[3,4],[8,11]
        res2.forEach(e -> System.out.print(Arrays.toString(e)));
        System.out.println();
    }
}
