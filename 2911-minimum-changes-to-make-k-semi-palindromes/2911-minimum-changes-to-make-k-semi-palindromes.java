class Solution {
    private int[][] minCostToSemiPalindrome;

    public int minimumChanges(String s, int k) {
        int n = s.length();
        minCostToSemiPalindrome = new int[n][n];

        // Precompute divisors and minimum changes for all substrings
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                minCostToSemiPalindrome[i][j] = computeMinChanges(s, i, j);
            }
        }

        // dp[i][r] means min changes to partition s[i..n-1] into r parts
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            java.util.Arrays.fill(dp[i], 1_000_000);
        }

        // Base case: 1 part for suffix s[i..n-1]
        for (int i = 0; i < n; i++) {
            dp[i][1] = minCostToSemiPalindrome[i][n - 1];
        }

        // Fill DP table
        for (int r = 2; r <= k; r++) {
            for (int i = 0; i < n; i++) {
                for (int j = i; j < n - 1; j++) {
                    dp[i][r] = Math.min(dp[i][r], minCostToSemiPalindrome[i][j] + dp[j + 1][r - 1]);
                }
            }
        }

        return dp[0][k];
    }

    // Computes the minimum changes to turn s[i..j] into a semi-palindrome
    private int computeMinChanges(String s, int start, int end) {
        int len = end - start + 1;
        int minChanges = 1_000_000;

        // Try all proper divisors of len
        for (int d = 1; d < len; d++) {
            if (len % d == 0) {
                int currentChanges = 0;
                // Check each of the d groups
                for (int offset = 0; offset < d; offset++) {
                    int left = start + offset;
                    int right = start + (len - 1) - (d - 1 - offset);
                    
                    while (left < right) {
                        if (s.charAt(left) != s.charAt(right)) {
                            currentChanges++;
                        }
                        left += d;
                        right -= d;
                    }
                }
                minChanges = Math.min(minChanges, currentChanges);
            }
        }
        return minChanges;
    }
}