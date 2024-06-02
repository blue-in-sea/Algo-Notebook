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

