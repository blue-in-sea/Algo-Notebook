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
 */
class Combination {
    // Time: C(N, K) = (N−k)!k! / N!
    // Space: O(k)
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(result, path, 1, n, k);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> path, 
                     int index, int n, int k) {

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
}
