/**
 * LaiCode 73. Combinations Of Coins
 * Given a number of different denominations of coins (e.g., 1 cent, 5 cents, 10 cents, 25 cents), get all the
 * possible ways to pay a target number of cents.
 *
 * coins = [25, 10, 5, 2, 1], target = 99
 */
public class CombinationsOfCoins {
    public List<List<Integer>> combinations(int target, int[] coins) {
        List<List<Integer>> res = new ArrayList<>();
        int[] path = new int[coins.length];
        dfs(res, path, 0, target, coins);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] path, int index, int target, int[] coins) {
        if (index == coins.length) {
            if (target == 0) {
                res.add(ToArrayList(path));
            }
            return;
        }
        // use one
        if (target - coins[index] >= 0) {
            path[index] += 1;
            dfs(res, path, index, target - coins[index], coins);
            path[index] -= 1;
        }
        // do not use 
        dfs(res, path, index + 1, target, coins);

    }

    // ===============================================================================
    // version 2
    private void dfs(List<List<Integer>> res, int[] path, int[] coins, int index, int target) {
        if (target == 0) {
            res.add(arrayToList(path));
            return;
        }

        for (int i = index; i < coins.length; i++) {
            if (target - coins[i] >= 0) {
                path[i]++;
                dfs(res, path, coins, i, target - coins[i]);
                path[i]--;
            }
        }
    }

    // ===============================================================================
    // Utils
    private List<Integer> ToArrayList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int ele : array) list.add(ele);
        return list;
    }
    // Arrays.stream(ints).boxed().toList();
}
