/**
 * 20. Valid Parentheses
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 *
 * An input string is valid if:
 * Open brackets must be closed by the same type of brackets.
 * Open brackets must be closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 *
 * Input: s = "()"
 * Output: true
 * Example 2:
 *
 * Input: s = "()[]{}"
 * Output: true
 * Example 3:
 *
 * Input: s = "(]"
 * Output: false
 */
class ValidParentheses {
    // Time: O(n)
    // Space: O(n)
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        
	    for (char c : s.toCharArray()) {
	        if (c == '(')
		        stack.push(')');
	        else if (c == '{')
		        stack.push('}');
	        else if (c == '[')
	    	    stack.push(']');
	        else if (stack.isEmpty() || stack.pop() != c)
	    	    return false;
	    }
	    return stack.isEmpty();
    }
}
