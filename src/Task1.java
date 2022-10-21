import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String sizes = scanner.nextLine();
        String[] arrString = sizes.split(" ");
        int n = Integer.parseInt(arrString[0]);
        int m = Integer.parseInt(arrString[1]);
        int s = Integer.parseInt(arrString[2]);
        int scanCount = Math.max(n, m);
        int[] firstArray = new int[n];
        int[] secondArray = new int[m];
        int i = 0;
        while (scanCount > 0) {
            scanCount--;
            String[] s1 = scanner.nextLine().split(" ");
            if (s1[0].equals("-")) {
                secondArray[i] = Integer.parseInt(s1[1]);
                i++;
                continue;
            }
            if (s1[1].equals("-")) {
                firstArray[i] = Integer.parseInt(s1[0]);
                i++;
                continue;
            }
            firstArray[i] = Integer.parseInt(s1[0]);
            secondArray[i] = Integer.parseInt(s1[1]);
            i++;
        }

        int result;
        int tempSum = 0;
        int tempResult = 0;
        int counter = 0;

        while (tempSum < s && counter < n) {
            if (tempSum + firstArray[counter] <= s) {
                tempSum += firstArray[counter];
                tempResult++;
                counter++;
            } else break;
        }
        result = tempResult;
        if (counter > 0) counter--;


        for (int j = 0; j < m; j++) {
            if (tempSum + secondArray[j] <= s) {
                tempSum += secondArray[j];
                tempResult++;
            } else {
                while (secondArray[j] + tempSum >= s && counter > 0) {
                    tempSum -= firstArray[counter];
                    counter--;
                    tempResult--;
                }
                if (tempSum + secondArray[j] <= s) {
                    tempSum += secondArray[j];
                    tempResult++;
                } else break;
            }
            if (tempResult > result) {
                result = tempResult;
            }
        }

        System.out.println(result);
    }
}