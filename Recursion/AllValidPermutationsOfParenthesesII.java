/**
 * 179. All Valid Permutations Of Parentheses II
 * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}.
 *
 * Assume 
 * l, m, n >= 0
 * l + m + n > 0
 *
 * l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "(<>)", "<()>", "<>()"]
 */
public class AllValidPermutationsOfParenthesesII {
    // Time: O(n * n!) where n is totalLen (每次从 remain 里选一个)
    // Space: O(n)
    private static final char[] PS = new char[]{ '(', ')', '<', '>', '{', '}' };

    public List<String> validParentheses(int l, int m, int n) {
        // Assume: l, m, n >= 0
        List<String> res = new ArrayList<>();

        int[] remain = new int[]{l, l, m, m, n, n};
        int totalLen = 2 * (l + m + n);

        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        dfs(res, sb, stack, totalLen, remain);
        return res;
    }

    private void dfs(List<String> res, StringBuilder sb, Stack<Character> stack, int totalLen, int[] remain) {
        if (sb.length() == totalLen) {
            res.add(new String(sb));
            return;
        }

        for (int i = 0; i < remain.length; i++) {
            if (i % 2 == 0) { // 左括号
                if (remain[i] > 0) {
                    sb.append(PS[i]);
                    remain[i]--;
                    stack.push(PS[i]);
                    dfs(res, sb, stack, totalLen, remain);

                    sb.deleteCharAt(sb.length() - 1);
                    remain[i]++;
                    stack.pop();
                }
            } else {        // 右括号
                if (!stack.isEmpty() && stack.peek() == PS[i - 1]) {
                    sb.append(PS[i]);
                    remain[i]--;
                    stack.pop();
                    dfs(res, sb, stack, totalLen, remain);

                    sb.deleteCharAt(sb.length() - 1);
                    remain[i]++;
                    stack.push(PS[i - 1]);
                }
            }
        }
    }
}
