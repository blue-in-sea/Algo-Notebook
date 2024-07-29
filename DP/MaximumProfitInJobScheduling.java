/**
 * LaiCode 1235. Maximum Profit in Job Scheduling
 * We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of 
 * profit[i].
 *
 * You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are 
 * no two jobs in the subset with overlapping time range. If you choose a job that ends at time X you will be able to
 * start another job that starts at time X.
 * 
 * Input: startTime = [1,2,3,3], endTime = [3,4,5,6], profit = [50,10,40,70]
 * Output: 120
 * Explanation: The subset chosen is the first and fourth job. 
 * Time range [1-3]+[3-6] , we get profit of 120 = 50 + 70.
 * 
 * Input: startTime = [1,2,3,4,6], endTime = [3,5,10,6,9], profit = [20,20,100,70,60]
 * Output: 150
 * Explanation: The subset chosen is the first, fourth and fifth job. 
 * Profit obtained 150 = 20 + 70 + 60.
 */
class MaximumProfitInJobScheduling {
    // DP + TreeMap <Key: endTime, Value: maxProfit by this Time>
    // Time: O(nlogn) 
    // Space: O(n)
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        // Get the number of jobs
        int n = profit.length;

        // Create a 2D array 'jobs' to store information about each job
        int[][] jobs = new int[n][3];

        // Populate 'jobs' with start times, end times, and profits
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
        }

        // Sort 'jobs' based on end times in ascending order
        Arrays.sort(jobs, (a, b) -> a[1] - b[1]);

        // Use a TreeMap to store the dynamic programming states (end time -> maximum profit)
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        treeMap.put(0, 0);  // Initialize the TreeMap with a key-value pair representing no jobs scheduled
       
        for (int[] job : jobs) {
            // Calculate the current profit by adding the current job's profit to the maximum profit until the end time of the previous job
            int currentProfit = treeMap.floorEntry(job[0]).getValue() + job[2];

            if (currentProfit > treeMap.lastEntry().getValue()) {
                // Update the TreeMap with a new key-value pair representing the end time of the current job and the maximum profit achievable
                treeMap.put(job[1], currentProfit);
            }
        }

        // Return the maximum profit achievable by considering all jobs
        return treeMap.lastEntry().getValue();
    }
}
