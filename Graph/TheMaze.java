/**
 * 490. The Maze
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through 
 * the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. 
 * 
 * When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [startrow, startcol] and destination = [destinationrow, destinationcol], return true if the ball can stop at the destination, otherwise return false.
 * Input: maze = [[0,0,1,0,0],[0,0,0,0,0],[0,0,0,1,0],[1,1,0,1,1],[0,0,0,0,0]], start = [0,4], destination = [4,4]
 * Output: true
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 */
class TheMaze {
    // DFS
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

    // ********************************************************************************************
    // BFS
    // Time: O(m⋅n⋅(m+n)) for matrix n by m
    // Space: O(m⋅n)
    int[][] DIRS = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        int r = maze.length;
        int c = maze[0].length;

        boolean[][] marked = new boolean[r][c];
        Queue<int[]> queue = new ArrayDeque<>();

        marked[start[0]][start[1]] = true;
        queue.offer(start);

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == destination[0] && cur[1] == destination[1]) return true;

            for (int[] dir : DIRS) {
                int i = cur[0], j = cur[1];
                
                while (inRange(i, j, r, c) && maze[i][j] == 0) {
                    i += dir[0];
                    j += dir[1];
                }

                // back 1 step after hitting the wall 
                int[] nei = new int[]{i -= dir[0], j -= dir[1]};
                if (!marked[nei[0]][nei[1]]) {
                    queue.offer(nei);
                    marked[nei[0]][nei[1]] = true;
                }
            }
        }

        return false; 
    }

    // ********************************************************************************************
    // Utils
    boolean inRange(int x, int y, int r, int c) {
        return x >= 0 && x < r && y >= 0 && y < c;
    }
}
