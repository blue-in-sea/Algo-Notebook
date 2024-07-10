/**
 * 78. Subsets (No Duplicate)
 * Given an integer arrays of unique elements, return all possible subsets 
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
class AllSubset {
    /**
     * Algo: DFS
     * 1. How many levels in the recursion tree?
     *    N level (if n = input.length).
     *    For each level, it makes the decision as to whether to put this element into the path set or not.
     * 2. How many different states should we put on each node?
     *    Two.
     *    Each case considers select (or not).
     *
     * Time: O(2^n)
     * Space: O(n) where n stack calls; at the bottom of each level, the path is size n
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(result, path, nums, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, int index){
        if (index == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        path.add(nums[index]);
        dfs(result, path, nums, index + 1);
        path.remove(path.size() - 1);

        dfs(result, path, nums, index + 1);
    }
}
