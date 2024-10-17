/**
 * 192. Kth Smallest With Only 2, 3 As Factors
 * Find the Kth smallest number s such that s = 2 ^ x * 3 ^ y, x >= 0 and y >= 0, x and y are all integers.
 *
 * Assumptions
 * K >= 1
 *
 * the smallest is 1
 * the 2nd smallest is 2
 * the 3rd smallest is 3
 * the 5th smallest is 2 * 3 = 6
 * the 6th smallest is 2 ^ 3 * 3 ^ 0 = 8
 */
public class KthSmallestWithOnly23AsFactors {
    public int kth(int k) {
        // Assume K >= 1
        int seed = 1;
        // We sue two deques to maintaon all the possible values.
        // The rule is 
        // deque2 only maintains the value of seed * 2^x
        // deque3 only maintains the value of seed * 2^x * 3^y
        Deque<Integer> two = new LinkedList<>();
        Deque<Integer> three = new LinkedList<>();

        two.add(seed * 2);
        three.add(seed * 3);

        int res = seed;
        while (k > 1) {

            if (two.peekFirst() < three.peekFirst()) {
                res = two.pollFirst();
                two.offerLast(res * 2);
                three.offerLast(res * 3);
            } else {
                res = three.pollFirst();
                three.offerLast(res * 3);
            }
            k--;
        }
        return res;
    }
}
