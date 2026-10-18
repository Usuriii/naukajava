package pd9;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        var eventProcessor = EventProcessor.initialize();
        List<Predicate<Event>> predicates = new ArrayList<>();
        List<Event> events = List.of(
                new Event(EventType.BATTERY_FULL, "power", "Bateria osiągneła 100%", 1),
                new Event(EventType.BATTERY_LOW, "power", "Bateria spadła do 15%", 4),
                new Event(EventType.CHARGER_DISCONNECTED, "device", "Odłączono ładowarkę", 4),
                new Event(EventType.CHARGER_CONNECTED, "device", "Podłączenie ładowarki", 2),
                new Event(EventType.BATTERY_SAVING_MODE_OFF, "power", "Wyłączono tryb oszczędziania baterii", 1),
                new Event(EventType.BATTERY_SAVING_MODE_ON, "power", "Włączono tryb oszczędziania baterii", 2),
                new Event(EventType.SYSTEM_DOWN, "system", "System został wyłączony", 5)
        );

        Predicate<Event> category = event -> event.getCategory().equalsIgnoreCase("power");
        Predicate<Event> critical = event -> event.getType() == EventType.SYSTEM_DOWN;
        Predicate<Event> warning = event -> event.getImportance() >= 2;

        predicates.add(critical.or(warning));
//        predicates.add(category.and(warning));
        Function<Event, String> typeAndDescription = event -> "[" + event.getType() + "] " + event.getDescription();
        Consumer<String> consumer = System.out::println;

        eventProcessor.process(events, predicates, typeAndDescription, consumer);
    }
}
