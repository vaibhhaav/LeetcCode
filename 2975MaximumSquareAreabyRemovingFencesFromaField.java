import java.util.*;

class Solution {
    public int maximizeSquareArea(int m, int n, int[] hFences, int[] vFences) {
        final int MOD = 1_000_000_007;
        List<Integer> h = new ArrayList<>();
        List<Integer> v = new ArrayList<>();
        h.add(1);
        h.add(m);
        v.add(1);
        v.add(n);
        for (int x : hFences) h.add(x);
        for (int x : vFences) v.add(x);
        Collections.sort(h);
        Collections.sort(v);
        Set<Integer> hDiffs = new HashSet<>();
        for (int i = 0; i < h.size(); i++) {
            for (int j = i + 1; j < h.size(); j++) {
                hDiffs.add(h.get(j) - h.get(i));
            }
        }
        long ans = -1;
        for (int i = 0; i < v.size(); i++) {
            for (int j = i + 1; j < v.size(); j++) {
                int diff = v.get(j) - v.get(i);
                if (hDiffs.contains(diff)) {
                    ans = Math.max(ans, (long) diff * diff);
                }
            }
        }
        return ans == -1 ? -1 : (int) (ans % MOD);
    }
}