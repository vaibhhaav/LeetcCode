import java.util.*;

public class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            int t = nums.get(i);
            if ((t & 1) == 0) {
                ans[i] = -1;
                continue;
            }
            int j = -1;
            for (int b = 31; b >= 0; b--) {
                if (((t >> b) & 1) == 1) {
                    int mask = (b == 31) ? -1 : ((1 << b) - 1);
                    if ((t & mask) == mask) {
                        j = b;
                        break;
                    }
                }
            }
            ans[i] = (j == -1) ? -1 : (t ^ (1 << j));
        }
        return ans;
    }
}