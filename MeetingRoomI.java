public class MeetingRoomI {

  // 2D Arr 
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
    for (int i = 0; i < intervals.length - 1; i++) {
      if (intervals[i+1][0] < intervals[i][1]) {
        return false;
      }
    }
    return true;
  }
}
