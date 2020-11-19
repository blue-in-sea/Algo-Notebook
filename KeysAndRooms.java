public class KeysAndRooms {
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
}
