package pd14;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DateCalculator {

    public static int age (LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public static LocalDate nextPayday (LocalDate from) {
        LocalDate lastDay = from.withDayOfMonth(from.lengthOfMonth());
        if (lastDay.getDayOfWeek() == DayOfWeek.SATURDAY) {
            lastDay = lastDay.minusDays(1);
        }

        if (lastDay.getDayOfWeek() == DayOfWeek.SUNDAY) {
            lastDay = lastDay.minusDays(2);
        }
        return lastDay;
    }

    public static Long businessDaysBetween (LocalDate from, LocalDate to) {
        return from.datesUntil(to)
                        .filter(day -> day.getDayOfWeek() != DayOfWeek.SATURDAY && day.getDayOfWeek() != DayOfWeek.SUNDAY).count();
    }

    public static ZonedDateTime convertTimezone (ZonedDateTime dt, String targetZone) {
        ZoneId zoneId = ZoneId.of(targetZone);
        return dt.withZoneSameInstant(zoneId);
    }

    public static String formatForLocale (LocalDateTime dt, String languageTag) {
        return dt.format(DateTimeFormatter.ofPattern(languageTag, Locale.forLanguageTag("pl")));
    }

    public static boolean isValidDate (String input, String pattern) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }


}
