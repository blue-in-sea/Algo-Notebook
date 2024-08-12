class TrieNode {
    boolean isWord;
    Map<Character, TrieNode> children;

    // default constructor
    public TrieNode() {
        children = new HashMap<>();
    }
}

class Solution {
    private TrieNode root;

    public Solution() {
        root = new TrieNode();
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

    private void buildDict(String[] words) {
        for (String word : words) {
            insert(word);
        }
    }

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

        // corner case
        if (board == null || rs == 0 || cs == 0 || words == null || words.length == 0) {
            return new ArrayList<>();
        }

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
}
