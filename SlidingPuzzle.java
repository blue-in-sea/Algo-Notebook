public class SlidingPuzzle {
    private final int[] DIRS = {-1, 1, -3, 3};
    public int slidingPuzzle(int[][] board) {
        // corner case check 
        StringBuilder sb = concertHelper(board);
        
        String start = sb.toString();
        String end = "123450";
        
        Deque<String> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();
        
        queue.offer(start);
        visited.add(start);
        
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                String curr = queue.poll();
                if (curr.equals(end)) return step;
                
                int pos = curr.indexOf("0");
                for (int dir : DIRS) {
                    int idx = pos + dir;
                    if (notInBound(pos, idx, dir)) continue;
                    
                    String next = generateNextBoard(curr, pos, idx);
                    if (visited.contains(next)) continue;
                    queue.offer(next);
                    visited.add(next);
                }
            }
            step++;
        }
        
        return -1; 
    } 
    
    private String generateNextBoard(String str, int pos, int idx) {
        StringBuilder sb = new StringBuilder(str);
        // swap two char and since string is immutable 
        // we do not need worry the intermedia state 
        sb.setCharAt(pos, str.charAt(idx));
        sb.setCharAt(idx, str.charAt(pos));
        return sb.toString();
    }
    
    private boolean notInBound(int pos, int idx, int dir) {
        return (idx < 0 || idx > 5 || (pos == 2 && dir == 1) || (pos == 3 && dir == -1));
    }
    
    private StringBuilder concertHelper(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                sb.append(matrix[i][j]);
            }
        }
        
        return sb;
    }
}
