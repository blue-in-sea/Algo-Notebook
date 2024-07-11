class AllPermutation {
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
