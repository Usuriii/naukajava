package pd16.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class User {

    Long id;
    String name;
    String username;
    String email;
    Address address;
    String phone;
    String website;
    Company company;
}
