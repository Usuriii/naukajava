package pd13.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Client {
    private String name;
    private int quantityToBuy;
}
