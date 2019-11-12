import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;

class TreeNode {
  int key;
  TreeNode left;
  TreeNode right;
  
  TreeNode(int key) {
    this.key = key;
  }
}

public class KClosestBST {
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    Deque<Integer> dq = new ArrayDeque<>();    
    inOrder(root, dq);
    
    while (dq.size() > k) {
      if (Math.abs(dq.peekFirst() - target) > Math.abs(dq.peekLast() - target)) {
        dq.pollFirst();
      } else {
        dq.pollLast();
      }
    }
    
    return new ArrayList<>(dq);
  }

  private void inOrder(TreeNode root, Deque<Integer> dq) {
    if (root == null) return;

    inOrder(root.left, dq);
    dq.add(root.key);
    inOrder(root.right, dq);
  }
}
