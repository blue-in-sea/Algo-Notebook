/**
 * 849. Maximize Distance to Closest Person
 * 
 * You are given an array representing a row of seats where seats[i] = 1 represents a person sitting in the ith seat, 
 * and seats[i] = 0 represents that the ith seat is empty (0-indexed).
 *
 * There is at least one empty seat, and at least one person sitting.
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 * Return that maximum distance to the closest person.
 *
 * Input: seats = [1,0,0,0,1,0,1]
 * Output: 2
 * Explanation: 
 * If Alex sits in the second open seat (i.e. seats[2]), then the closest person has distance 2.
 * If Alex sits in any other open seat, the closest person has distance 1.
 * Thus, the maximum distance to the closest person is 2.
 */
class MaximizeDistanceClosestPerson {
    /**
     * seats = [1,0,0,0,1,0,1]
     *
     * left = [0, 1, 2, 3, 0, 1, 0]
     * right = [0, 3, 2, 1, 0, 1, 0]
     * ans = Math.Math.min(left[i], right[i]));
     */
    public int maxDistToClosest(int[] seats) {
        int N = seats.length;
        int[] left = new int[N], right = new int[N];

        Arrays.fill(left, N);
        Arrays.fill(right, N);

        for (int i = 0; i < N; ++i) {
            if (seats[i] == 1) left[i] = 0;
            else if (i > 0) left[i] = left[i-1] + 1;
        }
        System.out.println(Arrays.toString(left));

        for (int i = N-1; i >= 0; --i) {
            if (seats[i] == 1) right[i] = 0;
            else if (i < N-1) right[i] = right[i+1] + 1;
        }

        System.out.println(Arrays.toString(right));

        int res = 0;
        for (int i = 0; i < N; ++i)
            if (seats[i] == 0)
                res = Math.max(res, Math.min(left[i], right[i]));

        return res;
    }
  


}
