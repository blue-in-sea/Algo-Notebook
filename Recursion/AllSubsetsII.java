class AllSubsetsII {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        Arrays.sort(nums); // sort the num
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

        // skip all duplicates
        while (index < nums.length - 1 && nums[index] == nums[index + 1]) {
            index++;
        }

        dfs(result, path, nums, index + 1);
    }
}
