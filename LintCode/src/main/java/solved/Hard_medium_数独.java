package solved;

/**
 * https://leetcode-cn.com/problems/sudoku-solver/
 * <p>
 * 编写一个程序，通过已填充的空格来解决数独问题。
 * <p>
 * 一个数独的解法需遵循如下规则：
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 空白格用 '.' 表示。
 *
 * @author guya on 2019/6/25
 */
public class Hard_medium_数独 {

    static final int N = 9;
    static final int M = 3;
    static final char ch_1 = '1';
    static final char ch_9 = '9';
    static final char space = '.';

    /**
     * 这个太慢了，慢的根源在于寻找下一个方格多耗了两个函数的调用
     *
     * @param board
     */
    public void solveSudoku(char[][] board) {
        solveSudoku(board, 0, 0);
    }

    private boolean solveSudoku(char[][] board, int x, int y) {
        if (board[x][y] == space) {
            for (char ch = ch_1; ch <= ch_9; ch++) {
                board[x][y] = ch;
                if (!check(board, x, y)) {
                    continue;
                }

//                print(board);
                if (solveNext(board, x, y)) {
                    return true;
                }
            }
            board[x][y] = space;
            return false;
        }
        return solveNext(board, x, y);
    }

    private boolean solveNext(char[][] board, int x, int y) {
        x++;
        if (x == N) {
            x = 0;
            y = y + 1;
        }
        if (y == N) {
            return true;
        }
        return solveSudoku(board, x, y);
    }

    private boolean check(char[][] board, int x, int y) {
        return checkH(board, x, y) && checkV(board, x, y) && checkB(board, x, y);
    }

    private boolean checkV(char[][] board, int x, int y) {
        for (int i = 0; i < N; i++) {
            if (board[x][i] == board[x][y] && i != y) {
                return false;
            }
        }
        return true;
    }

    private boolean checkH(char[][] board, int x, int y) {
        for (int i = 0; i < N; i++) {
            if (board[i][y] == board[x][y] && i != x) {
                return false;
            }
        }
        return true;
    }

    private boolean checkB(char[][] board, int x, int y) {
        int bx = x / M * M;
        int by = y / M * M;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                if (board[bx + i][by + j] == board[x][y]
                        // mdzz: 这里把by写成了bx
                        && (bx + i != x || by + j != y)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Hard_medium_数独 hardmedium数独 = new Hard_medium_数独();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        hardmedium数独.solveSudoku(board);
        print(board);
    }

    static void print(char[][] board) {
        for (char[] line : board) {
            for (char ch : line) {
                System.out.print(ch + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
