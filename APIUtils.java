import java.util.*;

public class APIUtils {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7};
        int[][] grid = new int[][]{{0, 1}, {1, 2}};

        Set<Integer> set = Set.of(1, 2, 3);
        Map<String, String> map = Map.of("k1", "v1", "k2", "v2");

        Map<String, Integer> treeMap = new TreeMap<>((k1, k2) -> k1.compareTo(k2));
        treeMap.put("Apple", 5);
        treeMap.put("Banana", 2);
        treeMap.put("Cherry", 7);

        List<Integer> list = List.of(0, 1, 4, 5);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(list);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);
        maxHeap.addAll(list);

        Random rand = new Random();
        int prob5 = rand.nextInt(5);     // To randomly generate a number from [0, 5)
        int prob7 = (int) (Math.random() * 7);  // To randomly generate a number from [0, 7)

        System.out.println(Arrays.toString(arr));
        System.out.println(Arrays.deepToString(grid));
        System.out.println("=======================");

        System.out.println(set);
        System.out.println("=======================");

        map.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("=======================");

        treeMap.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("=======================");
        printMap(treeMap);
        System.out.println("=======================");

        System.out.println(minHeap);
        System.out.println(maxHeap);
        System.out.println("=======================");

        System.out.println(prob5);
        System.out.println(prob7);
        System.out.println("=======================");

        TreeNode root = dfsArrToTree(arr, 0);
        printPreOrder(root);
        System.out.println();
        TreeNode root2 = bfsArrToTree(arr);
        printPreOrder(root);
        System.out.println();
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(int val) {this.val = val;}
    }
    /**
     *         1
     *       /   \
     *      2     3
     *     / \   / \
     *    4   5 6   7
     */
    // Preorder: 1 2 4 5 3 6 7
    // Array To Tree
    public static TreeNode dfsArrToTree(int[] arr, int idx /* array_index */) {
        if (idx >= arr.length)  return null;

        TreeNode root = new TreeNode(arr[idx]);
        root.left = dfsArrToTree(arr, 2 * idx + 1);
        root.right = dfsArrToTree(arr, 2 * idx + 2);

        return root;
    }

    public static TreeNode bfsArrToTree(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        Queue<TreeNode> queue = new ArrayDeque<>();
        TreeNode root = new TreeNode(arr[0]);
        queue.offer(root);

        // Start from the second element in the array
        for (int i = 1; i < arr.length - 1; i+=2) {
            TreeNode cur = queue.poll();

            cur.left = new TreeNode(arr[i]);
            queue.offer(cur.left);

            cur.right = new TreeNode(arr[i+1]);
            queue.offer(cur.right);
        }
        return root;
    }

    public static void printPreOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        printPreOrder(root.left);
        printPreOrder(root.right);
    }

    public static void printMap(Map<String, Integer> map) {
        for (Map.Entry<String, Integer> e : map.entrySet()) {
            String k = e.getKey();
            Integer v = e.getValue();
            System.out.println(k + ": " + v);
        }
        // map.keySet()
        // map.values()
    }

    // Build Map
    public static Map<Integer, List<Integer>> buildMap(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        
        for (int[] e : edges) {
            Integer u = e[0];
            Integer v = e[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }
        
        return graph;
    }
 }
