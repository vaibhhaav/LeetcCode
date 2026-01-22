class Solution {
    public int minimumPairRemoval(int[] nums) {
        int operations = 0;
        while (!isSorted(nums)) {
            int idx = findMinPairIndex(nums);
            nums = merge(nums, idx);
            operations++;
        }
        return operations;
    }

    private boolean isSorted(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) return false;
        }
        return true;
    }

    private int findMinPairIndex(int[] nums) {
        int minSum = Integer.MAX_VALUE;
        int index = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            int sum = nums[i] + nums[i + 1];
            if (sum < minSum) {
                minSum = sum;
                index = i;
            }
        }
        return index;
    }

    private int[] merge(int[] nums, int idx) {
        int[] result = new int[nums.length - 1];
        int pos = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i == idx) {
                result[pos++] = nums[i] + nums[i + 1];
                i++;
            } else {
                result[pos++] = nums[i];
            }
        }
        return result;
    }
}