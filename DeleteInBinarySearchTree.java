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
public class DeleteInBinarySearchTree {
  /**
   * case 1: no L, no R
   * case 2: no L, yes R
   * case 3: yes L, no R
   * case 4: yes L, yes R
   *
   * Note that case 2 should combine with case 4
   * instead of combining with case 1!!!
   */
  public TreeNode deleteTree(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (key == root.key) {
      if (root.left == null) {
  /*    TreeNode tmp = root.right;
        root.right = null;
        root = null;
        return tmp;             */
        return root.right;
      } else if (root.right == null) {
        return root.left;
      } else if (root.right.left == null) {
        root.right.left = root.left;
        return root.right;
      } else {
        TreeNode newRoot = deleteSmallest(root.right);
        newRoot.left = root.left;
        newRoot.right = root.right; 
        return newRoot;
      }
    }
    if (key < root.key) {
      root.left = deleteTree(root.left, key);
    } else if (key > root.key) {
      root.right = deleteTree(root.right, key);
    }
    return root;
  }

  private TreeNode deleteSmallest(TreeNode root) {
    while (root.left.left != null) {
      root = root.left;
    }
    TreeNode smallest = root.left;
    root.left = root.left.right;
    return smallest;
  }
}


/**
 * reorgnize the case classifiers 
 */
public class Solution {
  public TreeNode deleteTree(TreeNode root, int key) {
    if (root == null) {
      return null;
    }
    if (key == root.key) {
      if (root.left == null && root.right == null) {     /* case 1: no L, no R .   */
        return root.right;                           
      } else if (root.right == null) {                   /* case 3: yes L, no R    */
        return root.left;
      } else if (root.right.left == null) {              /* case 4: yes L, yes R   */
        root.right.left = root.left;                     /* (case 2:  no L, yes R) */
        return root.right;
      } else {                                           /* case 4: yes L, yes R   */
        TreeNode newRoot = deleteSmallest(root.right);   /* (case 2:  no L, yes R) */
        newRoot.left = root.left;
        newRoot.right = root.right; 
        return newRoot;
      }
    }
    if (key < root.key) {
      root.left = deleteTree(root.left, key);
    } else if (key > root.key) {
      root.right = deleteTree(root.right, key);
    }
    return root;
  }

  private TreeNode deleteSmallest(TreeNode root) {
    while (root.left.left != null) {
      root = root.left;
    }
    TreeNode smallest = root.left;
    root.left = root.left.right;
    return smallest;
  }
}
