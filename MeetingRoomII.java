// Solution: Time O(nlogn) = sort all points O(nlogn) in the iinterval + check all points O(n)    [n = airplance.size() * 2]
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
    int time;
    int type;
    
    Event(int time, int type) {
        this.time = time;
        this.type = type; 
    }
}

class MyComparator implements Comparator<Event> {
    @Override
    public int compare(Event a, Event b) {
        if (a.time == b.time) {
            return a.type - b.type;
        }
        return a.time - b.time;
    }
}

public class MeetingRoomII {
    /**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        List<Event> list = new ArrayList<>(intervals.size() * 2);
        for (Interval meeting : intervals) {
            list.add(new Event(meeting.start, 1));
            list.add(new Event(meeting.end, -1));
        }
        Collections.sort(list, new MyComparator());
        int room = 0, res = 0;
        for (Event m : list) {
            if (m.type == 1) {
                room++;
            } else {
                room--;
            }
            res = Math.max(room, res);
        }
        return res;
    }
}
