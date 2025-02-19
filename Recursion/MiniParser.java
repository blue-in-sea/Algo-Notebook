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
    /**
     * Intuition
     * The problem can be approached using recursive parsing. When encountering a list (represented by [ and ]), the 
     * idea is to recursively process its elements. When encountering an integer, it can be directly processed. 
     * Recursion naturally helps with handling nested structures of arbitrary depth.
     * 
     * Approach
     * 1. Use a recursive function parse to handle both lists and integers.
     * 2. If a list is detected (starting with [), create a new NestedInteger object and 
     *    recursively add elements to it until the closing ] is found.
     * 3. If a number is detected, directly return a NestedInteger object holding the number.
     * 4. The main deserialize function initializes the parsing by checking the input string 
     *    and then recursively processes it using the parse function.
     */

    // Time: O(n) where n is the length of the input string. Each character in the string is processed once.
    //    1. parse()
    //    * each char is process only once O(n)
    //    * In the worst case, the input string is fully nested (e.g., [[[[...]]]]), and the recursion depth is proportional to the length of the string
    //    2. dfs()
    //    * recursive call stack O(n)
    // Space: O(n) 
    //    1. parse()
    //    * recursive call stack O(n) 
    //    * space needed to store the parsed NestedInteger structure O(n) 
    //    2. dfs()
    //    * recursive call stack O(n)  
    public NestedInteger deserialize(String s) {
        Object parsed = parse(s);
        return dfs(parsed);
    }

    // Parse() recursively parse the string into a structured format (either a list or an integer).
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

    // dfs() convert ParsedStructure into a NestedInteger object.
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
