class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        int[][] dp = new int[n][n];
        int[][] ways = new int[n][n];

        // Start from 'S'
        dp[n - 1][n - 1] = 0;
        ways[n - 1][n - 1] = 1;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {

                if (board.get(i).charAt(j) == 'X') continue;

                if (i == n - 1 && j == n - 1) continue;

                int maxScore = -1;
                int count = 0;

                // Down
                if (i + 1 < n && ways[i + 1][j] > 0) {
                    if (dp[i + 1][j] > maxScore) {
                        maxScore = dp[i + 1][j];
                        count = ways[i + 1][j];
                    } else if (dp[i + 1][j] == maxScore) {
                        count = (count + ways[i + 1][j]) % MOD;
                    }
                }

                // Right
                if (j + 1 < n && ways[i][j + 1] > 0) {
                    if (dp[i][j + 1] > maxScore) {
                        maxScore = dp[i][j + 1];
                        count = ways[i][j + 1];
                    } else if (dp[i][j + 1] == maxScore) {
                        count = (count + ways[i][j + 1]) % MOD;
                    }
                }

                // Diagonal
                if (i + 1 < n && j + 1 < n && ways[i + 1][j + 1] > 0) {
                    if (dp[i + 1][j + 1] > maxScore) {
                        maxScore = dp[i + 1][j + 1];
                        count = ways[i + 1][j + 1];
                    } else if (dp[i + 1][j + 1] == maxScore) {
                        count = (count + ways[i + 1][j + 1]) % MOD;
                    }
                }

                if (count > 0) {
                    int val = board.get(i).charAt(j);
                    if (val != 'E') {
                        maxScore += val - '0';
                    }
                    dp[i][j] = maxScore;
                    ways[i][j] = count;
                }
            }
        }

        if (ways[0][0] == 0) return new int[]{0, 0};

        return new int[]{dp[0][0], ways[0][0]};
    }
}