package zl2;

import java.util.Scanner;

public class BMICalc {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaj wage w kg");
        double weightInKg = scanner.nextDouble();
        System.out.println("Podaj wzrost w cm");
        double heightInCm = scanner.nextDouble();
        if (heightInCm < 30 || heightInCm > 250) {
            throw new IllegalArgumentException("Podany wzrost jest mniejszy niz 30cm lub wiekszy niz 250cm");
        }
        double heightInM = heightInCm / 100;
        double bmi = weightInKg / (heightInM * heightInM);
        System.out.println(String.format("%.2f", bmi));

        if (bmi <= 18.50) {
            System.out.println("Niedowaga");
        } else if (bmi > 18.5 && bmi <= 25) {
            System.out.println("Norma");
        } else if (bmi > 25 && bmi < 30) {
            System.out.println("Nadwaga");
        } else if (bmi >= 30) {
            System.out.println("Otyłość");
        }
    }
}