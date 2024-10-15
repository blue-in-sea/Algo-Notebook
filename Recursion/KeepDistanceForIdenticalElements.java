/**
 * 264. Keep Distance For Identical Elements
 * Given an integer k, arrange the sequence of integers [1, 1, 2, 2, 3, 3, ...., k - 1, k - 1, k, k], such that the output integer array satisfy this condition:
 * Between each two i's, they are exactly i integers (for example: between the two 1s, there is one number, between the two 2's there are two numbers).
 *
 * If there does not exist such sequence, return null.
 * k is guaranteed to be > 0
 *
 * k = 3, The output = { 2, 3, 1, 2, 1, 3 }.
 */
public class Solution {
    // Method 3: k levels,
    // Time: O(k^k)
    public int[] keepDistance(int k) {
        // corner case
        if (k < 0) return null;

        int[] res = new int[k * 2];
        return dfs(res, k) ? res : null;
    }

    private boolean dfs(int[] res, int k) {
        // base case
        if (k == 0) return true;

        for (int i = 0; i + k + 1 < res.length; i++) {
            if (res[i] == 0 && res[i + k + 1] == 0) {
                res[i] = k;
                res[i + k + 1] = k;
                if (dfs(res, k - 1)) {
                    return true;
                }
                res[i] = 0;
                res[i + k + 1] = 0;
            }
        }

        return false;
    }
}
