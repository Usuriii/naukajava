package zl3;

import java.util.Scanner;

public class DayInfo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj numer dnia tygodnia");
        int dayNumber = scanner.nextInt();
        DayOfTheWeek day = switch (dayNumber) {
            case 1 -> DayOfTheWeek.MONDAY;
            case 2 -> DayOfTheWeek.TUESDAY;
            case 3 -> DayOfTheWeek.WEDNESDAY;
            case 4 -> DayOfTheWeek.THRUSDAY;
            case 5 -> DayOfTheWeek.FRIDAY;
            case 6 -> DayOfTheWeek.SATURDAY;
            case 7 -> DayOfTheWeek.SUNDAY;
            default -> throw new IllegalArgumentException("Podano złą wartość");
        };

        if (day.isWorkingDay() == true) {
            System.out.println(day.getDayName() + " - dzień pracujący ");
            System.out.println("Pierwszy autobus odjeżdża o 5:30");
        } else {
            System.out.println(day.getDayName() + " - dzień wolny od pracy");
            if (dayNumber == 6) {
                System.out.println("Pierwszy autobus odjeżdża o 7:00");
            } else {
                System.out.println("Pierwszy autobus odjeżdża o 9:00");
            }
        }
    }
}
