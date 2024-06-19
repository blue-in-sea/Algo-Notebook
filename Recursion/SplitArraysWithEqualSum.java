/**
 * 548. Split Array sWith Equal Sum
 * Given an input array with an integer k, how to split the array into k subarray such that each
 * subarray shares the same sum
 *
 * arr = [3, -1, 4, 6, -8, 1, 1] and k == 3
 * output = [3, -1], [4, 6, -8], [1, 1] which all have a sum is equal to 2
 */
public class SplitArraysWithEqualSum {
    public static void main(String[] args) {
        int[] arr = {3, -1, 4, 6, -8, 1, 1};
        int k = 3;
        System.out.println(split(arr, k));
    }

    private static List<List<Integer>> split(int[] array, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        /** calculate the total sum of the array, to find the equal sum of the subarray */
        int total = 0;
        for (int num : array) {
            total += num;
        }

        int sum = total / k;
        dfs(array, res, path, k, sum, 0, 0);
        return res;
    }

    private static void dfs(int[] array, List<List<Integer>> res, List<Integer> path,
                            int k, int sum, int startIndex, int level) {
        if (level == k || startIndex == array.length) {
            if (path.size() == k) {
                res.add(new ArrayList<>(path));
            }
            return;
        }

        int currSum = 0;
        for (int i = startIndex; i < array.length; i++) {
            currSum += array[i];
            if (currSum == sum) {
                path.add(i);
                dfs(array, res, path, k, sum, i + 1, level + 1);
                path.remove(path.size() - 1);
            }
        }
    }
}
