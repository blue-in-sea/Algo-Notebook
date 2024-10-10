/**
 * 889. Construct Binary Tree from Preorder and Postorder Traversal
 * Given two integer arrays, preorder and postorder where preorder is the preorder traversal of a binary tree of
 * distinct values and postorder is the postorder traversal of the same tree, reconstruct and return the binary tree.
 *
 *         1
 *       /   \
 *     2       3
 *   /  \     /  \
 *  4    5   6    7
 *
 * Input: preorder = [1,2,4,5,3,6,7], postorder = [4,5,2,6,7,3,1]
 * Output: [1,2,3,4,5,6,7]
 *
 * Input: preorder = [1], postorder = [1]
 * Output: [1]
 */
/**
 * Algo
 *     1. the root is the 1st ele in preorder
 *     2. find the next element from preorder, then its index from postorder map.
 *     3. For left tree , elements will be from start up to the index of next element from postorder array
 *     4. For right tree, elements will be from next index of the above element found up to end-1,
 *        because we already added root element into the tree, which is last in postorder array.
 *
 *                        x
 *     preorder =  [1,   [2,]   4,5,3,6,7]
 *     postorder = [4,5, [2,]     6,7,3,   1]
 *
 *     Note: in preOrder, root always be the first ele
 *           in postOrder, root always be the last ele
 */
class ReconstructBinaryTreeWithPreorderandPostorder {

    int preIndex = 0;

    public TreeNode constructFromPrePost(int[] preorder, int[] postorder) {
        // Assume preOrder, inOrder are not null, there is no duplicates in the BT,
        // the length of preOrder and inOrder are guaranteed to be the same
        Map<Integer, Integer> postOrderMap = buildMap(postorder);
        return dfs(preorder, postOrderMap, 0, postorder.length - 1);
        
    }

    private TreeNode dfs(int[] preOrder, Map<Integer, Integer> postOrderMap, int start, int end) {

        if (start > end) {
            return null;
        } 

        TreeNode root = new TreeNode(preOrder[preIndex]);
        preIndex++;

        if (start == end) {
            return root;
        }

        int postIndex = postOrderMap.get(preOrder[preIndex]);

        root.left = dfs(preOrder, postOrderMap, start, postIndex);
        root.right = dfs(preOrder, postOrderMap, postIndex + 1, end - 1);
        return root;
    }

    private Map<Integer, Integer> buildMap(int[] postOrder) {
        // <K , V> : <ele , idx>
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < postOrder.length; i++) {
            map.put(postOrder[i], i);
        }
        return map;
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
