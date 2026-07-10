class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        java.util.Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int[] pos = new int[n];
        int[] val = new int[n];
        for (int i = 0; i < n; i++) {
            val[i] = arr[i][0];
            pos[arr[i][1]] = i;
        }

        int[] comp = new int[n];
        int cid = 0;
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (val[i] - val[i - 1] > maxDiff) cid++;
            comp[i] = cid;
        }

        int[] reach = new int[n];
        int r = 0;
        for (int i = 0; i < n; i++) {
            while (r + 1 < n && val[r + 1] - val[i] <= maxDiff) r++;
            reach[i] = r;
        }

        int LOG = 18;
        int[][] up = new int[LOG][n];

        for (int i = 0; i < n; i++) up[0][i] = reach[i];

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int u = pos[queries[i][0]];
            int v = pos[queries[i][1]];

            if (u == v) {
                ans[i] = 0;
                continue;
            }

            int left = Math.min(u, v);
            int right = Math.max(u, v);

            if (comp[left] != comp[right]) {
                ans[i] = -1;
                continue;
            }

            int cur = left;
            int steps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                int nxt = up[k][cur];
                if (nxt < right) {
                    cur = nxt;
                    steps += 1 << k;
                }
            }

            ans[i] = steps + 1;
        }

        return ans;
    }
}