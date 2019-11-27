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
public class TopViewOfBinaryTree {
  public List<Integer> topView(TreeNode root) {   
    List<Integer> res = new ArrayList<>();

    if (root == null) {
      return res;
    }

    Map<Integer, List<Integer>> map = new TreeMap<>();

    Queue<TreeNode> queue = new LinkedList<>();
    Queue<Integer> qCol = new LinkedList<>();
    queue.offer(root);
    qCol.offer(0);

    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      int col = qCol.poll();

      map.putIfAbsent(col, new ArrayList<>());
      map.get(col).add(cur.key);

      if (cur.left != null) {
        queue.offer(cur.left);
        qCol.offer(col - 1);
      }

      if (cur.right != null) {
        queue.offer(cur.right);
        qCol.offer(col + 1);
      }
    }

    for (int i = Collections.min(map.keySet()); i <= Collections.max(map.keySet()); i++) {
      res.add(map.get(i).get(0));  // first element in the vertical level list
    }

    return res;
  }
}
