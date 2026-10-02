package pd6;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Team implements Participant {
    private String name;

    @Override
    public String getName() {
        return this.name;
    }
}
