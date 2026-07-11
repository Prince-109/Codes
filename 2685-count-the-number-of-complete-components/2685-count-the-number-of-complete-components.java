class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        boolean[] visited = new boolean[n];
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int[] res = new int[2]; 
                dfs(i, graph, visited, res);

                int vertices = res[0];
                int degreeSum = res[1];

                if (degreeSum == vertices * (vertices - 1)) {
                    answer++;
                }
            }
        }

        return answer;
    }

    private void dfs(int node, List<Integer>[] graph, boolean[] visited, int[] res) {
        visited[node] = true;
        res[0]++;                  
        res[1] += graph[node].size();

        for (int next : graph[node]) {
            if (!visited[next]) {
                dfs(next, graph, visited, res);
            }
        }
    }
}