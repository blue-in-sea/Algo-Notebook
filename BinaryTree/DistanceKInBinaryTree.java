package BinaryTree;

/**
 * 863. All Nodes Distance K in Binary Tree
 * Given the root of a binary tree, the value of a target node target, and an integer k,
 * return an array of the values of all nodes that have a distance k from the target node.
 *
 * You can return the answer in any order.
 *
 *                      3
 *
 *             5                1(*)
 *            (t)
 *        6        2         0        8
 *
 *       x  x   7     4    x  x      x  x
 *             (*)    (*)
 *
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, k = 2
 * Output: [7,4,1]
 * Explanation: The nodes that are a distance 2 from the target node (with value 5) have values 7, 4, and 1.
 *
 * Input: root = [1], target = 1, k = 3
 * Output: []
 */

class DistanceKInBinaryTree {
    // Algo:
    // 1. Build Undirected Graph from Tree with parent pointer (since tree is a direct acyclic graph)
    // 2. then, DFS to calculate the distances

    // Time complexity: O(n) for build graph O(n) & dfs O(n)
    //                  where in each case every node visited once and only once given a treee
    // Space: O(n) for graph storage & dfs stack calls
    Map<Integer, List<Integer>> graph;
    List<Integer> answer;
    Set<Integer> visited;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        graph = new HashMap<>();
        buildGraph(root, null);

        answer = new ArrayList<>();
        visited = new HashSet<>();
        visited.add(target.val);

        dfs(target.val, 0, k);
        return answer;
    }

    // Recursively build the undirected graph from the given binary tree.
    private void buildGraph(TreeNode cur, TreeNode parent) {
        if (cur != null && parent != null) {
            graph.computeIfAbsent(cur.val, k -> new ArrayList<>()).add(parent.val);
            graph.computeIfAbsent(parent.val, k -> new ArrayList<>()).add(cur.val);
        }
        if (cur.left != null) {
            buildGraph(cur.left, cur);
        }
        if (cur.right != null) {
            buildGraph(cur.right, cur);
        }
    }

    private void dfs(int cur, int distance, int k) {
        if (distance == k) {
            answer.add(cur);
            return;
        }

        for (int neighbor : graph.getOrDefault(cur, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                visited.add(neighbor);
                dfs(neighbor, distance + 1, k);
            }
        }
    }
}
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */