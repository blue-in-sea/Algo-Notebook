package BinaryTree;

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
     * BFS: level-order traversal (interview!!)
     * Time: O(N) where N is # of tree nodes
     * Space: O(D) where D is a tree diameter, worst case D = N
     */
    public List<Integer> rightSideViewBFS(TreeNode root) {
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
                    // add the most right element to the tree (at the cur level)
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
     * DFS: pre-order traversal + depth-map
     * Time: O(N) for visiting all nodes
     * Space: O(H) for stack call where worst case H = N
     */
    public List<Integer> rightSideViewDFS(TreeNode root) {
        // <K, V> : <Depth, Value of the most right Node on the cur depth>
        Map<Integer, Integer> map = new HashMap<>();  // map 存的 node 会被最右边的 node 覆盖掉
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
