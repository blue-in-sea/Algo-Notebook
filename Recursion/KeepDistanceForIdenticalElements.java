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
