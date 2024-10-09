/**
 * 211. Reconstruct Binary Search Tree With Postorder Traversal
 * Given the postorder traversal sequence of a binary search tree, reconstruct the original tree.
 * postorder traversal = {1, 4, 3, 11, 8, 5}
 * the corresponding binary search tree is
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
 * preorder: [5, 3, 1, 4, 8, 11]
 * inorder: [ 1, 3, 4, 5, 8, 11]
 */
public class ReconstructBinarySearchTreeWithPostorderTraversal {
    public TreeNode reconstruct(int[] post) {
        // Assume the given sequence is not null,
        // there is no duplicate in the binary search tree
        // Traversing position of the post order,
        // we traverse and construct the binary search tree
        // from the post's end to start
        int[] index = new int[] { post.length - 1 };
        return helper(post, index, Integer.MIN_VALUE);
    }

    private TreeNode helper(int[] post, int[] index, int min) {
        // 1. the last element in the post order sequence has to be the root
        // 2. the root has to be the 'min' value at the current level
        // (since we are build the BST from right subtree to left subtree,
        //  which means root has smaller than its right subtree)
        // 3. 'min' 是用来锁住区间的
        // (如果不在区间内就 return null, 当前元素就被 skip 到下一次 process)
        // 4. index 是当前元素在 post 里的位置
        // (用一个长度为一的 integer array 作为全局变量传下去，并且每次向前一步)
        if (index[0] < 0 || post[index[0]] <= min) {
            return null;
        }
        TreeNode root = new TreeNode(post[index[0]--]);
        root.right = helper(post, index, root.key);
        root.left = helper(post, index, min);
        return root;
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
