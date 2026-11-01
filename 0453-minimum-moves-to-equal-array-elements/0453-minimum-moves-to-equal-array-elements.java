class Solution {
    public int minMoves(int[] nums) {
        int minVal = nums[0];
        long sum = 0;

        for (int num : nums) {
            sum += num;
            if (num < minVal) {
                minVal = num;
            }
        }

        return (int) (sum - (long) nums.length * minVal);
    }
}