import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {
    public int maxSelectedElements(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, Integer> dp = new HashMap<>();
        int ans = 0;

        for (int x : nums) {
            int nextChain = dp.getOrDefault(x, 0) + 1;
            int currChain = dp.getOrDefault(x - 1, 0) + 1;

            int updatedNext = Math.max(dp.getOrDefault(x + 1, 0), nextChain);
            int updatedCurr = Math.max(dp.getOrDefault(x, 0), currChain);

            dp.put(x + 1, updatedNext);
            dp.put(x, updatedCurr);

            ans = Math.max(ans, Math.max(updatedCurr, updatedNext));
        }

        return ans;
    }
}