import java.util.*;

public class KnightsandPortals {

    static class State {
        int row, col, steps;
        boolean usedTeleport;

        State(int r, int c, int s, boolean used) {
            this.row = r;
            this.col = c;
            this.steps = s;
            this.usedTeleport = used;
        }
    }

    static int[] dR = {-1, 1, 0, 0};
    static int[] dC = {0, 0, -1, 1};

    public static int shortestPathWithTeleport(char[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<State> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[n][m][2];
        List<int[]> emptyCells = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.') {
                    emptyCells.add(new int[]{i, j});
                }
            }
        }

        queue.add(new State(0, 0, 0, false));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            State curr = queue.poll();

            if (curr.row == n - 1 && curr.col == m - 1) {
                return curr.steps;
            }

            for (int d = 0; d < 4; d++) {
                int nr = curr.row + dR[d];
                int nc = curr.col + dC[d];

                if (isValid(nr, nc, n, m) && grid[nr][nc] != '#') {
                    int teleportUsed = curr.usedTeleport ? 1 : 0;
                    if (!visited[nr][nc][teleportUsed]) {
                        visited[nr][nc][teleportUsed] = true;
                        queue.add(new State(nr, nc, curr.steps + 1, curr.usedTeleport));
                    }
                }
            }

            if (!curr.usedTeleport && grid[curr.row][curr.col] == '.') {
                for (int[] cell : emptyCells) {
                    int tr = cell[0];
                    int tc = cell[1];

                    if (tr == curr.row && tc == curr.col) continue;

                    if (!visited[tr][tc][1]) {
                        visited[tr][tc][1] = true;
                        queue.add(new State(tr, tc, curr.steps + 1, true));
                    }
                }
            }
        }

        return -1;
    }

    private static boolean isValid(int r, int c, int n, int m) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'S', '.', '.', '#'},
            {'#', '.', '#', '.'},
            {'.', '.', '.', '.'},
            {'#', '#', '.', 'E'}
        };

        grid[0][0] = '.';
        grid[3][3] = '.';

        int result = shortestPathWithTeleport(grid);
        System.out.println(result);
    }
}
