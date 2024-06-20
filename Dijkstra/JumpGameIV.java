/**
 * 1345. Jump Game IV
 * Given an array of integers arr, you are initially positioned at the first index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length.
 * i - 1 where: i - 1 >= 0.
 * j where: arr[i] == arr[j] and i != j
 *
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Input: arr = [100,-23,-23,404,100,23,23,23,3,404]
 * Output: 3
 * Explanation: You need three jumps from index 0 --> 4 --> 3 --> 9.
 * Note that index 9 is the last index of the array.
 */
public class JumpGameIV {
    // Algo: BFS 层顺遍历
    // Time: O(n)
    // Space: O(n)
    public int minJumps(int[] arr) {
        if (arr.length <= 1) {
            return 0;
        }

        // build a directed graph
        Map<Integer, List<Integer>> graph = buildGraph(arr);

        Queue<Integer> queue = new ArrayDeque<>(); // store index
        Set<Integer> seen = new HashSet<>();       // dedup & prevent cycling visits

        queue.offer(0);

        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int curIndex = queue.poll();

                // check if reaching the end index
                if (curIndex == arr.length - 1) return step;

                for (int nextIndex : graph.get(arr[curIndex])) {
                    if (seen.contains(nextIndex)) continue;

                    queue.offer(nextIndex);
                    seen.add(nextIndex);
                }

                // clear the list to prevent redundant search
                graph.get(arr[curIndex]).clear();

                // check neighbors
                if (curIndex + 1 < arr.length && !seen.contains(curIndex + 1)) {
                    queue.offer(curIndex + 1);
                    seen.add(curIndex + 1);
                }
                if (curIndex - 1 >= 0 && !seen.contains(curIndex - 1)) {
                    queue.offer(curIndex - 1);
                    seen.add(curIndex - 1);
                }
            }
            step++;
        }
        return 0;
    }

    private Map<Integer, List<Integer>> buildGraph(int[] arr) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            // <key = node, value = a list of indices for the node>
            // where the indices will be the distances from node to a[0]
            graph.computeIfAbsent(arr[i], v -> new ArrayList<>()).add(i);
        }
        return graph;
    }
}
