import java.util.*;

class Solution {
    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = -1;
            int target = nums.get(i);
            for (int x = 0; x <= target; x++) {
                if ((x | (x + 1)) == target) {
                    ans[i] = x;
                    break;
                }
            }
        }
        return ans;
    }
}