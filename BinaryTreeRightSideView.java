/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */

public class BinaryTreeRightSideView {
    /**
     * @param root: the root of the given tree
     * @return: the values of the nodes you can see ordered from top to bottom
     *
     * Soln 1: BFS
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> view = new ArrayList<>();
        
        if (root == null) {
            return view;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                
                if (i == size - 1) {
                    view.add(cur.val);
                }
                
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        
        return view;
    }
    /**
     * Soln 2: DFSC!!!
     */
    public List<Integer> rightSideView(TreeNode root) {
        // <K, V> : <Depth, Value of the Node>
        Map<Integer, Integer> map = new HashMap<>();
        dfs(map, root, 0);
        
        int depth = 0;
        List<Integer> view = new ArrayList<>();
        while (map.containsKey(depth)) {
            view.add(map.get(depth));
            depth++;
        }
        
        return view;
    }
    
    private void dfs(Map<Integer, Integer> map, TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        
        map.put(depth, root.val);
        dfs(map, root.left, depth + 1);
        dfs(map, root.right, depth + 1);
    }
}
