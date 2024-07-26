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

    private List<Integer> ToArrayList(int[] array) {
        List<Integer> list = new ArrayList<>();
        for (int ele : array) list.add(ele);
        return list;
    }
}
