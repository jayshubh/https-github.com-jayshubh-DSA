import java.util.Arrays;

class Solution {
    public String[] createGrid(int m, int n) {
        String[] grid = new String[m];
        

        char[] firstRow = new char[n];
        Arrays.fill(firstRow, '.');
        grid[0] = new String(firstRow);
        
     
        for (int i = 1; i < m; i++) {
            char[] row = new char[n];
            Arrays.fill(row, '#');
            row[n - 1] = '.';
            grid[i] = new String(row);
        }
        
        return grid;
    }
}