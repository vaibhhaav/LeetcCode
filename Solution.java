import java.util.*;

public class Solution {
    public int maximizeSquareHoleArea(int n, int m, int[] hBars, int[] vBars) {
        Set<Integer> hSet = new HashSet<>();
        Set<Integer> vSet = new HashSet<>();
        for (int h : hBars) hSet.add(h);
        for (int v : vBars) vSet.add(v);
        int maxH = getMaxConsecutive(hSet);
        int maxV = getMaxConsecutive(vSet);
        int side = Math.min(maxH, maxV);
        return side * side;
    }

    private int getMaxConsecutive(Set<Integer> set) {
        List<Integer> list = new ArrayList<>(set);
        Collections.sort(list);
        int maxLen = 1, curLen = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) == list.get(i - 1) + 1) {
                curLen++;
            } else {
                curLen = 1;
            }
            maxLen = Math.max(maxLen, curLen);
        }
        return maxLen + 1;
    }
}