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

// 1. Build the Trie via HashMap to store a list of children
class TrieNode {
    public boolean isWord;
    public Map<Character, TrieNode> children;

    public TrieNode() {
        this.children = new HashMap<>();
    }
}

class Trie {
    private TrieNode root;

    /** Initialize Trie */
    public Trie() {
        root = new TrieNode();
    }
    
     /** Inserts a word into the trie. */
    public void insert(String word) {
        // corner case
        if (word == null) {
            return;
        }

        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // if new a char, insert it to the trie
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode());
            }
            curr = curr.children.get(c);
        }
        // for (char c : word.toCharArray()) current = current.getChildren().computeIfAbsent(c, n -> new TrieNode()); 
        curr.isWord = true;
    }
    
    /** Returns true if the word is in the trie. */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            // if char does not exist in the trie, return false 
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return curr.isWord;
    }
    
    /** Returns true if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            // if char does not exist in the trie, return false 
            if (!curr.children.containsKey(c)) {
                return false;
            }
            curr = curr.children.get(c);
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie root = new Trie();
 * root.insert(word);
 * boolean param_2 = root.search(word);
 * boolean param_3 = root.startsWith(prefix);
 */

