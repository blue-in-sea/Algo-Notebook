/**
 * 90. Subsets II
 * Given an integer array that may contain duplicates, return all possible subsets
 *
 * Input: nums = [1,2,2]
 * Output: [[],[1],[1,2],[1,2,2],[2],[2,2]]
 */
class AllSubsetsII {
    /**
     * Algo: DFS
     * 1. How many levels in the recursion tree?
     *    N level (if n = input.length).
     *    For each level, it makes the decision as to whether to put this element into the path set or not.
     * 2. How many different states should we put on each node?
     *    Two.
     *    Each case considers select (or not).
     *
     * Time: O(n * 2^n) where 2^n to generate all subsets and then copy n element into output list
     * Space: O(n) where n stack calls; at the bottom of each level, the path is size n
     */
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums); // sort the num
        dfs(result, path, nums, 0);
        return result;
    }

    // Version 1
    private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, int index){
        if (index == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        
        path.add(nums[index]);
        dfs(result, path, nums, index + 1);
        path.remove(path.size() - 1);

        // skip all duplicates
        while (index < nums.length - 1 && nums[index] == nums[index + 1]) {
            index++;
        }

        dfs(result, path, nums, index + 1);
    }

    // Version 2 
    private void dfs(List<List<Integer>> result, List<Integer> path, int[] nums, int index){
        result.add(new ArrayList<>(path));

        for (int i = index; i < nums.length; i++) {
            if (i > index && nums[i] == nums[i - 1]) continue;   // remember to sort the arr
            path.add(nums[i]);
            dfs(result, path, nums, i + 1);
            path.remove(path.size() - 1);
        }
    }
}
