import java.util.Stack;

class Solution {
    public String removeKdigits(String num, int k) {
        int len = num.length();
      
        if (k == len) return "0";
        
        Stack<Character> stack = new Stack<>();
        
        for (int i = 0; i < len; i++) {
            char current = num.charAt(i);
            
           
            while (!stack.isEmpty() && k > 0 && stack.peek() > current) {
                stack.pop();
                k--;
            }
            stack.push(current);
        }
        
      
        while (k > 0 && !stack.isEmpty()) {
            stack.pop();
            k--;
        }
        
       
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        sb.reverse();
        
 
        while (sb.length() > 1 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }
        
        return sb.toString();
    }
}