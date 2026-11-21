import java.util.*;

class Solution {
    private List<Integer>[] origAdj;
    private List<Integer>[] newAdj;
    private int[] newParent;
    private int[] ans;
    private String s;

    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;
        this.s = s;

        origAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            origAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            origAdj[parent[i]].add(i);
        }

        newParent = Arrays.copyOf(parent, n);
        int[] lastSeen = new int[26];
        Arrays.fill(lastSeen, -1);

      
        dfsAssignParents(0, lastSeen);

       
        newAdj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            newAdj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            newAdj[newParent[i]].add(i);
        }

       
        ans = new int[n];
        dfsSubtreeSize(0);
        return ans;
    }

    private void dfsAssignParents(int u, int[] lastSeen) {
        int ch = s.charAt(u) - 'a';
        if (u > 0 && lastSeen[ch] != -1) {
            newParent[u] = lastSeen[ch];
        }

        int prev = lastSeen[ch];
        lastSeen[ch] = u;

        for (int v : origAdj[u]) {
            dfsAssignParents(v, lastSeen);
        }

        lastSeen[ch] = prev;
    }

    private int dfsSubtreeSize(int u) {
        int count = 1;
        for (int v : newAdj[u]) {
            count += dfsSubtreeSize(v);
        }
        ans[u] = count;
        return count;
    }
}