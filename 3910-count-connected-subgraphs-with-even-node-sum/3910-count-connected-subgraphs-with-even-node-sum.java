import java.util.*;

class Solution {
    public int evenSumSubgraphs(int[] nums, int[][] edges) {
        int n = nums.length;

        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int count = 0;

        // iterate all non-empty subsets
        for (int mask = 1; mask < (1 << n); mask++) {
            int sum = 0;
            List<Integer> nodes = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += nums[i];
                    nodes.add(i);
                }
            }

            // sum must be even
            if (sum % 2 != 0) continue;

            // check connected
            if (isConnected(mask, graph, n)) {
                count++;
            }
        }

        return count;
    }

    private boolean isConnected(int mask, List<Integer>[] graph, int n) {
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();

        int start = -1;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0) {
                start = i;
                break;
            }
        }

        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nei : graph[node]) {
                if ((mask & (1 << nei)) != 0 && !visited[nei]) {
                    visited[nei] = true;
                    q.offer(nei);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) != 0 && !visited[i]) {
                return false;
            }
        }

        return true;
    }
}