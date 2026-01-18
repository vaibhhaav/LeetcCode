import java.util.*;

class Solution {
    public int largestMagicSquare(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] row = new int[m][n + 1];
        int[][] col = new int[m + 1][n];
        int[][] d1 = new int[m + 1][n + 1];
        int[][] d2 = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                row[i][j + 1] = row[i][j] + grid[i][j];
                col[i + 1][j] = col[i][j] + grid[i][j];
                d1[i + 1][j + 1] = d1[i][j] + grid[i][j];
                d2[i + 1][j] = d2[i][j + 1] + grid[i][j];
            }
        }
        for (int k = Math.min(m, n); k >= 1; k--) {
            for (int r = 0; r + k <= m; r++) {
                for (int c = 0; c + k <= n; c++) {
                    int target = row[r][c + k] - row[r][c];
                    boolean ok = true;
                    for (int i = 0; i < k && ok; i++) {
                        int rs = row[r + i][c + k] - row[r + i][c];
                        if (rs != target) ok = false;
                    }
                    for (int j = 0; j < k && ok; j++) {
                        int cs = col[r + k][c + j] - col[r][c + j];
                        if (cs != target) ok = false;
                    }
                    if (!ok) continue;
                    int diagMain = d1[r + k][c + k] - d1[r][c];
                    if (diagMain != target) continue;
                    int diagAnti = d2[r + k][c] - d2[r][c + k];
                    if (diagAnti != target) continue;
                    return k;
                }
            }
        }
        return 1;
    }
}