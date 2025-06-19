import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int answer = 0;
    static int[] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        board = new int[N];

        check(0, N);

        System.out.println(answer);
    }

    public static void check(int row, int N) {
        if (row == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            board[row] = i;
            if (canMake(row)) {
                check(row + 1, N);
            }
        }
    }

    public static boolean canMake(int row) {
        for (int i = 0; i < row; i++) {
            if (board[i] == board[row] || Math.abs(board[i] - board[row]) == row - i) {
                return false;
            }
        }
        return true;
    }
}