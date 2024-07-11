/**
 * 47. Permutations II
 * Given a collection of numbers that might contain duplicates, return all possible unique permutations in any order.
 *
 * Input: nums = [1,1,2]
 * Output:
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
class AllPermutationsII {
    public List<List<Integer>> permuteUnique(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        if (array == null) {
            return result;
        }
        // does not the use StringBuilder for path solution
        // because size of path soln does not change
        dfs(array, 0, result);
        return result;
    }
    // index: at the level of index, we are to determine for the current
    // permutation, which element is positioned at the index
    private void dfs(int[] array, int index, List<List<Integer>> result) {
        if (index == array.length) {
            // base case: at the level of index, we are determine the current indices
            // of the current permutation which element to choose
            result.add(arrayToList(array));
            return;
        }

        Set<Integer> used = new HashSet<> ();
        for (int i = index; i < array.length; i++) {
            // hashset.add(a[i]) will return false if the value a[i]
            // is already in set
            if (used.add(array[i])) {
                swap(array, i, index);
                dfs(array, index + 1, result);
                // has to be (index + 1) to push down the unswap part (trim)
                // # of breaking points decreases as level goes deep
                swap(array, i, index);
            }
        }
    }

    private void swap(int[] array, int a, int b) {
        int tmp = array[a];
        array[a] = array[b];
        array[b] = tmp;
    }

    private List<Integer> arrayToList(int[] arr) {
        List<Integer> list = new ArrayList<>();
        for (int a : arr) {
            list.add(a);
        }
        return list;
    }
}
