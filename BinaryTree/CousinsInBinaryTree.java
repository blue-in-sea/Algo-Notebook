/**
 * 295. Cousins In Binary Tree
 * Medium
 * Given a binary Tree and the two keys, determine whether the two nodes are cousins of each other or not. Two nodes 
 * are cousins of each other if they are at the same level and have different parents.
 *
 * Assumptions:
 *
 * It is not guranteed the two keys are all in the binary tree.
 * There are no duplicate keys in the binary tree.
 * Examples:
 *
 *      6
 *    /   \
 *   3     5
 *  / \   / \
 *
 * 7   8 1   13
 * 7 and 1, result is true.
 * 3 and 5, result is false.
 * 7 and 5, result is false.
 */
class CousinsInBinaryTree {
    // Time: O(n), Space: O(n) where n for hashmap, h for stack calls 
    public boolean isCousin(TreeNode root, int a, int b) {
        Map<Integer, Integer> depth = new HashMap<>();
        Map<Integer, TreeNode> parent = new HashMap<>();
        dfs(depth, parent, root, null);
        return (depth.get(a) == depth.get(b) && parent.get(a) != parent.get(b));
    }

    private void dfs(Map<Integer, Integer> depth, Map<Integer, TreeNode> parent, TreeNode node, TreeNode par) {
        if (node != null) {
            depth.put(node.key, par != null ? 1 + depth.get(par.key) : 0);
            parent.put(node.key, par);
            dfs(depth, parent, node.left, node);
            dfs(depth, parent, node.right, node);
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
