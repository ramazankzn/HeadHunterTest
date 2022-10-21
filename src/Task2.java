import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sizes = scanner.nextLine();
        String[] fieldSize = sizes.split(" ");
        int n = Integer.parseInt(fieldSize[0]);
        int m = Integer.parseInt(fieldSize[1]);
        int[][] field = new int[n][m];

        for (int i = 0; i < n; i++) {
            String input = scanner.nextLine();
            String[] rowString = input.split(" ");
            for (int j = 0; j < m; j++) {
                field[i][j] = Integer.parseInt(rowString[j]);
            }
        }



    }
}
