// Solution 2: Time O(n^2) = check all [i, j] schedule O(n^2) + check all points O(n)   [n = airplance.size() * 2]
/**
 * Definition of Interval:
 * public classs NumberofAirplanesIntheSkySolnTwo {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
class Point {
    int time;
    int flag;
    
    Point(int t, int f) {
        time = t;
        flag = f;
    }
}

public class Solution {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        Point[] arr = new Point[airplanes.size() * 2];
        int n = 0;
        for (Interval plane : airplanes) {
            arr[n++] = new Point(plane.start, 1);  // take-off
            arr[n++] = new Point(plane.end, -1);   // landing
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (!scheduler(arr[i], arr[j])) {
                    swap(arr, i, j);
                }
            }
        }
        
        int sky = 0, res = 0;
        for (Point event : arr) {
            sky += event.flag;
            res = Math.max(sky, res);
        }
        
        return res;
    }
    
    private boolean scheduler(Point a, Point b) {
        if (a.time < b.time) {
            return true;
        }
        if (a.time == b.time && a.flag < b.flag) {
            return true;
        }
        return false;
    }
    
    private void swap(Point[] arr, int i, int j) {
        Point tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
