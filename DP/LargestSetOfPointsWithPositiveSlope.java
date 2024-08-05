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
    // when x2 > x1, we must have y2 > y1

    // 1. Sort the input points according to their x-axis - Time O(nlogn)
    // A[N] = {<x0, y0>, <x1, y1>, <x2, y2> .. <xn-1, yn-1>}

    // 2. Find the longest ascending subsequence based on their y-axis - Time O(n^2)
    // Set = {y0, y1, y2 ... } 
    static class MyComparator implements Comparator<Point> {
        @Override
        public int compare(Point p1, Point p2) {
            return p1.x != p2.x ? p1.x - p2.x : p2.y - p1.y;
            // x-value small goes front
            // y-value small goes back
        }
    }
    public int largest(Point[] points) {
        int n = points.length;

        Arrays.sort(points, new MyComparator());
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
