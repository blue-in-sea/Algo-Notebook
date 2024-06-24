class RobotBoundedInCircle {
    // Time:  O(N) where N is a number of instructions to parse.
    // Space: O(1) mathcal O(1) because the array directions contains only 4 elements.

    final int[][] DIRS = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public boolean isRobotBounded(String instructions) {
        // Initial position is in the center
        int x = 0, y = 0;
        // facing north
        int idx = 0;

        for (char i : instructions.toCharArray()) {
            if (i == 'L') {
                idx = (idx + 3) % 4;
            } else if (i == 'R') {
                idx = (idx + 1) % 4;
            } else {
                x += DIRS[idx][0];
                y += DIRS[idx][1];   
            }
        }

        // after one cycle:
        // 1. robot returns into initial position, or 
        // 2. robot doesn't face north
        return (x == 0 && y == 0) || (idx != 0);  
    }
}
