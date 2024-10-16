/**
 * 77. Combinations
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n. You may return the answer in any order.
 *
 * Input: n = 4, k = 2
 * Output: 
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 * Explanation: There are 4 choose 2 = 6 total combinations.
 * Note that combinations are unordered, i.e., [1,2] and [2,1] are considered to be the same combination.
 */
class Combination {
    // Time: C(N, K) = N! / (N−k)!k!
    // Space: O(k)
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(result, path, 1, n, k);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> path, int index, int n, int k) {

        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = index; i <= n; i++) {
            path.add(i);
            dfs(result, path, i + 1, n, k);
            path.remove(path.size() - 1);
        }
    }

    // ============================================================================
    // Version 2
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(res, path, n, k, 0);
        return res;
    }
    
    private void dfs(List<List<Integer>> res, List<Integer> path, int n, int k, int index) {
        if (index == n) {
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        // add
        path.add(index + 1);
        dfs(res, path, n, k, index + 1);
        path.remove(path.size() - 1);

        // not add
        dfs(res, path, n, k, index + 1);
    }
}
