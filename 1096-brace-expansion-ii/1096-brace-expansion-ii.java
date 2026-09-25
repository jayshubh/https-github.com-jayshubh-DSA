import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Stack<Set<String>> operands = new Stack<>();
        Stack<Character> operators = new Stack<>();
        
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            
            if (Character.isLetter(c)) {
                // Read continuous letters as a single operand
                StringBuilder sb = new StringBuilder();
                while (i < expression.length() && Character.isLetter(expression.charAt(i))) {
                    sb.append(expression.charAt(i++));
                }
                i--; // Move index back after inner loop overshoot
                
                Set<String> set = new HashSet<>();
                set.add(sb.toString());
                
                // If previous token was an operand/closing brace, implicit concatenation occurs
                if (i > sb.length() - 1 && (Character.isLetter(expression.charAt(i - sb.length())) || expression.charAt(i - sb.length()) == '}')) {
                    operators.push('.');
                }
                operands.push(set);
            } else if (c == '{') {
                // If '{' follows an operand or '}', it implies concatenation ('.' operator)
                if (i > 0 && (Character.isLetter(expression.charAt(i - 1)) || expression.charAt(i - 1) == '}')) {
                    operators.push('.');
                }
                operators.push('{');
            } else if (c == '}') {
                // Evaluate until matching '{'
                while (!operators.isEmpty() && operators.peek() != '{') {
                    evaluate(operands, operators.pop());
                }
                operators.pop(); // Remove '{'
            } else if (c == ',') {
                // Evaluate top operators before handling union
                while (!operators.isEmpty() && operators.peek() != '{') {
                    evaluate(operands, operators.pop());
                }
                operators.push(',');
            }
        }
        
        // Process remaining operators
        while (!operators.isEmpty()) {
            evaluate(operands, operators.pop());
        }
        
        // Sort the unique words lexicographically
        List<String> result = new ArrayList<>(operands.pop());
        Collections.sort(result);
        return result;
    }
    
    private void evaluate(Stack<Set<String>> operands, char op) {
        Set<String> set2 = operands.pop();
        Set<String> set1 = operands.pop();
        Set<String> res = new HashSet<>();
        
        if (op == '.') { // Concatenation (Cartesian product)
            for (String s1 : set1) {
                for (String s2 : set2) {
                    res.add(s1 + s2);
                }
            }
        } else if (op == ',') { // Union
            res.addAll(set1);
            res.addAll(set2);
        }
        
        operands.push(res);
    }
}