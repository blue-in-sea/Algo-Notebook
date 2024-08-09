class WordSearch {
    private char[][] board;
    private int rs;
    private int cs;
    private boolean[][] marked;
    private final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    
    public boolean exist(char[][] board, String word) {
        this.board = board;
        this.rs = board.length;
        this.cs = board[0].length;
        this.marked = new boolean[rs][cs];
        
        
        for (int i = 0; i < rs; i++) {
            for (int j = 0; j < cs; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (dfs(i, j, word, 0)) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }

}
