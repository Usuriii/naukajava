package pd9;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Event {
    private final EventType type;
    private final String category;
    private final String description;
    private final int importance;
}
