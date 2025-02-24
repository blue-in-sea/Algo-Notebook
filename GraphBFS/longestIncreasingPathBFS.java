class Solution {
    private static final int[][] DIRS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    int row;
    int col;
    
    public int longestIncreasingPath(int[][] matrix) {
        row = matrix.length;
        col = matrix[0].length;
        if (row == 1 && col == 1) {
            return 1;
        }

        int[][] outDegrees = getOutdegree(matrix);

        // 把出度为零的 node 即最大的端点，放入 queue 里进行遍历和拓扑排序
        Queue<Pair> queue = new ArrayDeque<>();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (outDegrees[i][j] == 0) {
                    queue.offer(new Pair(i, j));
                }
            }
        }

        int max = 0;
        while (!queue.isEmpty()) {
            max++; // 层序遍历

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Pair cur = queue.poll();

                for (int[] dir : DIRS) {
                    int prevX = cur.x + dir[0];
                    int prevY = cur.y + dir[1];

                    if (inBound(prevX, prevY) &&  matrix[prevX][prevY] < matrix[cur.x][cur.y]) {
                        outDegrees[prevX][prevY]--;

                        if (outDegrees[prevX][prevY] == 0) {
                            queue.offer(new Pair(prevX, prevY));
                        }
                    
                    }
                }
            }
        }

        return max;
    }

    public int[][] getOutdegree(int[][] matrix) {
        int[][] outDegrees = new int[row][col];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                for (int[] dir : DIRS) {
                    int nextX = i + dir[0];
                    int nextY = j + dir[1];

                    if (inBound(nextX, nextY) && matrix[i][j] < matrix[nextX][nextY]) {
                        outDegrees[i][j]++;
                    }
                }
            }
        }

        return outDegrees;
    }

    private boolean inBound(int x, int y) {
        return x >= 0 && x < row && y >= 0 && y < col;
    }

    class Pair {
        int x;
        int y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
