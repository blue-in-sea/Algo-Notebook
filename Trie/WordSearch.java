class WordSearch {
    private char[][] board;
    private int rs;
    private int cs;
    private boolean[][] marked;
   

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

    // version 2
    private boolean dfs(int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        
        if (i < 0 || i >= rs || j < 0 || j >= cs || marked[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }
        
        marked[i][j] = true;
        
        if (dfs(i + 1, j, word, index + 1) || 
            dfs(i - 1, j, word, index + 1) || 
            dfs(i, j + 1, word, index + 1) || 
            dfs(i, j - 1, word, index + 1)) {
            return true;
        }
        
        marked[i][j] = false;
        return false;
    }
}
