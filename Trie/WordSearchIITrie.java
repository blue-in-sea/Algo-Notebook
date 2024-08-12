/**
 * Word Search II
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those 
 * horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
 *
 * Given words = ["oath","pea","eat","rain"] and board =
 *
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 * Return ["eat","oath"].
 */
class TrieNode {
    boolean isWord;
    Map<Character, TrieNode> children;

    // default constructor
    public TrieNode() {
        children = new HashMap<>();
    }
}

class WordSearchIITrie {
    private char[][] board;
    private int rs;
    private int cs;
    private boolean[][] marked;
    private final int[][] DIRS = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    private TrieNode root;

    public List<String> findWords(char[][] board, String[] words) {
        this.board = board;
        this.rs = board.length;
        this.cs = board[0].length;
        this.marked = new boolean[rs][cs];

        // corner case
        if (board == null || rs == 0 || cs == 0 || words == null || words.length == 0) {
            return new ArrayList<>();
        }

        this.root = new TrieNode();
        buildDict(words);

        Set<String> res = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < rs; i++) {
            for (int j = 0; j < cs; j++) {
                dfs(res, sb, root, i, j);
            }
        }

        return new ArrayList<>(res);
    }

    private void dfs(Set<String> res, StringBuilder sb, TrieNode root, int i, int j) {
        if (i < 0 || i >= rs || j < 0 || j >= cs || marked[i][j]) {
            return;
        }

        char c = board[i][j];
        if (!root.children.containsKey(c)) {
            return;
        }

        sb.append(c);
        root = root.children.get(c);

        if (root.isWord) {
            res.add(new String(sb));
        }

        marked[i][j] = true;
        for (int[] dir : DIRS) {
            int x = i + dir[0];
            int y = j + dir[1];
            dfs(res, sb, root, x, y);
        }
        marked[i][j] = false;
        sb.deleteCharAt(sb.length() - 1);
    }

    private void buildDict(String[] words) {
        for (String word : words) {
            insert(word);
        }
    }

    private void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }
}
