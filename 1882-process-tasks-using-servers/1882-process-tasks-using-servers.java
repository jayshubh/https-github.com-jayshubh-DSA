import java.util.*;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // available heap: stores [weight, index]
        PriorityQueue<int[]> available = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        // busy heap: stores [free_time, weight, index]
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            if (a[1] != b[1]) {
                return Long.compare(a[1], b[1]);
            }
            return Long.compare(a[2], b[2]);
        });

        // Initially all servers are available
        for (int i = 0; i < n; i++) {
            available.offer(new int[]{servers[i], i});
        }

        long currentTime = 0;
        for (int j = 0; j < m; j++) {
            currentTime = Math.max(currentTime, j);

            // If no servers are available, jump time to when the next server frees up
            if (available.isEmpty() && !busy.isEmpty()) {
                currentTime = Math.max(currentTime, busy.peek()[0]);
            }

            // Free up servers whose tasks are finished by currentTime
            while (!busy.isEmpty() && busy.peek()[0] <= currentTime) {
                long[] server = busy.poll();
                int weight = (int) server[1];
                int index = (int) server[2];
                available.offer(new int[]{weight, index});
            }

            // Assign the current task (j) to the best available server
            int[] bestServer = available.poll();
            int weight = bestServer[0];
            int index = bestServer[1];

            ans[j] = index;
            long freeTime = currentTime + tasks[j];
            busy.offer(new long[]{freeTime, weight, index});
        }

        return ans;
    }
}