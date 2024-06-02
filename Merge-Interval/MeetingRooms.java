class MeetingRoomsII {
    /**
     * 235. MeetingRoomII
     * Given an array of meeting time intervals where intervals[i] = [start_i, end_i], return the minimum rooms required.
     *
     * Input: intervals = [[0,30],[5,10],[15,20]]
     * Output: 2
     *
     * Input: intervals = [[7,10],[2,4]]
     * Output: 1
     */
    
    // Algo: 扫描线
    // Similar question: NumberOfAirPlanesII (find the max # of airplanes on the sky)
    
    // Data Str: TreeMap<k: time, v: cnt of the meeting room at this time> (sorted hashmap)
    // * search & insertion & deletion: O(logn)
    // * Time complexity to build a treemap: O(nlogn)
    // * Space complexity O(n) same with hashmap

    // 1. build the treemap (sort the time, keep track of the number of rooms needed at the given time)
    // 2. travser the treemap to find the max # of overlap intervals

    public int minMeetingRooms(int[][] intervals) {
        // corner case
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new TreeMap<Integer, Integer>();
        for (int[] interval : intervals) {
            // if start time
            map.put(interval[0], map.getOrDefault(interval[0], 0) + 1);
            // if end time
            map.put(interval[1], map.getOrDefault(interval[1], 0) - 1);
        }

        int max = 0; // max # of overlap intervals == min rooms needed 
        int sum = 0;
        for (int cnt : map.values()) {
            sum += cnt;
            max = Math.max(sum, max);
        }
        return max;
    }
}
