package TikTok;

//               1
//       2                  4
//    5     3             x     7

// 125 + 123 + 147 = 395
// path val

// 1 - 2 (1) - 5 (12) 0+125 - 3 (12, 125) - 4 (1, 125) - 7 (14, 125)
// 1 [12 14] - 2 [125,5 123,3 14] - [125 123 147] - [123 147], 125 - ... - [] (sum)

import java.util.ArrayDeque;

//               1
//       2                  4
//    5     3             x     7

// 125 + 123 + 147 = 395
// path val

// 1 - 2 (1) - 5 (12) 0+125 - 3 (12, 125) - 4 (1, 125) - 7 (14, 125)
// 1 [12 14] - 2 [125,5 123,3 14] - [125 123 147] - [123 147], 125 - ... - [] (sum)

import java.util.Deque;

public class pathSum {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val) {
            this.val = val;
        }
    }

    private static int pathSum(TreeNode root) {
        int[] total = {0};
        dfs(root, 0, total);
        return total[0];
    }

    private static void dfs(TreeNode root, int path, int[] total) {

        path = root.val + path * 10;

        if (root.left == null && root.right == null) {
            total[0] += path;
        }

        if (root.left != null) dfs(root.left, path, total);
        if (root.right != null) dfs(root.right, path, total);
    }

    static class tuple {
        TreeNode curNode;
        int curVal;

        public tuple(TreeNode node, int curVal) {
            this.curNode = node;
            this.curVal = curVal;

        }
    }

    // breath first search
    private static int bfs(TreeNode root) {
        Deque<tuple> q = new ArrayDeque<>();
        q.offer(new tuple(root, root.val));
        int sum = 0;

        while (!q.isEmpty()) {
            tuple t = q.poll();

            TreeNode node = t.curNode;
            int curVal = t.curVal;

            // leave node
            if (node.left == null && node.right == null) sum += curVal;


            if (node.left != null) {
                q.offer(new tuple(node.left, curVal * 10 + node.left.val));
            }

            if (node.right != null) {
                q.offer(new tuple(node.right, curVal * 10 + node.right.val));
            }

            //System.out.println("++++");
            //System.out.println(node.val);

        }

        return sum;
    }


    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);

        root.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println("bfs:" + bfs(root));
        System.out.println("dfs:" + pathSum(root));
    }
}
