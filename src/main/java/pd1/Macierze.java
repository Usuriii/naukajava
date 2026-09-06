package pd1;

import java.util.Scanner;

public class Macierze {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        //PD-1 Macierze - obrót i transpozycja
        //
        //Utwórz macierz 3x3 wczytaną od użytkownika (9 liczb)
        //Wyświetl macierz w czytelnym formacie
        //Oblicz i wyświetl transpozycję macierzy
        //Obróć macierz o 90° w prawo
        //Znajdź i wyświetl sumę każdego wiersza i każdej kolumny
        //Sprawdź czy macierz jest symetryczna (a[i][j] == a[j][i])

        System.out.println("Podaj 9 liczb");
        int[][] matrix = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = scanner.nextInt();
            }
        }

        System.out.println("Macierz:");
        printMatrix(matrix);


        int [][] matrixTransposition = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrixTransposition[j][i] = matrix[i][j];
            }
        }
        System.out.println("Transpozycja macierzy:");
        printMatrix(matrixTransposition);

        System.out.println("Obrót oryginalnego macierza o 90° w prawo");
        int [][] rotatedMatrix = new int[3][3];
//        String macierz = """
//                1 2 3
//                4 5 6
//                7 8 9
//
//                7 4 1
//                8 5 2
//                9 6 3
//                czyli pozycja
//                0.0 -> 0.2      1.0 -> 0.1      2.0 -> 0.0
//                0.1 -> 1.2      1.1 -> 1.1      2.1 -> 1.0
//                0.2 -> 2.2      1.2 -> 2.1      2.2 -> 2.0
//                """;
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
            System.out.print(columnSum + " " );
        }
        System.out.println();


        System.out.println("Symetryczność:");
        if (czySymetryczna(matrix)){
            System.out.println("Macierz jest symetryczna");
        } else {
            System.out.println("Macierz jest niesymetryczna");
        }


    }
    public static void printMatrix (int[][] array){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
    public static boolean czySymetryczna(int[][] matrix){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (matrix[i][j] != matrix[j][i]) {
                    return false;
                }
            }
        } return true;
    }
}
