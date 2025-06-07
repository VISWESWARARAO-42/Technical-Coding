public class MatrixIslandsWithDiagonals {
    public static int countIslands(int[][] grid) {
        int count = 0;
        int rows = grid.length, cols = grid[0].length;
        boolean[][] visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) {
                    dfs(grid, visited, i, j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int[][] grid, boolean[][] visited, int x, int y) {
        int[] dx = {-1,-1,-1,0,0,1,1,1};
        int[] dy = {-1,0,1,-1,1,-1,0,1};

        visited[x][y] = true;

        for (int d = 0; d < 8; d++) {
            int nx = x + dx[d], ny = y + dy[d];
            if (isValid(grid, visited, nx, ny)) {
                dfs(grid, visited, nx, ny);
            }
        }
    }

    private static boolean isValid(int[][] grid, boolean[][] visited, int x, int y) {
        int rows = grid.length, cols = grid[0].length;
        return x >= 0 && y >= 0 && x < rows && y < cols &&
               grid[x][y] == 1 && !visited[x][y];
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 0, 0},
            {0, 1, 0, 0},
            {1, 0, 0, 1},
            {0, 0, 1, 1}
        };
        int result = countIslands(grid);
        System.out.println("Number of islands: " + result);
    }
}
