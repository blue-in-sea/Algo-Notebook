/**
 * Implement Trie (Prefix Tree)
 *
 * Implement a trie with insert, search, and startsWith methods:
 * API: 
 * insert() - Inserts the string word into the trie.
 * search() - Inserts the string word into the trie.
 * startsWith() - Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 * Example
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // returns true
 * trie.search("app");     // returns false
 * trie.startsWith("app"); // returns true
 * trie.insert("app");
 * trie.search("app");     // returns true
 *
 * Assume:
 * All inputs are consist of lowercase letters a-z.
 * All inputs are guaranteed to be non-empty strings.
 */

// Time: O(W * L) where W is number of words, L is the avg(length of words); In general, L steps for each of the W words
// Space: O(W * L) 

// https://stackoverflow.com/questions/13032116/trie-complexity-and-searching
// HashTable - same worest case complexity, but easier on entire word searching

class TrieNode {
    public boolean isWord;
    public char val;
    public TrieNode[] children = new TrieNode[26]; // Graph representation via Arr Node[a, b, c, d, null, ...] if null meaning no this char as children

    public TrieNode() {}

    public TrieNode(char c) { // This constructor is used to create the curr node in the trie for insertion
        TrieNode node = new TrieNode(); // store the ref of the curr node
        node.val = c;                   // store the val of the curr node
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
            if (curr.children[c - 'a'] == null) {
                curr.children[c - 'a'] = new TrieNode(c);
            }
            curr = curr.children[c - 'a'];
        }
        curr.isWord = true; 
    }
    
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // found a char from word dis not exist in the trie, return false
            if (curr.children[c - 'a'] == null) {
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return curr.isWord;
    }
    
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // found a char from prefix dis not exist in the trie, return false
            if (curr.children[c - 'a'] == null) {
                return false;
            }
            curr = curr.children[c - 'a'];
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
