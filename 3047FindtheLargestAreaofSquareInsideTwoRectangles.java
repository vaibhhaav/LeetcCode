class Solution {
    public long largestSquareArea(int[][] bottomLeft, int[][] topRight) {
        int n = bottomLeft.length;
        long ans = 0;
        for (int i = 0; i < n; i++) {
            long a1 = bottomLeft[i][0], b1 = bottomLeft[i][1];
            long c1 = topRight[i][0], d1 = topRight[i][1];
            for (int j = i + 1; j < n; j++) {
                long a2 = bottomLeft[j][0], b2 = bottomLeft[j][1];
                long c2 = topRight[j][0], d2 = topRight[j][1];
                long w = Math.min(c1, c2) - Math.max(a1, a2);
                long h = Math.min(d1, d2) - Math.max(b1, b2);
                if (w > 0 && h > 0) {
                    long side = Math.min(w, h);
                    ans = Math.max(ans, side * side);
                }
            }
        }
        return ans;
    }
}