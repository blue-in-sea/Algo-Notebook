/**
 * Step 1: 按照区间start从小到大排序，满足题目要求的区间应该是没有交集的。
 * Step 2: 所以要比较看当前区间end是否大于下一个区间start, 
 *         并且不同区间start应该不相同。
 */
public class MeetingRoomI {
  /**
   * @param intervals: an array of meeting time intervals
   * @return: if a person could attend all meetings
   */
  // Soln 1: list + 往后查 start 
  public boolean canAttendMeetings(List<Interval> intervals) {
    if (intervals == null) {
      return false;
    }
    
    Collections.sort(intervals, new Comparator<Interval>(){
      public int compare(Interval a, Interval b) {
        return a.start - b.start;
      }
    });
    
    for (int i = 0; i < intervals.size(); i++) {
      if (intervals.get(i).start == intervals.get(i + 1).start) {
        return false;
      }
      if (intervals.get(i).end > intervals.get(i).start) {
        return false;
      }
    }
    return true;
  }
  

  // Soln 2: 2D Arr + 往前查 end 
  public boolean canAttendMeetings(int[][] intervals) {
    if (intervals == null) {
      return false;
    }
    Arrays.sort(intervals, new Comparator<int[]>() {
      @Override
      public int compare(int[] a, int[] b) {
        return a[0] - b[0];
      }
    });
    int end = intervals[0][1];
    for (int i = 1; i < intervals.length; i++) { // looping condition changes in Soln 2
      if (intervals[i][0] < end) { // 比较上次会议的 end
        return false;
      }
      end = Math.max(end, intervals[i][1]);
    }
    return true;
  }
}
