/**
 * 106. Construct Binary Tree from Inorder and Postorder Traversal
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and postorder
 * is the postorder traversal of the same tree, construct and return the binary tree.
 *
 *         3
 *       /   \
 *     9       20
 *           /  \
 *         15     7
 *
 * (Solution) DFS
 *  * Key: inorder sequence used to define left-subtree & right subtree
 *                            R
 * postorder: [9, 15, 7, 20   |3]
 * inorder:   [9, |3|  15, 20, 7]
 *         L-tree      R-tree
 *
 *                      R
 * postorder: [15, 7   |20]
 * inorder:   [15, |20| 7]
 *          L-tree      R-tree
 */
class ReconstructBinaryTreeWithInorderAndPostorder {
    // Method 1: Utilizing the inOrder sequence to determine the size of left/right subtree
    // Time: O(n) where n is # of tree node
    // Space: O(h) where worst case O(n)
    public TreeNode buildTree(int[] inOrder, int[] postOrder) {
        // Assume inOrder and postOrder are not null, there is no duplicates in the BT,
        // the length of two array are guaranteed to be the same
        Map<Integer, Integer> inOrderMap = buildMap(inOrder);
        return dfs(postOrder, inOrderMap, 0, inOrder.length - 1, 0, postOrder.length - 1);
    }

    private TreeNode dfs(int[] postOrder, Map<Integer, Integer> inOrderMap,
                         int inLeft, int inRight, 
                         int postLeft, int postRight) {
        // have to cross each other to make sure no element being unprocessed
        if (inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(postOrder[postRight]);
        // locate the root in the inOrder sequence, so that we will know 
        // the size of left/right subtree
        int inMid = inOrderMap.get(root.val);
        // postOrder: [left subtree][right subtree][root]
        // inOrder:  [left subtree][root][right subtree]

        // On right-substree: 
        // [inMid + 1 ... inRight] 

        // postRight - postLeft = inRight - inMid
        // postLeft = postRight - inRight + inMid

        // postRight = postRight - 1
        root.right = dfs(postOrder, inOrderMap, inMid + 1, inRight, postRight - inRight + inMid, postRight - 1);
                                                                              
        // On left-substree:  
        // [inLeft ... inMid - 1]

        // postLeft = postLeft 

        // postRight - inMid + 1 = postRight - inRight
        // postRight = postRight - inRight + inMid - 1
        root.left = dfs(postOrder, inOrderMap, inLeft, inMid - 1, postLeft, postRight - inRight + inMid - 1);
        return root;
    }

    private Map<Integer, Integer> buildMap(int[] inOrder) {
        // <K , V> : <ele , idx>
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inOrder.length; i++) {
            map.put(inOrder[i], i);
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
