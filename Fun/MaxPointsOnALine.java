/**
 * 149. Max Points on a Line
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number
 * of points that lie on the same straight line
 *
 * Input: points = [[1,1],[2,2],[3,3]]
 * Output: 3 where slope = 1
 *
 * Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
 * Output: 4 where slope = -1
 */
class MaxPointsOnALine {
    // Method: cntMap where <key = slope, value = cnt of points on this line>
    // slope = (y2 - y1) / (x2 - x1)
    // Time: O(n^2), Space: O(n)
    public int maxPoints(int[][] points) {
        int n = points.length;
        if (n == 1) return 1;

        int max = 0;
        for (int i = 0; i < n; i++) {
            Map<Double, Integer> cnt = new HashMap<>();
            for (int j = i + 1; j < n; j++) {
                double slope = findSlope(points[i], points[j]);
                cnt.put(slope, cnt.getOrDefault(slope, 1) + 1);
                max = Math.max(cnt.get(slope), max);
            }
        }

        return max;
    }

    private double findSlope(int[] p1, int[] p2) {
        int x1 = p1[0], x2 = p2[0];
        int y1 = p1[1], y2 = p2[1];

        if (x1 == x2) return Double.MAX_VALUE;
        if (y1 == y2) return 0;

        return (double) (y2 - y1) / (double) (x2 - x1);
    }
}
