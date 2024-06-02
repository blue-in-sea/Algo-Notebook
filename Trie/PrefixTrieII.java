
class TrieNode {
    public boolean isWord;
    Map<Character, TrieNode> children;

    int cntStarts = 0; // counting the number of words that start with a given prefix (at the given node)
    int cntEnds = 0; // counting the total number of words that match a given word (at the given node)

    // default constructor
    public TrieNode() { 
        children = new HashMap<>();                  
    }
}

class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // insert a new char into the trie
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
            curr.cntStarts++; // whenever visit a chid, we increment the cntStats
        }
        curr.cntEnds++; // whenever finish inserting a word, we increment the cntEnds
        curr.isWord = true;
    }
    
    public int countWordsEqualTo(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // found char does not exist, return 0
           if (!curr.children.containsKey(c)) {
                return 0;
            }
            curr = curr.children.get(c);
        }
        return curr.cntEnds;
    }
    
    public int countWordsStartingWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // found char does not exist, return 0
            if (!curr.children.containsKey(c)) {
                return 0;
            }
            curr = curr.children.get(c);
        }
        return curr.cntStarts;
    }
    
    public void erase(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            curr = curr.children.get(c);
            curr.cntStarts--;
        }
        curr.cntEnds--;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
