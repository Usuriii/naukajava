package zl3;

public enum DayOfTheWeek {
    MONDAY("Poniedziałek",true),
    TUESDAY("Wtorek",true),
    WEDNESDAY("Środa",true),
    THRUSDAY("Czwartek",true),
    FRIDAY("Piątek",true),
    SATURDAY("Sobota",false),
    SUNDAY("Niedziela",false);

    private final String dayName;
    private final boolean isWorkingDay;

    DayOfTheWeek(String dayName, boolean isWorkingDay) {
        this.dayName = dayName;
        this.isWorkingDay = isWorkingDay;
    }

    public boolean isWorkingDay() {
        return isWorkingDay;
    }

    public String getDayName() {
        return dayName;
    }
}
