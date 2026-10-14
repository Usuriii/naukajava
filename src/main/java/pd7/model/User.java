package pd7.model;

import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
public class User {
    private final String name;
    private final String email;
    @ToString.Exclude
    private final String password;
}
