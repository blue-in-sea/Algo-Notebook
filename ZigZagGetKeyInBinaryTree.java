/* *
 * Get the list of keys in a given binary tree layer by layer in zig-zag order.
 * 
 * Examples
 *         5               layer 0
 *       /   \
 *     3      8            layer 1
 *   /   \      \
 *  1     4      11        layer 2
 *
 * the result is [5, 3, 8, 11, 4, 1]
 */

public class TreeNode {
  int key;
  TreeNode left;
  TreeNode right;
  
  TreeNode(int key) {
    this.key = key;
  }
}

public List<Integer> zigZag(TreeNode root) {
  List<Integer> list = new LinkedList<>();
  if (root == null) return list;
  
  Deque<TreeNode> deque = new LinkedList<>();
  deque.offerFirst(root);
  int layer = 0;
  
  while (!deque.isEmpty()) {
    int size = deque.size();
    
    for (int i = 0; i < size; i++) {
      if (layer == 0) { // even layer, right to left
        TreeNode cur = deque.pollLast();
        list.add(cur.key);
        
        if (cur.right != null)  deque.offerFirst(cur.right);
        if (cur.left != null)  deque.offerFirst(cur.left);
        
      } else { // odd layer, left to right
        TreeNode cur = deque.pollFirst();
        list.add(cur.key);
        
        if (cur.left != null)  deque.offerLast(cur.left);
        if (cur.right != null)  deque.offerLast(cur.right);
      }
    }
            
    layer = 1 - layer;
  }
  return list;
}
