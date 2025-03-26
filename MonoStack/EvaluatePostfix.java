public class EvaluatePostfix {
    public int evaluatePostfix(String expr) {
        if (expr == null || expr.isEmpty()) return 0;

        Stack<Integer> stack = new Stack<>();
        String[] tokens = expr.trim().split("\\s+"); // Split by whitespace

        for (String token : tokens) {
            if (isOperator(token)) {
                int b = stack.pop();
                int a = stack.pop();
                int result = applyOperator(a, b, token.charAt(0));
                stack.push(result);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }

        return stack.pop();
    }

    private boolean isOperator(String s) {
        return s.length() == 1 && "+-*/".contains(s);
    }

    private int applyOperator(int a, int b, char op) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // Integer division
            default: throw new IllegalArgumentException("Invalid operator: " + op);
        }
    }

        // Test cases
    public static void main(String[] args) {
        Solution sol = new Solution();

        String expr1 = "1 7 2 * -";         // Expected: -13 (infix = 1 - 7 * 2) 
        String expr2 = "4 5 * 7 3 / + 0 +"; // Expected: 22 (infix = 4 * 5 + 7 / 3 + 0) = 20 + 2 + 0

        System.out.println("Expr1 result: " + sol.evaluatePostfix(expr1));
        System.out.println("Expr2 result: " + sol.evaluatePostfix(expr2));
    }
}

/*
Postfix Expression: 1 7 2 * -

Token	Action	                                   Stack After Step
1	    Push        	                           [1]
7	    Push                                       [1, 7]
2	    Push        	                           [1, 7, 2]
*	    Pop 2 and 7 → 7 * 2 = 14 → Push result	   [1, 14]
-	    Pop 14 and 1 → 1 - 14 = -13 → Push result  [-13]

Final Stack: [-13]
*/
