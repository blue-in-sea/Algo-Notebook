/**
 * 252. Meeting Rooms (Similar to MergeIntervals)
 * Given an array of meeting time intervals where intervals[i] = [start_i, end_i], determine if a person could attend
 * all meetings.
 *
 * Input: intervals = [[0,30],[5,10],[15,20]]
 * Output: false
 *
 * Input: intervals = [[7,10],[2,4]]
 * Output: true
 */

class MeetingRooms {
    // Algo
    // 1. sort the interval by start_time first, then by the end_time
    // 2. check if the prev.end_time is conflict with the cur.start_time

    // Time: O(nlogn) on sorting
    // Space: O(1)
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        // the person can attend the first meeting for sure
        for (int i = 1; i < intervals.length; i++) {
            // if prev.end exceeds cur.start
            if (intervals[i - 1][1] > intervals[i][0]) {
                return false;
            }
        }
        return true;
    }
}
