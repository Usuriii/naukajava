package pd1;

public class Choinka {
    public static void main(String[] args) {
        // Wynik:
        //     *
        //    ***
        //   *****
        //  *******
        // *********

        System.out.println("// Wynik:");
        for (int i = 5; i > 0; i--) {
            for (int j = 0; j < 1; j++) {
                System.out.print("//");
            }
            for (int j = i; j > 0; j--) {
                System.out.print(" ");
            }
            for (int k = 2 * (6 - i) - 1; k > 0; k--) {
                System.out.print("*");
            }
            System.out.println();
        }
    }
}
