import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {
        long ans = 0;
        long cost = 0;

        Deque<long[]> dq = new ArrayDeque<>();
        
        int n = nums.length;
        int j = n - 1;
        
        for (int i = n - 1; i >= 0; --i) {
            int num = nums[i];
            long count = 1;
            
            while (!dq.isEmpty() && dq.peekLast()[0] < num) {
                long[] prev = dq.pollLast();
                long nextNum = prev[0];
                long nextCount = prev[1];
                
                count += nextCount;
                cost += (long) (num - nextNum) * nextCount;
            }
            
            dq.offerLast(new long[]{num, count});

            while (cost > k) {
                long[] front = dq.peekFirst();
                long rightmostNum = front[0];
                long rightmostCount = front[1];
                
                cost -= (long) (rightmostNum - nums[j]);
                j--;
                
                if (rightmostCount > 1) {
                    dq.pollFirst();
                    dq.offerFirst(new long[]{rightmostNum, rightmostCount - 1});
                } else {
                    dq.pollFirst();
                }
            }
            
            ans += (j - i + 1);
        }
        
        return ans;
    }
}