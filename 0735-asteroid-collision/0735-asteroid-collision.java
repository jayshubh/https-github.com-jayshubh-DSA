import java.util.Stack;

class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();

        for (int curr : asteroids) {
            boolean destroyed = false;

            while (!stack.isEmpty() && stack.peek() > 0 && curr < 0) {
                if (stack.peek() < Math.abs(curr)) {
                   
                    stack.pop();
                } else if (stack.peek() == Math.abs(curr)) {
                  
                    stack.pop();
                    destroyed = true;
                    break;
                } else {
                   
                    destroyed = true;
                    break;
                }
            }

           
            if (!destroyed) {
                stack.push(curr);
            }
        }

     
        int[] ans = new int[stack.size()];
        for (int i = stack.size() - 1; i >= 0; i--) {
            ans[i] = stack.pop();
        }

        return ans;
    }
}