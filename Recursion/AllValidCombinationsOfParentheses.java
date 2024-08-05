/**
 * 46. Permutations
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 *
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 */
class AllValidCombinationsOfParentheses {
    /**
     * Algo: DFS (same as AllSubsets)
     * 1. How many levels in the recursion tree? What does it store on each level?
     *    N.
     *    Each level consider one position to add a parentheses
     * 2. How many different states should we put on each level
     *    2 
     *    (two states) Either added on this position either ‘(’ or ‘)’
     */

    // Time: O(2^(2n))
    // Space: O(2n)
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, 0, 0, n);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb,
                     int l, int r, int n) {
        // base case
        if (l + r == 2 * n) {
            res.add(sb.toString());
            return;
        }
        // add '('
        if (l < n) {
            sb.append('(');
            dfs(res, sb, l + 1, r, n);
            sb.deleteCharAt(sb.length() - 1);
        }
        // add ')'
        if (r < n && r < l) {
            sb.append(')');
            dfs(res, sb, l, r + 1, n);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}
