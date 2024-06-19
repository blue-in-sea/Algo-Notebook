/**
 * 298. Binary Tree Longest Consecutive Sequence
 * Given the root of a binary tree, return the length of the longest consecutive sequence path.
 * A consecutive sequence path is a path where the values increase by one along the path.
 *
 *        1
 *     x     3
 *   2  x   x   4
 *            x    5
 * Input: root = [1,null,3,2,4,null,null,null,5]
 * Output: 3
 * Explanation: Longest consecutive sequence path is 3-4-5, so return 3.
 */

/**
 * class TreeNode {
 *     public int key;
 *     public TreeNode left;
 *     public TreeNode right;
 *     public TreeNode(int key) {
 *       this.key = key;
 *     }
 *   }
 */
public class BinaryTreeLongestConsecutiveSequence {
  // Top-down Recursion Approach (interview!!!)
  // Time: O(N)
  // Space: O(H)
  public int longestConsecutive(TreeNode root) {
    if (root == null) {
      return 0;
    }

    int[] max = new int[1]; // store a value of global max
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

    max[0] = Math.max(max[0], longest); // because Java always passed by value, hence we create an object to store
    // https://stackoverflow.com/questions/23110555/integer-and-integer-array-storage-on-stack-heap
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
