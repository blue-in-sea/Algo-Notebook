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
