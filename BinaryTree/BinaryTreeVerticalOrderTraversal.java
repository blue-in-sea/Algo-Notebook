/**
 * 987. Binary TreeVertical Order Traversal
 *               3
 *      9                20
 *              15               7
 *
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[9],[3,15],[20],[7]]
 */


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

public class BinaryTreeVerticalOrderTraversal {
    /**
     * BFS with Tree Map (self-balancing binary search tree)
     * [Time to insert n-th element = O(Log (n-1))]
     * Time: O(nlogn) where we do n insertions in the tree map
     * Space: O(n) where size of queues & treemap
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();

        if (root == null) {
            return res;
        }

        //<K: vertical-level sorting in ascending, V: a lists of node in the given level>
        Map<Integer, List<Integer>> map = new TreeMap<>();  // order map : bbst
        
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> qCol = new LinkedList<>();

        queue.offer(root); // a list of tree nodes
        qCol.offer(0);     // indices of vertical level
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            Integer col = qCol.poll();
            
            if (!map.containsKey(col)) {
                map.put(col, new ArrayList<Integer>(Arrays.asList(cur.val)));
            } else {
                map.get(col).add(cur.val);
            }
            
            if (cur.left != null) {
                queue.offer(cur.left);
                qCol.offer(col - 1);
            }
            
            if (cur.right != null) {
                queue.offer(cur.right);
                qCol.offer(col + 1);
            }
        }

        for (int n : map.keySet()) {
            res.add(map.get(n));
        }
        
        return res;
    }
    
    /** 
     * BFS with Hash Map + post-processing
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null) {
            return res;
        }
        
        Map<Integer, List<Integer>> map = new HashMap<>();  // order map : bbst
        
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> qCol = new LinkedList<>();
        queue.offer(root);
        qCol.offer(0);
        
        while (!queue.isEmpty()) {
            TreeNode cur = queue.poll();
            Integer col = qCol.poll();
            
            map.putIfAbsent(col, new ArrayList<>());
            map.get(col).add(cur.val);
            
            if (cur.left != null) {
                queue.offer(cur.left);
                qCol.offer(col - 1);
            }
            
            if (cur.right != null) {
                queue.offer(cur.right);
                qCol.offer(col + 1);
            }
        }
        
        for (int i = Collections.min(map.keySet()); i <= Collections.max(map.keySet()); i++){
            res.add(map.get(i));
        }
        
        return res;
    }
}
