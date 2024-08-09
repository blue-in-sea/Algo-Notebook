class WordSearchIIDFS {
    private char[][] board;
    private int rs;
    private int cs;
    private boolean[][] marked;
    private final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.rs = board.length;
        this.cs = board[0].length;
        this.marked = new boolean[rs][cs];

        Set<String> res = new HashSet<>();
    
        for (String word : words) {
            reset(marked);

            for (int i = 0; i < rs; i++) {
                for (int j = 0; j < cs; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        if (dfs(i, j, word, 0)) {
                            res.add(word);
                        }
                    }
                }
            }
        }

        return new ArrayList<>(res);
    }

    private boolean dfs(int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        
        if (i < 0 || i >= rs || j < 0 || j >= cs || marked[i][j] || board[i][j] != word.charAt(index)) {
            return false;
        }
        
        marked[i][j] = true;
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];

            if (dfs(x, y, word, index + 1)) {
                return true;
            }
        }
        marked[i][j] = false;
        
        return false;
    }

    private void reset(boolean[][] marked) {
        for (int i = 0; i < rs; i++) {
            for (int j = 0; j < cs; j++) {
                marked[i][j] = false;
            }
        }
    }
}
