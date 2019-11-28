/**
 * public class TreeNode {
 *   public int key;
 *   public TreeNode left;
 *   public TreeNode right;
 *   public TreeNode(int key) {
 *     this.key = key;
 *   }
 * }
 */
public class BinaryTreeLongestConsecutiveSequence {
  
  // Top-down Recursion Approach 
  // Time: O(N)
  // Space: O(H)
  public int longestConsecutive(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int[] max = new int[1]; // attentions for array size
    dfs(root, root.key, 0, max);
    return max[0];
  }

  private void dfs(TreeNode root, int parent, int longest, int[] max) {
    // base case
    if (root == null) {
      return;
    }

    if (parent == root.key - 1) { // 判断 curNode 能不能与 parentNode 接上
      longest++;
    } else {
      longest = 1;
    }

    max[0] = Math.max(max[0], longest);
    dfs(root.left, root.key, longest, max);
    dfs(root.right, root.key, longest, max);
  }
  
  
  // Bottom-up Recursion Approach 
  // Time: O(N)
  // Space: O(H)
  public int longestConsecutive(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int[] max = new int[1];
    dfs(root, max);
    return max[0];
  }

  private int dfs(TreeNode root, int[] max) {
    // base case
    if (root == null) {
      return 0;
    }

    int cur = 1;
    // 2. What should we expect from left and right child?
    int left = dfs(root.left, max);
    int right = dfs(root.right, max);

    // 1. What should we do at the current level?
    // Note: 这里需要在当前层查下一层，因为需要判断下一层的 node 
    //       返回上来能不能与我当前 node 相接上    
                                          /* 在这之前要 null check */
    if (root.left != null && root.key + 1 == root.left.key) {
      cur = Math.max(cur, 1 + left);
    }
    if (root.right != null && root.key + 1 == root.right.key) {
      cur = Math.max(cur, 1 + right);
    }
    max[0] = Math.max(max[0], cur);
    // 3. Waht should we return to the parent level?
    return cur;
  }
}
