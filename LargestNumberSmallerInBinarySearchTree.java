public class TreeNode {

}

public class LargestNumberSmallerInBinarySearchTree {
  public int largestSmaller(TreeNode root, int target) {
    // assume tree is not null and no duplicate keys
    int result = Integer.MIN_VALUE;

    while (root != null){
      if (root.key < target) {
        result = root.key;
        root = root.right;
      } else {
        root = root.left;
      }
    }
    return result;
  }
}
