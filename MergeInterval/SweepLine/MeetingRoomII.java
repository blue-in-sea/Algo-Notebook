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
    
    // Time: 0(2 * N + 2Nlog2N) where N is the size of the intervals
    public int myMeetingRooms(int[][] intervals) {
        if (intervals == null) return 0;
        int N = intervals.length;
        int[][] time = new int[N * 2][2];
        for (int i = 0; i < N; i++) {
            int start = intervals[i][0];
            time[i][0] = start;
            time[i][1] = 1;
            int end = intervals[i][1];
            time[N + i][0] = end;
            time[N + i][1] = -1;
        }
        Arrays.sort(time, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    return a[1] - b[1];
                }
                return a[0] - b[0];
            }
        });
        int room = 0, cnt = 0;
        for (int[] t : time) {
            cnt += t[1];
            room = Math.max(room, cnt);
        }
        return room;
    }
}
