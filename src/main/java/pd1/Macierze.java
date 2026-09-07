package pd1;

import java.util.Scanner;

public class Macierze {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Podaj 9 liczb");
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Macierz:");
        printMatrix(matrix);


        int[][] matrixTransposition = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrixTransposition[j][i] = matrix[i][j];
            }
        }
        System.out.println("Transpozycja macierzy:");
        printMatrix(matrixTransposition);

        System.out.println("Obrót oryginalnego macierza o 90° w prawo");
        int[][] rotatedMatrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                rotatedMatrix[j][2 - i] = matrix[i][j];
            }
        }
        printMatrix(rotatedMatrix);

        System.out.println("Suma wierszy");
        for (int i = 0; i < 3; i++) {
            int rowSum = 0;
            for (int j = 0; j < 3; j++) {
                rowSum = rowSum + matrix[i][j];
            }
            System.out.print(rowSum + " ");
        }
        System.out.println();


        System.out.println("Suma kolumn");
        for (int i = 0; i < 3; i++) {
            int columnSum = 0;
            for (int j = 0; j < 3; j++) {
                columnSum = columnSum + matrix[j][i];
            }
            System.out.print(columnSum + " ");
        }
        System.out.println();


        System.out.println("Symetryczność:");
        if (isSymmetrical(matrix)) {
            System.out.println("Macierz jest symetryczna");
        } else {
            System.out.println("Macierz jest niesymetryczna");
        }


    }

    public static void printMatrix(int[][] array) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isSymmetrical(int[][] matrix) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
