/**
 * LaiCode 128. Lowest Common Ancestor III
 * Hard
 * Given two nodes in a binary tree, find their lowest common ancestor (the given two nodes are not guaranteed to be 
 * in the binary tree).
 * Return null If any of the nodes is not in the tree.
 * 
 * There is no parent pointer for the nodes in the binary tree
 * The given two nodes are not guaranteed to be in the binary tree
 *
 * Examples
 *
 *         5
 *
 *       /   \
 *
 *      9     12
 *
 *    /  \      \
 *
 *   2    3      14
 *
 * The lowest common ancestor of 2 and 14 is 5
 * The lowest common ancestor of 2 and 9 is 9
 * The lowest common ancestor of 2 and 8 is null (8 is not in the tree)
 */
public class LCAIII {
  public TreeNode lowestCommonAncestor(TreeNode root,
      TreeNode one, TreeNode two) {
    // The given two nodes are not guaranteed to be in the binary tree
    int a = one.key;
    int b = two.key;
    TreeNode result = helper(root, a, b);

    // start from the result node, could somehow be better 
    if (result != null) {
      // check if a in and b not in this tree
      if (result.key == a && helper(result, b, b) == null) {
        return null;
      } 
      // check if b in and a not in this tree
      else if (result.key == b && helper(result, a, a) == null) {
        return null;
      }
      // otherwise, it is the proper LCA result
      else {
        return result;
      }
    }
    return result;
  }

  private TreeNode helper(TreeNode root, int a, int b) {
    if (root == null || root.key == a || root.key == b) {
      return root;
    }

    TreeNode left = helper(root.left, a, b);
    TreeNode right = helper(root.right, a, b);

    if (left != null && right != null) {
      return root;
    }
    return left == null ? right : left;
  }
}

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
