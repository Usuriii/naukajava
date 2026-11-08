package pd9;

import lombok.NoArgsConstructor;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

@NoArgsConstructor(staticName = "initialize")
public class EventProcessor {
    public void process(List<Event> events, List<Predicate<Event>> filters,
                        Function<Event, String> formatter, Consumer<String> output) {
        for (Event event : events) {
            boolean check = true;
            for (Predicate<Event> filter : filters) {
                check = filter.test(event);
                if (!check) {
                    break;
                }
            }
            if (check) {
                String formattedEvent = formatter.apply(event);
                output.accept(formattedEvent);
            }
        }
    }
}
