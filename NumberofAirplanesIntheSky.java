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
class Event {
    public int time;
    public int type; // 1: take off, 0: landing
    public Event(int time, int type) {
        this.time = time;
        this.type = type;
    }

    public static Comparator<Event> MyComparator = new Comparator<Event>() {
        @Override
        public int compare(Event e1, Event e2) {
            if (e1.time == e2.time) {
                return e1.type - e2.type;
            }
            return e1.time - e2.time;
        }
    };
}
    
public class NumberofAirplanesIntheSky {
    /**
     * @param airplanes: An interval array
     * @return: Count of airplanes are in the sky.
     */
    public int countOfAirplanes(List<Interval> airplanes) {
        List<Event> list = new ArrayList<>(airplanes.size() * 2);
        for (Interval plane : airplanes) {
            list.add(new Event(plane.start, 1));  // take off
            list.add(new Event(plane.end, 0));    // landing
        }
        
        Collections.sort(list, Event.MyComparator);
        int sky = 0, res = 0;  
        // when airplanes = [] return 0, or res = 1 (but sanity check airplanes is empty)
        for (Event e : list) {
            if (e.type == 1) {
                sky++;
            } else {
                sky--;
            }
            res = Math.max(sky, res);
        }
        
        return res;
    }
}

