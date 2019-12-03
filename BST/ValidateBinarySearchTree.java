public class ValidateBinarySearchTree {
    public void deleteEdge(TreeNode root) {
	    if(root == null) return;
	    root = dfs(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode dfs(TreeNode root, int left, int right) {
	    if(root == null) return null;
	    if(root.val <= left || root.val >= right) return null;
	    root.left = dfs(root.left, left, root.val);
	    root.right = dfs(root.right, root.val, right);
	    return root;
    }
}
