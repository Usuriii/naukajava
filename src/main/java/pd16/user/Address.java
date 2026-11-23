package pd16.user;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Address {

    String street;
    String suite;
    String city;
    int zipcode;
    Geo geo;
}
