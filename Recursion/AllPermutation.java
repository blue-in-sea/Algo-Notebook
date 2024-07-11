/**
 * 46. Permutations (No Dup)
 * Given an array of unique integers, return all the possible permutations. You can return the answer in any order.
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 */
class AllPermutation {
   /**
     * Algo: DFS with swap(i, j)
     * 1. How many levels in the recursion tree? What does it store on each level?
     *    3 levels, since each level represents a positions  
     *    For each level, it makes the decision on whether to this element into set or NOT
     * 2. How many different states should we put on each level
     *    # of the remaining unused letter 
     *    if we are on i-th level, we need to try (i - 1)th branches.
     */

    // Time: O(n * n!) where n! to find all permutations and then copy n element into output list
    // Space: O(n) where n stack calls; at the bottom of each level, the path is size n
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // in-place: so no need extra array to store each path
        dfs(result, nums, 0);
        return result;
    }
    private void dfs(List<List<Integer>> result, int[] nums, int index) {
        // base case
        if (index == nums.length) {
            result.add(arrayToList(nums)); 
            return;
        }
        // recursion
        for(int i = index; i < nums.length; i++) {
            swap(nums, i, index);
            dfs(result, nums, index + 1);
            swap(nums, i, index);
        }
    }

    private void swap(int[] array, int left, int right) {
        int temp = array[left];
        array[left] = array[right];
        array[right] = temp;
    }

    private List<Integer> arrayToList(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int n : nums) {
            list.add(n);
        }
        return list;
    }
}
