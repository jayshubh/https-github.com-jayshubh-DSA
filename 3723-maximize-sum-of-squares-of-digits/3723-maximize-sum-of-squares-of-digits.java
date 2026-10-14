class Solution {
    public String maxSumOfSquares(int num, int sum) {
   
        if (sum > (long) num * 9 || sum < 1) {
            return "";
        }
        
        StringBuilder sb = new StringBuilder();
        int remainingSum = sum;
        
        for (int i = 0; i < num; i++) {
            int maxDigit = Math.min(9, remainingSum);
            int minNeededForRest = Math.max(0, remainingSum - maxDigit - (num - i - 1) * 9); 
            int d = 9;
            while (d >= 0) {
                if (remainingSum - d <= (num - i - 1) * 9 && remainingSum - d >= 0) {
                    sb.append(d);
                    remainingSum -= d;
                    break;
                }
                d--;
            }
        }
        
        return sb.toString();
    }
}