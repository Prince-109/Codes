class Solution {
    public int uniqueXorTriplets(int[] nums) {
        final int MAX = 2048;

        boolean[][] dp = new boolean[4][MAX];
        dp[0][0] = true;

        boolean[] singleValues = new boolean[MAX];

        for (int num : nums) {
            singleValues[num] = true;

            for (int cnt = 2; cnt >= 0; cnt--) {
                for (int xor = 0; xor < MAX; xor++) {
                    if (dp[cnt][xor]) {
                        dp[cnt + 1][xor ^ num] = true;
                    }
                }
            }
        }

        boolean[] seen = new boolean[MAX];

        for (int xor = 0; xor < MAX; xor++) {
            if (dp[3][xor]) {
                seen[xor] = true;
            }
        }

        for (int v = 0; v < MAX; v++) {
            if (singleValues[v]) {
                seen[v] = true;
            }
        }

        int ans = 0;
        for (boolean b : seen) {
            if (b) ans++;
        }

        return ans;
    }
}