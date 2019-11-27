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
     * @param root: the root of tree
     * @return: the vertical order traversal
     * 
     * Soln 1: using Tree Map (self-balancing binary search tree)
     * [Time to insert n-th element = O(Log (n-1))]
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        
        if (root == null) {
            return res;
        }
        
        Map<Integer, List<Integer>> map = new TreeMap<>();  // order map : bbst
        
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> qCol = new LinkedList<>();
        queue.offer(root);
        qCol.offer(0);
        
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
     * Soln 2: using Hash Map + post-processing
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
