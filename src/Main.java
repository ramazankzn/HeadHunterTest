import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static int n;
    static int m;
    static int[][] territory;
    static ArrayList<Field> fields = new ArrayList<>();

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

        double maxEffectivity = 0; // максимальная эффективность
        int maxSize = 0; // максимальный размер

        for (Field field : fields
        ) {
            if (field.getEfficiency() > maxEffectivity && field.getSize() > 1) {
                maxEffectivity = field.getEfficiency();
            }
        }
        for (Field field : fields) {
            if (field.getEfficiency() == maxEffectivity && field.getSize() >=  maxSize && field.getSize() > 1) {
                maxSize = field.size;
            }
        }

        //print(territory);
        System.out.println(maxSize);
    }

    public static void findNewFields(int[][] territory) {
        int regionNumber = 2;
        int top = 0;
        int bottom = 0;
        int left = 0;
        int right = 0;

        for (int i = 1; i < territory.length - 1; i++) {
            for (int j = 1; j < territory[i].length - 1; j++) {
                if (territory[i][j] == 1) {
                    find(i, j, regionNumber);
                    regionNumber++;
                }
            }
        }

        for (int region = 2; region < regionNumber; region++) {
            boolean first = true;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (territory[i][j] == region) {
                        if (first) {
                            top = i;
                            bottom = i;
                            left = j;
                            right = j;
                            first = false;
                        } else {
                            top = Math.min(i, top);
                            bottom = Math.max(i, bottom);
                            left = Math.min(j, left);
                            right = Math.max(j, right);
                        }
                    }
                }
            }
            fields.add(new Field(region, left, right, top, bottom));
        }
    }

    static void find(int y, int x, int regionNumber) {
        ArrayList<Integer[]> coordinates = new ArrayList<>();
        Integer[] startPoint = {y, x};
        coordinates.add(startPoint);
        int counterOfOnes = 1;
        for (int i = 0; i < counterOfOnes; i++) {
            y = coordinates.get(i)[0];
            x = coordinates.get(i)[1];
            territory[y][x] = regionNumber;
            if (territory[y - 1][x - 1] == 1) {
                Integer[] newCoordinates = {y - 1, x - 1};
                coordinates.add(newCoordinates);
                counterOfOnes++;
            }
            if (territory[y - 1][x] == 1) {
                Integer[] newCoordinates = {y - 1, x};
                coordinates.add(newCoordinates);
                counterOfOnes++;
            }
            if (territory[y - 1][x + 1] == 1) {
                Integer[] newCoordinates = {y - 1, x + 1};
                coordinates.add(newCoordinates);
                counterOfOnes++;
            }
            if (territory[y][x - 1] == 1) {
                Integer[] newCoordinates = {y, x - 1};
                coordinates.add(newCoordinates);
                counterOfOnes++;
            }
            if (territory[y][x + 1] == 1) {
                Integer[] newCoordinates = {y, x + 1};
                coordinates.add(newCoordinates);
                counterOfOnes++;
            }
            if (territory[y + 1][x - 1] == 1) {
                Integer[] newCoordinates = {y + 1, x - 1};
                coordinates.add(newCoordinates);
                counterOfOnes++;
            }
            if (territory[y + 1][x] == 1) {
                Integer[] newCoordinates = {y + 1, x};
                coordinates.add(newCoordinates);
                counterOfOnes++;
            }
            if (territory[y + 1][x + 1] == 1) {
                Integer[] newCoordinates = {y + 1, x + 1};
                coordinates.add(newCoordinates);
                counterOfOnes++;
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

    static class Field {
        int regionNumber;
        int left;
        int right;
        int top;
        int bot;
        int fertileCells;
        int badCells;
        int size;
        int efficiency;

        public int getSize() {
            return size;
        }

        public int getEfficiency() {
            return efficiency;
        }

        public Field(int regionNumber, int left, int right, int top, int bot) {
            this.regionNumber = regionNumber;
            this.left = left;
            this.right = right;
            this.top = top;
            this.bot = bot;
            countCells();
        }

        public void countCells() {
            for (int i = top; i <= bot; i++) {
                for (int j = left; j <= right; j++) {
                    if (territory[i][j] > 0) fertileCells++;
                    else badCells++;
                }
            }
            size = fertileCells + badCells;
            efficiency = fertileCells / size;
        }
    }
}


