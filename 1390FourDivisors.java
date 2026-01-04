class Solution {
    public int sumFourDivisors(int[] nums) {
        int totalSum = 0;
        for (int num : nums) {
            int sum = getDivisorSum(num);
            totalSum += sum;
        }
        return totalSum;
    }

    private int getDivisorSum(int num) {
        int count = 0;
        int sum = 0;

        for (int i=1; i*i <= num; i++) {
            if (num % i == 0) {
                int d1 = i;
                int d2 = num/i;

                if(d1 == d2) {
                    count++;
                    sum += d1;
                } else {
                    count+=2;
                    sum += d1+d2;
                }

                if (count > 4) return 0;
            }
        }

        return count == 4 ? sum : 0;
    }
}