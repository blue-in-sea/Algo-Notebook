/**
 * 215. Reconstruct Binary Tree With Levelorder And Inorder
 * Given the levelorder and inorder traversal sequence of a binary tree, reconstruct the original tree.
 *
 * The given sequences are not null and they have the same length
 * There are no duplicate keys in the binary tree
 *
 *         5
 *
 *       /    \
 *
 *     3        8
 *
 *   /   \        \
 *
 * 1      4        11   
 *
 * level-order traversal = {5, 3, 8, 1, 4, 11}
 * inorder traversal =     {1, 3, 4, 5, 8, 11}
 */
public class ReconstructBinaryTreeWithLevelorderAndInorder {
    public TreeNode reconstruct(int[] inOrder, int[] levelOrder) {
        // Assume inOrder and levelOrder are not null, there is 
        // no duplicate in the binary tree
        Map<Integer, Integer> inOrderMap = buildMap(inOrder);
        List<Integer> levelList = buildList(levelOrder);
        return helper(levelList, inOrderMap);
    }

    private TreeNode helper(List<Integer> levelList, Map<Integer, Integer> inOrderMap) {
        if(levelList.isEmpty()) {
            return null;
        }
        TreeNode root = new TreeNode(levelList.remove(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : levelList) {
            if (inOrderMap.get(num) < inOrderMap.get(root.key)) {
                left.add(num);
            } else {
                right.add(num);
            }
        }
        root.left = helper(left, inOrderMap);
        root.right = helper(right, inOrderMap);
        return root;
    }

    private Map<Integer, Integer> buildMap(int[] inOrder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
        }
        return map;
    }

    private List<Integer> buildList(int[] levelOrder) {
        List<Integer> list = new ArrayList<>();
        for (int ele : levelOrder) {
            list.add(ele);
        }
        return list;
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
