package pd14;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;

public class Main {
    public static void main(String[] args) {
        int age = DateCalculator.age(LocalDate.of(1997, 8, 11));
        System.out.println(age);
        System.out.println();

        LocalDate date = DateCalculator.nextPayday(LocalDate.now());
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(date);
        System.out.println();

        Long l = DateCalculator.businessDaysBetween(LocalDate.now(), LocalDate.now().plusMonths(1));
        System.out.println(l);
        System.out.println();

        ZonedDateTime zonedDateTime = DateCalculator.convertTimezone(now, "Asia/Tokyo");
        System.out.println(zonedDateTime);
        System.out.println();

        LocalDateTime now1 = LocalDateTime.now();
        String string = DateCalculator.formatForLocale(now1, "d MMMM yyyy");
        System.out.println(string);
        System.out.println();

        boolean validDate = DateCalculator.isValidDate("12.11.2026", "dd.MM.yyyy");
        System.out.println(validDate);
        boolean validDate1 = DateCalculator.isValidDate("12.11.2026", "dd-MM-yyyy");
        System.out.println(validDate1);


    }
}
