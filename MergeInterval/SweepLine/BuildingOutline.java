package MergeInterval.SweepLine;

import java.util.*;

public class BuildingOutline {
    /**
     * @param buildings: A list of lists of integers
     * @return: Find the outline of those buildings
     */
    public List<List<Integer>> buildingOutline(int[][] buildings) {
        List<List<Integer>> res = new ArrayList<>();
        if (buildings == null || buildings.length == 0 || buildings[0].length == 0) {
            return res;
        }
        
        List<int[]> points = new ArrayList<>();
        for (int[] b : buildings) {
            points.add(new int[]{b[0], -b[2]});
            points.add(new int[]{b[1], b[2]});
        }
        
        Collections.sort(points, new Comparator<int[]>() {
            @Override 
            public int compare(int[] x, int[] y) {
                return x[0] == y[0] ? x[1] - y[1] : x[0] - y[0];
            }
        });
        
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.put(0, 1);
        
        int prev = 0;
        int start = points.get(0)[0];
        for (int[] point : points) {
            if (point[1] < 0) {
                map.put(-point[1], map.getOrDefault(-point[1], 0) + 1);
            } else {
                int cnt = map.get(point[1]);
                if (cnt == 1) {
                    map.remove(point[1]);
                } else {
                    map.put(point[1], cnt - 1);
                }
            }
            int cur = map.firstKey();
            if (cur != prev) {
                if (prev != 0) {
                    res.add(Arrays.asList(start, point[0], prev));
                }
                start = point[0];
                prev = cur;
            }
        }
        return res;
    }
}
