/**
 * 217. Largest Set Of Points With Positive Slope
 * Given an array of 2D coordinates of points (all the coordinates are integers), find the largest number of points that can form a set such that any pair of points
 * in the set can form a line with positive slope. Return the size of such a maximal set.
 *
 * The given array is not null
 * Note: if there does not even exist 2 points can form a line with positive slope, should return 0.
 *
 * <0, 0>, <1, 1>, <2, 3>, <3, 3>, the maximum set of points are {<0, 0>, <1, 1>, <2, 3>}, the size is 3.
 */
class Point {
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class LargestSetOfPointsWithPositiveSlope {
    // slope = (y2 - y1) / (x2 - x1) > 0
    // when x2 > x1, we must have y2 > y1 !!

    // 1. Sort the points by x ascending and y descending 
    // A[N] = {<x0, y0>, <x1, y1>, <x2, y2> .. <xn-1, yn-1>}

    // 2. Similar to longest ascending subsequence
    /// For every ending points[i], (linear scan)
    //    For every j -> [0, i)     (looking back)
    //        points[j].y < points[i].y -> positive slope 
    static class MyComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return p1.x != p2.x ? p1.x - p2.x : p2.y - p1.y;
            // sort by x ascending, then y descending 
        }
    }
    public int largest(Point[] points) {
        int n = points.length;

        // Arrays.sort(points, new MyComparator());
        Arrays.sort(points, (p1, p2) -> p1.x != p2.x ? p1.x - p2.x : p2.y - p1.y);
        int max = 0;
        int[] dp = new int[points.length];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (points[j].y < points[i].y) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            dp[i]++;
            max = Math.max(max, dp[i]);
        }
        return max == 1 ? 0 : max;
    }
}
