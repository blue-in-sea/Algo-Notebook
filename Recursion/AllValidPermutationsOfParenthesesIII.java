/**
 * LaiCode 642. All Valid Permutations Of Parentheses III
 * Get all valid permutations of l pairs of (), m pairs of <> and n pairs of {}, subject to the priority restriction:
 * {} higher than <> higher than ().
 * 
 * Assume: 
 * l, m, n >= 0, 
 * l + m + n >= 0
 *
 * l = 1, m = 1, n = 0, all the valid permutations are ["()<>", "<()>", "<>()"].
 * l = 2, m = 0, n = 1, all the valid permutations are [“()(){}”, “(){()}”, “(){}()”, “{()()}”, “{()}()”, “{}()()”].
 */
public class AllValidPermutationsOfParenthesesIII {
  private static final char[] PS = new char[]{ '(', ')', '<', '>', '{', '}' };

  public List<String> validParenthesesIII(int l, int m, int n) {
    // Assume: l, m, n >= 0
    List<String> res = new ArrayList<>();

    int[] remain = new int[]{l, l, m, m, n, n};
    int totalLen = 2 * (l + m + n);

    StringBuilder sb = new StringBuilder();
    Stack<Integer> stack = new Stack<>();

    dfs(res, sb, stack, totalLen, remain);
    return res;
  }

  private void dfs(List<String> res, StringBuilder sb, Stack<Integer> stack, int totalLen, int[] remain) {
    if (sb.length() == totalLen) {
      res.add(new String(sb));
      return;
    }

    for (int i = 0; i < remain.length; i++) {
      if (i % 2 == 0) { // 左括号 
                              // (栈空         或   栈顶比当前大)
        if (remain[i] > 0 && (stack.isEmpty() || stack.peek() > i)) {
          sb.append(PS[i]);
          remain[i]--;
          stack.push(i);
          dfs(res, sb, stack, totalLen, remain);
          
          sb.deleteCharAt(sb.length() - 1);
          remain[i]++;
          stack.pop();
        }
      } else {        // 右括号
        if (!stack.isEmpty() && stack.peek() == i - 1) {
          sb.append(PS[i]);
          remain[i]--;
          stack.pop();
          dfs(res, sb, stack, totalLen, remain);
          
          sb.deleteCharAt(sb.length() - 1);
          remain[i]++;
          stack.push(i - 1);
        }
      }
    }
  }
}
