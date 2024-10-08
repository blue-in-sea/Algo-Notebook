/**
 * LaiCode 647. Lowest Common Ancestor V
 * Given two nodes in a K-nary tree, find their lowest common ancestor.
 * 
 * -There is no parent pointer for the nodes in the K-nary tree.
 * -The given two nodes are guaranteed to be in the K-nary tree.
 *
 * Examples
 *
 *         5
 *       /   \
 *      9   12
 *    / | \      \
 *   1  2  3      14
 *
 * The lowest common ancestor of 2 and 14 is 5.
 * The lowest common ancestor of 2 and 9 is 9.
 */
/**
 * public class KnaryTreeNode {
 *     int key;
 *     List<KnaryTreeNode> children;
 *     public KnaryTreeNode(int key) {
 *         this.key = key;
 *         this.children = new ArrayList<>();
 *     }
 * }
 */
public class LCAV {
    public KnaryTreeNode lowestCommonAncestor(KnaryTreeNode root, KnaryTreeNode a, KnaryTreeNode b) {
        // assume root is not null, and two nodes are guaranteed to be in tree
        // base cases: no found or found a or found b 
        if (root == null || root == a || root == b) {
            return root;
        }
        KnaryTreeNode found = null;
        for (KnaryTreeNode child : root.children) {
            KnaryTreeNode node = lowestCommonAncestor(child, a, b);
            if (node == null) {  // case 1
                continue;
            }
            if (found == null) {  // case 2
                found = node;
            } else {  // case 3
                return root;
            }
        }
        return found;
    }
}
// recursive calls 
// case 1: if all of them are null, return null
// case 2: if only one of them is not null, return non-null
// case 3: if more than one of them are not null, return root
