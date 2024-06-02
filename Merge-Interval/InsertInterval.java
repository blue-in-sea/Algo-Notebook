/**
 * 57. Insert newInterval
 * Insert newInterval into intervals such that intervals is still sorted in ascending order by start_i and
 * intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
 *
 * Return intervals after the insertion.
 * Note that you don't need to modify intervals in-place. You can make a new array and return it.
 *
 * Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
 * Output: [[1,5],[6,9]]
 *
 * Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * Output: [[1,2],[3,10],[12,16]]
 * Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].
 */
class InsertInterval {
   /**
     * ___: current interval; _ _ _: newInterval
     *
     * 1) cur.end < newInterval.start，then we can safely add i to result;
     * 	  newInterval still needs to be compared with latter intervals
     *
     * 	  |________|
     * 			         |_ _ _ _ _|
     *
     * 2) cur.start > newInterval.end，then we can safely add both to result，
     * 	  and mark newInterval as null
     *
     * 			         |________|
     * 	  |_ _ _ _ _|
     *
     * 3) There is overlap between i and newInterval. Merge curInterval into newInterval: res.start = min(cur.start, new.start) & res.start = max(cur.end > new.end)
     * then use the updated newInterval (res) to compare with latter intervals.
     * [case 1]	
     * 	  |________|
     * 		  |_ _ _ _ _|
     *
     * [case 2]	  			
     *
     * 		 |________|
     * 	 |_ _ _ _ _|			
     */
    
    // Time: O(n) given the input intervals were sorted 
    // Space: O(1) no extra space used 
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for(int[] curInterval : intervals){
            // case 1: insertion of the newInterval does not needed any more 
            // (or) cur.end < new.start 
            if(newInterval == null || curInterval[1] < newInterval[0]){
                res.add(curInterval);
            } 
            // case 2: cur.start > new.end
            else if(curInterval[0] > newInterval[1]){
                // be carefult the sequence here
                res.add(newInterval);
                res.add(curInterval);
                newInterval = null; // already inserte, set newInterval to be null
            } 
            // case 3: merge 2 overlap intervals
            else {   
                // new start time will be the min start
                newInterval[0] = Math.min(newInterval[0], curInterval[0]);//get min
                // new end time will be the max end
                newInterval[1] = Math.max(newInterval[1], curInterval[1]);//get max
            }
        }
        
        // post-process: where the interval will be on the last end 
        if(newInterval != null) {
            res.add(newInterval);
        }
        
        return res.toArray(new int[res.size()][]);
    } 
}
