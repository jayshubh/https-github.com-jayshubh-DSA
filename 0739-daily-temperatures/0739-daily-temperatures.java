import java.util.Stack;

class Solution {
    public int[] dailyTemperatures(int[] arr) {
        int[] a = new int[arr.length];
        Stack<Integer> s = new Stack<>();


        for(int i = 0; i<arr.length;i++){
            while(!s.isEmpty() && arr[i] > arr[s.peek()]){
                a[s.peek()] = i-s.peek();
                s.pop();
            }
            s.push(i);
        }
        return a;    
    }
}