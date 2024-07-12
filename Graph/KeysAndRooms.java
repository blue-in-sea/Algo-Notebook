/**
 * 841. Keys and Rooms
 * There are n rooms labeled from 0 to n - 1 and all the rooms are locked except for room 0. Your goal is to visit all the rooms. However, you cannot enter a locked room without having its key.
 * When you visit a room, you may find a set of distinct keys in it. Each key has a number on it, denoting which room it unlocks, and you can take all of them with you to unlock the other rooms.
 *
 * Given an array rooms where rooms[i] is the set of keys that you can obtain if you visited room i, 
 * return true if you can visit all the rooms, or false otherwise.
 *
 * Input: rooms = [[1],[2],[3],[]]
 * Output: true
 * Explanation: 
 * We visit room 0 and pick up key 1.
 * We then visit room 1 and pick up key 2.
 * We then visit room 2 and pick up key 3.
 * We then visit room 3.
 * Since we were able to visit every room, we return true.
 *
 * Input: rooms = [[1,3],[3,0,1],[2],[0]]
 * Output: false
 * Explanation: We can not enter room number 2 since the only key that unlocks it is in that room.
 */
public class KeysAndRooms {
    // Graph Traversal: Start from room 0, visited the current rooms, to check the if there is key for next room; 
    //                  return if all rooms visited == input
    
    // Method 1: BFS
    // Time: O(V + E) for visiting all the V, E once
    // Space: O(V) for size of queue, or hashset
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
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

    // **********************************************************
    // Method 2: DFS
    // Time: O(V + E) on graph traversal
    // Space: O(V) for stack calls or hashset
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
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

    // **********************************************************
    // Helper to build graph (not necessary)
    private  Map<Integer, List<Integer>> buildGraph(List<List<Integer>> rooms) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        // initialize node
        for (int i = 0; i < rooms.size(); i++) {
            graph.put(i, new ArrayList<>());
        }
        // add edges 
        for (int i = 0; i < rooms.size(); i++) {
            for (Integer r : rooms.get(i)) {
                graph.get(i).add(r);
            }
        }
        return graph;
    }
}
