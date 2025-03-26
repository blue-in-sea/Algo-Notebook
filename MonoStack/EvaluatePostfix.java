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
}
