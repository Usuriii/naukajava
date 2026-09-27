package pd5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class Product implements Entity<String> {
    private String name;
    private final String id;

    @Override
    public String getId() {
        return id;
    }
}
