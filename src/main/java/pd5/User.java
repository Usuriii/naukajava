package pd5;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class User implements Entity<Long> {
    private String name;
    private final Long id;

    @Override
    public Long getId() {
        return id;
    }
}
