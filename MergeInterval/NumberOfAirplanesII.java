/**
 * Definition of Interval:
 * public classs Interval {
 *     int start, end;
 *     Interval(int start, int end) {
 *         this.start = start;
 *         this.end = end;
 *     }
 * }
 */
public class NumberOfAirplanesII {
    /**
     * @param airplanes: An interval array
     * @return: List of airplanes are in the sky during each interval.
     */
    public List<List<Integer>> statusOfAirplanes(List<Interval> airplanes) {
        List<List<Integer>> list = new ArrayList<>();
        Map<Integer, Integer> map = new TreeMap<>();
        for (Interval points : airplanes) {
            map.put(points.start, map.getOrDefault(points.start,0) + 1);
            map.put(points.end,  map.getOrDefault(points.end,0) - 1);
        }

        int cur = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            cur += (int) entry.getValue();
            list.add(Arrays.asList((int) entry.getKey(), cur));
        }
        return list;
    }
}
