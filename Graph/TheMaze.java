class TheMaze {
    // Time: O(m⋅n⋅(m+n)) for matrix n by m
    //   dfs take (m⋅n) stack calls, for each level, the robot can take at most m+n steps
    // Space: O(m⋅n)
    int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int r = maze.length;
        int c = maze[0].length;

        boolean[][] mark = new boolean[r][c];

        return dfs(r, c, maze, start, destination, mark);  
    }

    public boolean dfs(int r, int c, int[][] maze, int[] cur, int[] des, boolean[][] marked) {
        // base case: 
        if (marked[cur[0]][cur[1]]) return false;
        if (cur[0] == des[0] && cur[1] == des[1]) return true;

        marked[cur[0]][cur[1]] = true;
        // traverse the graph in four dircetions 
        for (int k = 0; k < 4; k++) {
            int x = cur[0], y = cur[1];

            // The ball can go through the empty spaces by rolling up, down, left or right, 
            // but it won't stop rolling until hitting a wall. 
            while (inRange(x, y, r, c) && maze[x][y] == 0) {
                x += DIRS[k][0];
                y += DIRS[k][1];
            }
            
            // after hitting the wall, backtrack
            if (dfs(r, c, maze, new int[]{x - DIRS[k][0], y - DIRS[k][1]}, des, marked)) {
                return true;
            }
        }
        return false;
    }

    private boolean inRange(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}
