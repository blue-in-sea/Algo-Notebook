/**
 * 78. Subsets (No Duplicate)
 * Given an integer arrays of unique elements, return all possible subsets 
 *
 * Input: nums = [1,2,3]
 * Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 */
class AllSubset {
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
