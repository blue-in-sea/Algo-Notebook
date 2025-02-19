/**
 * 385. Mini Parser
 * Given a string s represents the serialization of a nested list, implement a parser to deserialize it and
 * return the deserialized NestedInteger.
 *
 * Each element is either an integer or a list whose elements may also be integers or other lists.
 *
 * Input: s = "324"
 * Output: 324
 * Explanation: You should return a NestedInteger object which contains a single integer 324.
 *
 * Input: s = "[123,[456,[789]]]"
 * Output: [123,[456,[789]]]
 * Explanation: Return a NestedInteger object containing a nested list with 2 elements:
 * 1. An integer containing value 123.
 * 2. A nested list containing two elements:
 *     i.  An integer containing value 456.
 *     ii. A nested list with one element:
 *          a. An integer containing value 789
 *
 * Constraints:
 * 1 <= s.length <= 5 * 104
 * s consists of digits, square brackets "[]", negative sign '-', and commas ','.
 * s is the serialization of valid NestedInteger.
 * All the values in the input are in the range [-106, 106].
 */

// Algo Explain: https://authorslog.com/@dare2solve/385-mini-parser-aw46KFc7BX
public class NestedInteger {

    // Time: O(n) where n is the length of the input string. Each character in the string is processed once.
    // Space: O(n) 
    //    * recursive call stack O(n) 
    //    * space needed to store the parsed NestedInteger structure.
    public NestedInteger deserialize(String s) {
        Object parsed = parse(s);
        return dfs(parsed);
    }

    private Object parse(String s) {
        if (s.charAt(0) == '[') {
            List<Object> list = new ArrayList<>();
            int start = 1, depth = 0;
            for (int i = 1; i < s.length(); i++) {
                if (s.charAt(i) == '[') depth++;
                else if (s.charAt(i) == ']') depth--;
                else if (s.charAt(i) == ',' && depth == 0) {
                    list.add(parse(s.substring(start, i))); // 当前层，parse前半部分
                    start = i + 1;
                }
            }
            if (start < s.length() - 1) list.add(parse(s.substring(start, s.length() - 1))); // 当前层，加上后半部分
            return list;
        } else {
            return Integer.parseInt(s);
        }
    }

    private NestedInteger dfs(Object input) {
        if (input instanceof Integer) {
            return new NestedInteger((int) input);
        }
        NestedInteger ni = new NestedInteger();
        for (Object obj : (List<Object>) input) {
            ni.add(dfs(obj));
        }
        return ni;
    }

    // Below is the structure of class NestedInteger
    // ==========================================================================
  
    // Fields to store either a single integer or a list of NestedInteger objects
    private Integer value;
    private List<NestedInteger> list;

    // Constructor to initialize an empty nested list
    public NestedInteger() {
        this.list = new ArrayList<>();
        this.value = null;
    }

    // Constructor to initialize a single integer
    public NestedInteger(int value) {
        this.value = value;
        this.list = null;
    }

    // Returns true if this NestedInteger holds a single integer, rather than a nested list
    public boolean isInteger() {
        return value != null;
    }

    // Returns the single integer that this NestedInteger holds, if it holds a single integer
    // Returns null if this NestedInteger holds a nested list
    public Integer getInteger() {
        return value;
    }

    // Sets this NestedInteger to hold a single integer
    public void setInteger(int value) {
        this.value = value;
        this.list = null;
    }

    // Sets this NestedInteger to hold a nested list and adds a nested integer to it
    public void add(NestedInteger ni) {
        if (this.list == null) {
            this.list = new ArrayList<>();
        }
        this.list.add(ni);
    }

    // Returns the nested list that this NestedInteger holds, if it holds a nested list
    // Returns an empty list if this NestedInteger holds a single integer
    public List<NestedInteger> getList() {
        if (this.list == null) {
            return new ArrayList<>();
        }
        return this.list;
    }

    // Override toString() for easier debugging and visualization
    @Override
    public String toString() {
        if (this.isInteger()) {
            return this.value.toString();
        } else {
            return this.list.toString();
        }
    }
}
/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *     // Constructor initializes an empty nested list.
 *     public NestedInteger();
 *
 *     // Constructor initializes a single integer.
 *     public NestedInteger(int value);
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // Set this NestedInteger to hold a single integer.
 *     public void setInteger(int value);
 *
 *     // Set this NestedInteger to hold a nested list and adds a nested integer to it.
 *     public void add(NestedInteger ni);
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
