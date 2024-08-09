/**
 * Word Search I (exit?)
 * 
 * Given an m x n grid of characters board and a string word, return true if word exists in the grid.
 * Given words = ["oath","pea","eat","rain"] and board =
 *
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * Return true for ["eat","oath"]
 */

/**
 * DFS + backtracking
 * 
 * Time: O(N⋅3^L) where N is the number of cells in the board and L is the length of the word to be matched.
 *     For the backtracking function, initially we could have at most 4 directions to explore, but further the choices
 *     are reduced into 3 (since we won't go back to where we come from)
 *
 * Space: O(L) for stack calls where L is the length of the word to be matched
 */
class WordSearchDFS {
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

    // dfs version 1
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

    // dfs version 2
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
