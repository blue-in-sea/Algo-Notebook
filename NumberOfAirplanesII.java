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
            cur += entry.getValue();
            list.add(Arrays.asList((int) entry.getKey(), cur));
        }
        return list;
    }

    public static void printInput(List<Interval> airplanes) {
        System.out.print('<');
        for (Interval points : airplanes) {
            System.out.print(" (");
            System.out.print(points.start);
            System.out.print(",");
            System.out.print(points.end);
            System.out.print(") ");

        }
        System.out.println('>');
    }

    public static void printOutput(List<List<Integer>> flightChart) {
        System.out.print('<');
        for (List<Integer> status : flightChart) {
            System.out.print(" [");
            System.out.print(status.get(0));
            System.out.print(",");
            System.out.print(status.get(1));
            System.out.print("] ");

        }
        System.out.println('>');
    }

    public static void main(String[] args) {
        NumberOfAirplanesII soln = new NumberOfAirplanesII();

        // test case 1
        List<Interval> flightReport = new ArrayList<>();
        flightReport.add(new Interval(1, 10));
        flightReport.add(new Interval(2, 3));
        flightReport.add(new Interval(5, 8));
        flightReport.add(new Interval(13, 14));
        flightReport.add(new Interval(4, 7));

        List<List<Integer>> list = soln.statusOfAirplanes(flightReport);
        printInput(flightReport);
        printOutput(list);


        // test case 2
        List<Interval> flightStatus = new ArrayList<>();
        flightStatus.add(new Interval(1, 2));
        flightStatus.add(new Interval(2, 3));
        flightStatus.add(new Interval(3, 4));

        list = soln.statusOfAirplanes(flightStatus);
        printInput(flightStatus);
        printOutput(list);
    }
}
