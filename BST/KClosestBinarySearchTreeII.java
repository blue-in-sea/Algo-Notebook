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

public class KClosestBinarySearchTreeII {
    /**
     * @param root: the given BST
     * @param target: the given target
     * @param k: the given k
     * @return: k values in the BST that are closest to the target
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        
        List<Integer> results = new ArrayList<>();
        if (k == 0 || root == null) return results;

        Deque<TreeNode> lowerStack = getPath(root, target);
        Deque<TreeNode> upperStack = new ArrayDeque<>();
        upperStack.addAll(lowerStack);

        if (target < lowerStack.peekFirst().val) {
            moveLower(lowerStack);
        } else {
            moveUpper(upperStack);
        }

        for (int i = 0; i < k; i++) {
            if (lowerStack.isEmpty() || !upperStack.isEmpty() &&
                    target - lowerStack.peekFirst().val > upperStack.peekFirst().val - target) {
                results.add(upperStack.peekFirst().val);
                moveUpper(upperStack);
            } else {
                results.add(lowerStack.peekFirst().val);
                moveLower(lowerStack);
            }
        }

        return results;
    }

    private Deque<TreeNode> getPath(TreeNode root, double target) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null) {
            stack.offerFirst(root);
            root = target < root.val ? root.left : root.right;
        }
        return stack;
    }

    private void moveUpper(Deque<TreeNode> stack) {
        TreeNode node = stack.peekFirst();

        if (node.right == null) {
            node = stack.pollFirst();
            while (!stack.isEmpty() && stack.peekFirst().right == node) {
                node = stack.pollFirst();
            }
            return;
        }

        node = node.right;
        while (node != null) {
            stack.offerFirst(node);
            node = node.left;
        }
    }

    public void moveLower(Deque<TreeNode> stack) {
        TreeNode node = stack.peekFirst();
        if (node.left == null) {
            node = stack.pollFirst();
            while (!stack.isEmpty() && stack.peekFirst().left == node) {
                node = stack.pollFirst();
            }
            return;
        }
        
        node = node.left;
        while (node != null) {
            stack.offerFirst(node);
            node = node.right;
        }
    }
}
