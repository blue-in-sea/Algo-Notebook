/**
 * LaiCode 127. Lowest Common Ancestor II
 * Given two nodes in a binary tree (with parent pointer available), find their lowest common ancestor.
 * There is parent pointer for the nodes in the binary tree
 *
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
public class LCAII {
  public TreeNodeP lowestCommonAncestor(TreeNodeP a, TreeNodeP b) {
    int al = len(a);
    int bl = len(b);

    if (al > bl) {
      return mergeNode(a, b, al - bl);
    } else {
      return mergeNode(b, a, bl - al);
    }
  }

  private TreeNodeP mergeNode(TreeNodeP longer, TreeNodeP shorter, int diff) {
    while (diff > 0) {
      longer = longer.parent;
      diff--;
    }
    while (longer != shorter) {
      longer = longer.parent;
      shorter = shorter.parent;
    }
    return longer;
  }

  private int len(TreeNodeP node) {
    int len = 0;
    while (node != null) {
      node = node.parent;
      len++;
    }
    return len;
  }
}

/**
 * public class TreeNodeP {
 *   public int key;
 *   public TreeNodeP left;
 *   public TreeNodeP right;
 *   public TreeNodeP parent;
 *   public TreeNodeP(int key, TreeNodeP parent) {
 *     this.key = key;
 *     this.parent = parent;
 *   }
 * }
 */
