/**
 * 47. Permutations II (Dups)
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
    /**
     * Algo: DFS with swap(i, j) 
     * DataStr: HashSet 在处理一个节点的时候，那些字母和 a[i] 交换过
     * 
     * 1. How many levels in the recursion tree? What does it store on each level?
     *    3 levels, since each level represents a positions  
     *    For each level, it makes the decision on whether to this element into set or NOT
     * 2. How many different states should we put on each level
     *    # of the remaining unused unique letter 
     *    if we are on i-th level, we need to try (i - 1)th branches.
     */
    
    // Time: O(n * n!) where n! to find all permutations and then copy n element into output list
    // Space: O(n) where n stack calls; in each stack call, O(n) to maintain hashset
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
