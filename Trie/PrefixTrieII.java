/**
 * Implement Trie (Prefix Tree)
 *
 * Implement a trie with insert, search, and startsWith methods:
 * API: 
 * insert() - Inserts the string word into the trie.
 * countWordsEqualTo() - Returns the number of instances of the word in the trie.
 * countWordsStartingWith() - Returns the number of strings in the trie that shared the prefix.
 * erase() - Erases a word from the trie.
 *
 * Example:
 * Input
 * ["Trie", "insert", "insert", "countWordsEqualTo", "countWordsStartingWith", "erase", "countWordsEqualTo",
 * "countWordsStartingWith", "erase", "countWordsStartingWith"]
 * [[], ["apple"], ["apple"], ["apple"], ["app"], ["apple"], ["apple"], ["app"], ["apple"], ["app"]]
 * Output
 * [null, null, null, 2, 2, null, 1, 1, null, 0]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");               // Inserts "apple".
 * trie.insert("apple");               // Inserts another "apple".
 * trie.countWordsEqualTo("apple");    // There are two instances of "apple" so return 2.
 * trie.countWordsStartingWith("app"); // "app" is a prefix of "apple" so return 2.
 * trie.erase("apple");                // Erases one "apple".
 * trie.countWordsEqualTo("apple");    // Now there is only one instance of "apple" so return 1.
 * trie.countWordsStartingWith("app"); // return 1
 * trie.erase("apple");                // Erases "apple". Now the trie is empty.
 * trie.countWordsStartingWith("app"); // return 0
 */

// Time: O(W * L) where W is number of words, L is the avg(length of words); In general, L steps for each of the W words
// Space: O(W * L) 

// https://stackoverflow.com/questions/13032116/trie-complexity-and-searching
// HashTable - same worest case complexity, but easier on entire word searching


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
