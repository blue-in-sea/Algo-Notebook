
/**
 * 404. Factor Combinations
 * Given an integer number, return all possible combinations of the factors that can multiply to the target number.
 *
 * Give A = 24
 * since 24 = 2 x 2 x 2 x 3
 *
 *               = 2 x 2 x 6
 *
 *               = 2 x 3 x 4
 *
 *               = 2 x 12
 *
 *               = 3 x 8
 *
 *               = 4 x 6
 *
 * should return { { 2, 2, 2, 3 }, { 2, 2, 6 }, { 2, 3, 4 }, { 2, 12 }, { 3, 8 }, { 4, 6 } }
 *
 * note: duplicate combination is not allowed.
 */
public class FactorCombinations {
    public List<List<Integer>> combinations(int target) {
        if (target == 1) return new ArrayList<>();

        List<Integer> factors = getFactor(target);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(res, path, 0, target, factors);
        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int index,
        int target, List<Integer> factors) {
        // base case
        if (index == factors.size()) {
            if (target == 1) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        // choose 0 factor at factors.get(index)
        dfs(res, path, index + 1, target, factors);
        // choose 1 factor 
        if (target % factors.get(index) == 0) {
            path.add(factors.get(index));
            dfs(res, path, index, target / factors.get(index), factors);
            path.remove(path.size() - 1);
        }
    }

    private List<Integer> getFactor(int n) {
        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= n - 1; i++) {
            if (n % i == 0) {
                res.add(i);
            }
        }
        return res;
    }
}
