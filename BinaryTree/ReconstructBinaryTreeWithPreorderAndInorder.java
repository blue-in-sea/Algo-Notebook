/**
 * 105. Construct Binary Tree from Preorder and Inorder Traversal
 * Given two integer arrays preorder and inorder where preorder is the preorder traversal of a binary tree and inorder
 * is the inorder traversal of the same tree, construct and return the binary tree.
 *
 *         3
 *       /   \
 *     9       20
 *   /       /  \
 * 1       15     7
 *
 * preorder: [3, 9, 1, 2, 15, 7]
 * inorder: [1, 9, 3, 15, 20, 7]
 *
 * (Solution) DFS
 *            R    L-tree   R-tree
 * preorder: [3] [9, 1, 2] [15, 7]
 * inorder: [1, 9, 2] [3] [15, 20, 7]
 *            L-tree         R-tree
 *               R
 *  * preorder: [9] [1, 2]
 *  * inorder: [1] [9] [2]
 *              L       R
 */
class ReconstructBinaryTreeWithPreorderAndInorder {
    // Method 1: Utilizing the inOrder sequence to determine the size of left/right subtree
    // Time: O(n) where n is # of tree node
    // Space: O(h) where worst case O(n)
    public TreeNode buildTree(int[] preOrder, int[] inOrder) {
        // Assume preOrder, inOrder are not null, there is no duplicates in the BT,
        // the length of preOrder and inOrder are guaranteed to be the same
        Map<Integer, Integer> inOrderMap = buildMap(inOrder);
        return dfs(preOrder, inOrderMap, 0, inOrder.length - 1, 0, preOrder.length - 1);
    }

    private TreeNode dfs(int[] preOrder, Map<Integer, Integer> inOrderMap,
                         int inLeft, int inRight, 
                         int preLeft, int preRight) {
        // have to cross each other to make sure no element being unprocessed
        if (inLeft > inRight) {
            return null;
        }

        TreeNode root = new TreeNode(preOrder[preLeft]);
        // locate the root in the inOrder sequence, so that we will know 
        // the size of left/right subtree
        int inMid = inOrderMap.get(root.val);
        // preOrder: [root][left subtree][right subtree]
        // inOrder:  [left subtree][root][right subtree]

        // On left-substree: 
        // [inLeft ... inMid - 1]
        // preRight - (preLeft - 1)  = (InMid - 1) - inLeft, and so
        // preRight = preLeft + inMid - inLeft
        root.left = dfs(preOrder, inOrderMap, inLeft, inMid - 1, preLeft + 1, preLeft + inMid - inLeft);
                                                                              
        // On right-substree:  
        // [inMid + 1 ... inRight] 
        // preRight - preLeft = InRight - (inMid + 1), and so
        // preLeft = preRight - InRight + inMid - 1                                                               
        root.right = dfs(preOrder, inOrderMap, inMid + 1, inRight, preRight + inMid - inRight + 1, preRight);
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
