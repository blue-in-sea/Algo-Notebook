/**
 * The programming interface for a legacy motor controller accepts commands as binary strings of variable length. The
 * console has a very primitive autocomplete/auto-correct feature: as a new command is entered one character at a time,
 * it will display the previously entered command that shares the longest common prefix. If multiple commands are tied,
 * it displays the most recent one. If there is no previous command that shares a common prefix, it will display the
 * most recent command.
 *
 * Given a sequence of commands entered into the console, for each command, determine the index of the command
 * last displayed by autocomplete a it was entered.
 *
 * n = 6
 * command = ['000', '1110', '01', '001', '110', '11']
 * return array is [0, 1, 1, 1, 2, 5]
 */

/**
 * 题目要求：
 * 1. as a new command is entered one character at a time, it will display the previously entered command
 *    that shares the longest common prefix.
 * 2. If multiple commands are tied, it displays the most recent one.
 * 3. If there is no previous command that shares a common prefix, it will display the most recent command
 */

public class AutoComplete {
    public static List<Integer> autocomplete(List<String> command) {
        List<Integer> resultList = new ArrayList<>();
        Trie trie = new Trie();

        for (int i = 0; i < command.size(); i++) {
            resultList.add(trie.insert(command.get(i), i + 1));

        }
        resultList.set(0, 0);
        return resultList;
    }

    static class Trie {
        private TrieNode root;
        public Trie() {
            root = new TrieNode(1);
        }

        public int insert(String word, int pos) {
            int res = 0;
            TrieNode curr = root;
            boolean flag = true;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (curr.children[c - '0'] == null) {
                    curr.children[c - '0'] = new TrieNode(pos);
                    if (flag) {
                        res = curr.index;
                        flag = false;
                    }
                }

                curr.index = pos;
                curr = curr.children[c - '0'];
            }
            res = flag ? curr.index : res;
            curr.index = pos;
            return res;
        }
    }

    static class TrieNode {
        int index;
        TrieNode[] children = new TrieNode[2];

        TrieNode(int index) {
            this.index = index;
        }
    }
    
    public static void main(String[] args) {
        /**
         * [000, 1110, 01, 001, 110, 11]
         * [0, 1, 1, 1, 2, 5]
         * [1, 0, 1, 1110, 11, 1, 1011, 1110, 0, 110]
         * [0, 1, 1, 3, 4, 5, 6, 4, 2, 8]
         */
        List<String> commandOne = new ArrayList<>();
        commandOne.add("000");
        commandOne.add("1110");
        commandOne.add("01");
        commandOne.add("001");
        commandOne.add("110");
        commandOne.add("11");
        System.out.println(Arrays.toString(commandOne.toArray()));
        System.out.println(Arrays.toString(autocomplete(commandOne).toArray()));

        List<String> commandTwo = new ArrayList<>();
        commandTwo.add("1");
        commandTwo.add("0");
        commandTwo.add("1");
        commandTwo.add("1110");
        commandTwo.add("11");
        commandTwo.add("1");
        commandTwo.add("1011");
        commandTwo.add("1110");
        commandTwo.add("0");
        commandTwo.add("110");
        System.out.println(Arrays.toString(commandTwo.toArray()));
        System.out.println(Arrays.toString(autocomplete(commandTwo).toArray()));
    }
}
