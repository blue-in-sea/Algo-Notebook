class MajorityElement {
    // Time: O(n), Extra Space: O(1)
    public int majorityElement(int[] nums) {
        // 打擂台的一个思路
        // assume nums is not null and not empty
        // in order to be a majority number that has occurrence greater than n/2
        // it must carry the most occurrence in the given input 擂台赛赢家
        int cnt = 1;
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (cnt == 0) {
                cnt++;
                res = nums[i];
            } else if (res == nums[i]) {
                cnt++;
            } else {
                cnt--;
            }
        }
        return res;
    }
}
