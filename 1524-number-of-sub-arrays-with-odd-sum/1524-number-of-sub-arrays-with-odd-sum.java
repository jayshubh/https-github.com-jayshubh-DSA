class Solution {
    public int numOfSubarrays(int[] arr) {
        long oddCount = 0, evenCount = 1; // 
        long currentSum = 0;
        long totalOddSubarrays = 0;
        int MOD = 1_000_000_007;

        for (int num : arr) {
            currentSum += num;

            if (currentSum % 2 == 1) {
                totalOddSubarrays += evenCount;
                oddCount++;
            } else {
                totalOddSubarrays += oddCount;
                evenCount++;
            }
            
            totalOddSubarrays %= MOD;
        }

        return (int) totalOddSubarrays;
    }
}