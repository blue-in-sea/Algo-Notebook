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
}

class MyComparator implements Comparator<Event> {
    public int compare(Event a, Event b) {
        if (a.time == b.time) {
            return a.type - b.type;
        }
        return a.time - b.time;
    }
}
 
public class Solution {
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
        
        Collections.sort(list, new MyComparator());
        int sky = 0, res = 0;  
        // when interval all miss each other 
        // at leaste one plane will in the sky
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
