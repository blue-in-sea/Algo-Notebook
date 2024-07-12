/**
 * 505. The Maze II
 * There is a ball in a maze with empty spaces (represented as 0) and walls (represented as 1). The ball can go through
 * the empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall.
 * When the ball stops, it could choose the next direction.
 *
 * Given the m x n maze, the ball's start position and the destination, where start = [sx, sy] and destination = [ex, ey],
 * return the shortest distance for the ball to stop at the destination.
 *
 * If the ball cannot stop at destination, return -1.
 *
 * Input: start = [0,4], destination = [4,4]
 * maze = [
 *  [0,0,1,0,0],
 *  [0,0,0,0,0],
 *  [0,0,0,1,0],
 *  [1,1,0,1,1],
 *  [0,0,0,0,0]
 * ]
 *
 * Output: 12
 * Explanation: One possible way is : left -> down -> left -> down -> right -> down -> right.
 * The length of the path is 1 + 1 + 3 + 1 + 2 + 2 + 2 = 12.
 */
public class MazeII {
    // Time: O(MN logMN) where maze[][] is M by N, and insertion/polling of PQ is log(MN)
    // Space: O(MN) distance array

    private final int[][] dirs={{0,1}, {0,-1}, {-1,0}, {1,0}};

    public int shortestDistance(int[][] maze, int[] start, int[] end) {
        int[][] distance = new int[maze.length][maze[0].length];

        for (int[] row: distance) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        distance[start[0]][start[1]] = 0;
        bfs(start, maze, distance);

        return distance[end[0]][end[1]] == Integer.MAX_VALUE ? -1 : distance[end[0]][end[1]];
    }

    // dijkstra: find the shortest path
    private void bfs(int[] start, int[][] maze, int[][] distance) {
        PriorityQueue<Node> queue = new PriorityQueue<>((n1, n2) -> n1.d - n2.d);
        queue.offer(new Node(start[0], start[1], 0));

        while (!queue.isEmpty()) {
            Node curr = queue.poll();

            if (distance[curr.x][curr.y] < curr.d) {
                continue;
            }

            // traverse the child node
            for (int[] dir : dirs) {
                int nextX = curr.x + dir[0];
                int nextY = curr.y + dir[1];
                int step = 0;

                while (canGo(nextX, nextY, maze)) {
                    nextX += dir[0];
                    nextY += dir[1];
                    step++;
                }

                if (distance[curr.x][curr.y] + step < distance[nextX - dir[0]][nextY - dir[1]]) {
                    // if reachable, fill the distance aways
                    distance[nextX - dir[0]][nextY - dir[1]] = distance[curr.x][curr.y] + step;
                    queue.offer(new Node(nextX - dir[0], nextY - dir[1], distance[nextX - dir[0]][nextY - dir[1]]));
                }
            }
        }
    }

    private boolean canGo(int x, int y, int[][]maze) {
        return x >= 0 && y >= 0 && x < maze.length && y < maze[0].length && maze[x][y] == 0;
    }

    class Node {
        int x;
        int y;
        int d;
        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}
