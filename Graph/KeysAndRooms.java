public class KeysAndRooms {
    // Method 1: BFS
    // Time: O(V + E) on graph traversal
    // Space: O(V) for size of queue & hashset
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // corner case 
        if (rooms == null || rooms.size() == 0) return true;

        Deque<Integer> queue = new ArrayDeque<>();
        Set<Integer> visited = new HashSet<>();

        queue.offer(0);
        visited.add(0);

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            for (int nei : rooms.get(curr)) {
                if (!visited.contains(nei)) {
                    queue.offer(nei);
                    visited.add(nei);
                }
            }
        }

        return visited.size() == rooms.size();
    }
    
    // Method 2: DFS
    // Time: O(V + E) on graph traversal
    // Space: O(V) for size of stack & hashset
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        // corner case 
        if (rooms == null || rooms.size() == 0) return true;

        Set<Integer> visited = new HashSet<>();
        dfs(rooms, 0, visited);
        return visited.size() == rooms.size();
    }

    private void dfs(List<List<Integer>> rooms, int curr, Set<Integer> visited) {
        if (!visited.add(curr)) return;

        for (int nei : rooms.get(curr)) {
            if (!visited.contains(nei)) {
                dfs(rooms, nei, visited);
            }
        }
    }
}
