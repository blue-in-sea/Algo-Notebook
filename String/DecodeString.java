/**
 * 394. Decode String
 * Given an encoded string, return its decoded string.
 *
 * Input: s = "3[a]2[bc]"
 * Output: "aaabcbc"
 * Example 2:
 *
 * Input: s = "3[a2[c]]"
 * Output: "accaccacc"
 * Example 3:
 *
 * Input: s = "2[abc]3[cd]ef"
 * Output: "abcabccdcdcdef"
 */
class DecodeString {
    // Method: Stack
    // s = "3[[a2]bc]" -> decode to 'abcbc abcbc abcbc'
    // if c = ']'
    //    1. pop stack and add it to decodedStr util stack top is '['
    //    2. pop '[' from stack 
    //    3. pop and add it to k, util stack top is a digit
    //    4. decode k[decodedString] -> push decodedStr k times to stack (in a reverse order)
    // else 
    //    add the c to stack

    // Time: O(maxK^cntK * n) where K is the digit, and n is the input str len
    // Time: O(maxK * (maxK^cntK * n))
    public String decodeString(String s) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ']') {
                List<Character> decodedString = new ArrayList<>();
                // get the encoded string
                while (stack.peek() != '[') {
                    decodedString.add(stack.pop());
                }
                // pop [ from the stack
                stack.pop();
                int base = 1;
                int k = 0;
                // get the number k
                while (!stack.isEmpty() && Character.isDigit(stack.peek())) {
                    k = k + (stack.pop() - '0') * base;
                    base *= 10;
                }
                // decode k[decodedString], by pushing decodedString k times into stack
                while (k != 0) {
                    for (int j = decodedString.size() - 1; j >= 0; j--) {
                        stack.push(decodedString.get(j));
                    }
                    k--;
                }
            }
            // push the current character to stack
            else {
                stack.push(s.charAt(i));
            }
        } 

        // get the result from stack
        char[] result = new char[stack.size()];
        for (int i = result.length - 1; i >= 0; i--) {
            result[i] = stack.pop();
        }
        return new String(result); 
    }
}
