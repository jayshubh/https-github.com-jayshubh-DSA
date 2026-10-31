class Solution {
    public int uniquePaths(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int MOD = 1_000_000_007;

     
        int[][][] target = new int[m][n][2];

       
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                for (int dir = 0; dir < 2; dir++) {
                    int ni = i + (dir == 1 ? 1 : 0);
                    int nj = j + (dir == 0 ? 1 : 0);

                    if (ni >= m || nj >= n) {
                        target[i][j][dir] = -1;
                    } 
                 
                    else if (grid[ni][nj] == 0) {
                        target[i][j][dir] = ni * n + nj;
                    } 
                   
                    else {
                       
                        int nextDir = 1 - dir;
                        target[i][j][dir] = target[ni][nj][nextDir];
                    }
                }
            }
        }

       
        long[][] dp = new long[m][n];
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] == 0) continue;

                
                for (int dir = 0; dir < 2; dir++) {
                    int dest = target[i][j][dir];
                    if (dest != -1) {
                        int r = dest / n;
                        int c = dest % n;
                        dp[r][c] = (dp[r][c] + dp[i][j]) % MOD;
                    }
                }
            }
        }

        return (int) dp[m - 1][n - 1];
    }
}