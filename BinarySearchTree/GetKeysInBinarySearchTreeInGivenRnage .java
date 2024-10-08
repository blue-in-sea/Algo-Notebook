/**
 * 55. Get Keys In Binary Search Tree In Given Range
 * Get the list of keys in a given binary search tree in a given range[min, max] in ascending order, both min and max
 * are inclusive.
 *
 *         5
 *       /    \
 *     3        8
 *   /   \        \
 *  1     4        11
 *
 * get the keys in [2, 5] in ascending order, result is  [3, 4, 5]
 *
 * Corner Cases
 * What if there are no keys in the given range? Return an empty list in this case.
 * How is the binary tree represented?
 * We use the level order traversal sequence with a special symbol "#" denoting the null node.
 * The sequence [1, 2, 3, #, #, 4] represents the following binary tree:
 *
 *     1
 *   /   \
 *  2     3
 *       /
 *     4
 */
public class GetKeysInBinarySearchTreeInGivenRnage {
    public List<Integer> getRange(TreeNode root, int min, int max) {
        List<Integer> res = new ArrayList<>();
        dfs(root, min, max, res);
        return res;
    }

    // InOrder Traversal
    private void dfs(TreeNode root, int min, int max, List<Integer> res) {
        if (root == null) {
            return;
        }

        if (root.key > min) {
            dfs(root.left, min, max, res);
        }

        if (root.key >= min && root.key <= max) {
            res.add(root.key);
        }

        if (root.key < max) {
            dfs(root.right, min, max, res);
        }
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
