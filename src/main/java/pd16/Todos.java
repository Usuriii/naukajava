package pd16;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Todos {
    int userId;
    String id;
    String title;
    boolean completed;
}
