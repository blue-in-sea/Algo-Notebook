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
 * 3) There is overlap between i and newInterval. We can merge curInterval into newInterval, 
 * then use the updated newInterval to compare with latter intervals.
 * [case 1]	
 * 	  |________|
 * 		  |_ _ _ _ _|
 *
 * [case 2]	  			
 *            
 * 		 |________|
 * 	 |_ _ _ _ _|			
 */

class InsertInterval {
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
