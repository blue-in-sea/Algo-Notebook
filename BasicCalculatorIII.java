class BasicCalculatorIII {
    // Time: O(n), Space: O(n)
    // Recursion Depth = MAX nesting level of parentheses.
    public int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }    

        Queue<Character> q = new LinkedList<>();
        for (char c : s.toCharArray()) {
            q.offer(c);
        }

        q.offer('+');
        return cal(q);
    }
    
    private int cal(Queue<Character> q) {
        Stack<Integer> stack = new Stack<>();
        char sign = '+';
        int num = 0;
        
        while (!q.isEmpty()) {
            char c = q.poll();

            if (c == ' ') {
                continue;
            }

            if (Character.isDigit(c)) {
                num = 10 * num + c - '0';

            } else if (c == '(') {
                num = cal(q);  // recursive evaluation for parentheses

            } else {
                eval(stack, num, sign);
                
                num = 0;
                sign = c;

                if (c == ')') {
                    break;
                }
            }
        }
        int sum = 0;
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        return sum;
    }

    private void eval(Stack<Integer> stack, int num, int sign) {
        if (sign == '+') {
            stack.push(num);
        } else if (sign == '-') {
            stack.push(-num);
        } else if (sign == '*') {
            stack.push(stack.pop() * num);
        } else if (sign == '/') {
            stack.push(stack.pop() / num);
        }
    }
}

// Algo
// 1. Scanning the input as a queue of char
// 2. Recursively to handle nested parentheses
// 3. Stack to manage operator precedence (especially * and /)

// Rule: If the char is a
// 1. digit,           build the curr number (num = num * 10 + digit)
// 2. '(',             recursively to evaluate the subexpression
// 3. operator or ),   
//        1) Evaluate num with the previous sign and push result to stack.
//        2) Update sign to the current operator, reset num.
//        3) If it's ')', break and return sum of stack (end of current subexpression)

/*
                           calculate("2*(5+5*2)/3+(6/2+8)")
                                      |
             -----------------------------------------------------
            |                          |                         |
           2 * (...)                 / 3                      + (...)
                 |                                            |
          ----------------                             -----------------
         |                |                             |               |
        5 + (...)        eval = 15                    6 / 2           + 8
             |
         5 * 2 → 10

Subtree Results:
---------------
5 + 10 = 15
2 * 15 = 30
30 / 3 = 10
6 / 2 = 3
3 + 8 = 11
10 + 11 = ✅ 21

*/
