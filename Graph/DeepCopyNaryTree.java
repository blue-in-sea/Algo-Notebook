/**
 * 1490. Clone N-ary Tree
 * Given a root of an N-ary tree, return a deep copy (clone) of the tree.
 *
 *                  1
 *        3         2        4
 *    5      6   x     x    x   x
 *
 * Input: root = [1,null,3,2,4,null,5,6]
 * Output copy = [1,null,3,2,4,null,5,6]
 */

/**
 * class Node {
 *     int val;
 *     public List<Integer> children;
 *     public Node(int val) {
 *         this.val = val;
 *         this.children = new ArrayList<Node>();
 *     }
 * }
 */
class DeepCopyNaryTree {
    // 1. DFS
    // Time: O(n) traverse each node in the tree once and only once.
    // Space: O(n) for stack calls & copy tree
    public Node cloneTree(Node root) {
        // Base case: null node.
        if (root == null) {
            return root;
        }

        // First, copy the node itself.
        Node nodeCopy = new Node(root.val);

        // Then, recursively clone the sub-trees.
        for (Node child : root.children) {
            nodeCopy.children.add(this.cloneTree(child));
        }

        return nodeCopy;
    }
    /** Tree is a directed acyclic graph; has a root, with all nodes connected. */
    // 2. BFS
    // see clone graph bfs (DeepCopyUndirectedGraph - section2)
}

