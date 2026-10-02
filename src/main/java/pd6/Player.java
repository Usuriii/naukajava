package pd6;

import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Player implements Participant {
    private String name;


    @Override
    public String getName() {
        return this.name;
    }
}
