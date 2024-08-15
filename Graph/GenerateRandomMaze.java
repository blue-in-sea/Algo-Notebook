/**
 * Generate Random Maze
 * Randomly generate a maze of size N * N (where N = 2K + 1) whose corridor and wall’s width are both 1 cell. For
 * each pair of cells on the corridor, there must exist one and only one path between them. (Randomly means that the
 * solution is generated randomly, and whenever the program is executed, the solution can be different.). The wall is
 * denoted by 1 in the matrix and corridor is denoted by 0.
 *
 * 随机生成一个大小为 N * N（其中 N = 2K + 1）的迷宫，其走廊和墙壁的宽度均为 1 个单元格。对于走廊上的每一对单元格，它们之间必须存在且只有一条路径。 
 * （随机是指解是随机生成的，每次执行程序时，解都可以不同。）。矩阵中墙壁用 1 表示，走廊用 0 表示。
 * 
 * Assumptions
 * N = 2K + 1 and K >= 0
 * the top left corner must be corridor
 * there should be as many corridor cells as possible
 * for each pair of cells on the corridor, there must exist one and only one path between them
 *
 * N = 5, one possible maze generated is
 *
 *         0  0  0  1  0
 *
 *         1  1  0  1  0
 *
 *         0  1  0  0  0
 *
 *         0  1  1  1  0
 *
 *         0  0  0  0  0
 */
public class GenerateRandomMaze {
    public int[][] maze(int n) {
        // Assume n = 2k + 1, where k >= 0
        int[][] maze = new int[n][n];
        // initialize the matrix as only (0, 0) is corridor
        // other cells are all wall in the beginning
        // later we are trying to break the wall from corridor
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    maze[i][j] = 0;
                } else {
                    maze[i][j] = 1;
                }
            }
        }
        generate(maze, 0, 0);
        return maze;
    }

    private void generate(int[][] maze, int x, int y) {
        DIRS[] dirs = DIRS.values();
        shuffle(dirs);

        for (DIRS dir : dirs) {
            int midX = dir.moveX(x, 1);
            int midY = dir.moveY(y, 1);

            int nextX = dir.moveX(x, 2);  // x move in four directions
            int nextY = dir.moveY(y, 2);  // y move in four directions

            if (inValidWall(maze, nextX, nextY)) {
                maze[midX][midY] = 0;
                maze[nextX][nextY] = 0;
                generate(maze, nextX, nextY);
            }
        }
    }

    private boolean inValidWall(int[][] maze, int x, int y) {
        int rows = maze.length;
        int cols = maze[0].length;

        return x >= 0 && x < rows && y >= 0 && y < cols && maze[x][y] == 1;
    }

    private void shuffle(DIRS[] dirs) {
        for (int i = 0; i < dirs.length; i++) {
            int index = (int) (Math.random() * (dirs.length - i));
            DIRS tmp = dirs[i];
            dirs[i] = dirs[i + index];
            dirs[i + index] = tmp;
        }
    }

    // enum is a good way for representing a set of predefined constant
    enum DIRS {
        Left(-1, 0), Right(1, 0), Down(0, -1), Up(0, 1);

        int deltaX;
        int deltaY;

        DIRS (int deltaX, int deltaY) {
            this.deltaX = deltaX;
            this.deltaY = deltaY;
        }

        // move certain times of deltaX
        public int moveX(int x, int times) {
            return x + times * deltaX;
        }

        // move certain times of deltaY
        public int moveY(int y, int times) {
            return y + times * deltaY;
        }
    }
}

