package zl2;

import java.util.Scanner;

public class BMICalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj wage w kg");
        double weight = scanner.nextDouble();
        System.out.println("Podaj wzrost w cm");
        double heightInCm = scanner.nextDouble();
        if (heightInCm < 30 || heightInCm > 250) {
            throw new IllegalArgumentException("Podany wzrost jest mniejszy niz 30cm lub wiekszy niz 250cm");
        }
        double heightInM = heightInCm * 0.01;
        double bmiCal = weight / (heightInM * heightInM);
        System.out.println(String.format("%.2f", bmiCal));

        if (bmiCal < 18.5) System.out.println("Niedowaga");
        else if (bmiCal > 18.5 && bmiCal < 24.9) {
            System.out.println("Norma");
        } else if (bmiCal > 25 && bmiCal < 29.9) {
            System.out.println("Nadwaga");
        } else if (bmiCal >= 30) {
            System.out.println("Otyłość");
        }
    }
}
