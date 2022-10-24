import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Task2 {
    static int n;
    static int m;
    static int[][] territory;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String sizes = scanner.nextLine();
        String[] fieldSize = sizes.split(" ");
        n = Integer.parseInt(fieldSize[0]) + 2;
        m = Integer.parseInt(fieldSize[1]) + 2;
        territory = new int[m][n];
        //m  - строки
        //n - столбцы

        for (int i = 1; i < m - 1; i++) {
            String input = scanner.nextLine();
            String[] rowString = input.split(" ");
            for (int j = 1; j < n - 1; j++) {
                territory[i][j] = Integer.parseInt(rowString[j - 1]);
            }
        }
        findNewFields(territory);

        print(territory);
    }

    public static void findNewFields(int[][] territory) {
        int regionNumber = 2;
        int[] leftTop;
        int[] rightBot;
        for (int i = 1; i < territory.length - 1; i++) {
            for (int j = 1; j < territory[i].length - 1; j++) {
                if (territory[i][j] == 1) {
                    findNeighbor(i, j, regionNumber);
                    
                    regionNumber++;
                }
            }
        }
    }

    static void findNeighbor(int y, int x, int regionNumber) {
        ArrayList<Integer[]> coordinates = new ArrayList<>();
        Integer startPoint[] = {y, x};
        coordinates.add(startPoint);
        int border = 1;
        for (int index = 0; index < border; index++) {
            y = coordinates.get(index)[0];
            x = coordinates.get(index)[1];
            for (int i =  Math.max(0, (y - 1)); i <= Math.min((y + 1), (m - 1));  i++) {
                for (int j = Math.max(0, (x - 1)); j <= Math.min(x + 1, n-1); j++) {
                    if (territory[i][j] == 1) {
                        territory[i][j] = regionNumber;
                        Integer[] pair = {i, j};
                        coordinates.add(pair);
                        border++;
                    }
                }
            }
        }



    }

    public static void print(int[][] field) {
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j]);
            }
            System.out.println();
        }
    }
}

class Field {
    int regionNumber;
    int leftTop;
    int rightTop;
    int leftBot;
    int rightBot;
    int fertileCells;
    int badCells;
    int size = fertileCells + badCells;
    double efficiency = fertileCells / size;
}
