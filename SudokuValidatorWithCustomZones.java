import java.util.*;

public class SudokuValidatorWithCustomZones {
    public static boolean validateSudoku(char[][] board, List<List<int[]>> customZones) {
        return areRowsValid(board) &&
               areColumnsValid(board) &&
               areBoxesValid(board) &&
               areCustomZonesValid(board, customZones);
    }
    private static boolean areRowsValid(char[][] board) {
        for (char[] row : board) {
            if (!hasUniqueValues(row)) {
                return false;
            }
        }
        return true;
    }
    private static boolean areColumnsValid(char[][] board) {
        for (int col = 0; col < 9; col++) {
            char[] column = new char[9];
            for (int row = 0; row < 9; row++) {
                column[row] = board[row][col];
            }
            if (!hasUniqueValues(column)) {
                return false;
            }
        }
        return true;
    }
    private static boolean areBoxesValid(char[][] board) {
        for (int startRow = 0; startRow < 9; startRow += 3) {
            for (int startCol = 0; startCol < 9; startCol += 3) {
                char[] box = new char[9];
                int idx = 0;
                for (int r = 0; r < 3; r++) {
                    for (int c = 0; c < 3; c++) {
                        box[idx++] = board[startRow + r][startCol + c];
                    }
                }
                if (!hasUniqueValues(box)) {
                    return false;
                }
            }
        }
        return true;
    }
    private static boolean areCustomZonesValid(char[][] board, List<List<int[]>> zones) {
        for (List<int[]> zone : zones) {
            if (zone.size() != 9) {
                return false; 
            }
            char[] values = new char[9];
            for (int i = 0; i < 9; i++) {
                int[] position = zone.get(i);
                values[i] = board[position[0]][position[1]];
            }
            if (!hasUniqueValues(values)) {
                return false;
            }
        }
        return true;
    }
    private static boolean hasUniqueValues(char[] values) {
        Set<Character> seen = new HashSet<>();
        for (char ch : values) {
            if (ch != '.') {
                if (seen.contains(ch)) {
                    return false;
                }
                seen.add(ch);
            }
        }
        return true;
    }

    // example input/output 
    public static void main(String[] args) {
        char[][] board = {
            {'5','3','.','.','7','.','.','.','.'},
            {'6','.','.','1','9','5','.','.','.'},
            {'.','9','8','.','.','.','.','6','.'},
            {'8','.','.','.','6','.','.','.','3'},
            {'4','.','8','.','.','3','.','.','1'},
            {'7','.','.','.','2','.','.','.','6'},
            {'.','6','.','.','.','.','2','8','.'},
            {'.','.','.','4','1','9','.','.','5'},
            {'.','.','.','.','8','.','.','7','9'}
        };
        List<List<int[]>> customZones = new ArrayList<>();
        List<int[]> zone1 = Arrays.asList(
            new int[]{0, 0}, new int[]{0, 1}, new int[]{0, 2},
            new int[]{1, 0}, new int[]{1, 1}, new int[]{1, 2},
            new int[]{2, 0}, new int[]{2, 1}, new int[]{2, 2}
        );
        customZones.add(zone1);
        System.out.println("Sudoku Board:");
        for (char[] row : board) {
            for (char ch : row) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        System.out.println("\nCustom Zone 1:");
        for (int[] pos : zone1) {
            System.out.printf("(%d,%d) -> %c\n", pos[0], pos[1], board[pos[0]][pos[1]]);
        }
        boolean isValid = validateSudoku(board, customZones);
        System.out.println("\nIs the Sudoku board valid (including custom zones)? " + isValid);
    }
}
