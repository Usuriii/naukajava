package pd7.model;

import lombok.ToString;

@ToString
public class User {
    private final String name;
    private final String email;
    @ToString.Exclude
    private final String passwordHash;

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = "hashed_" + password;
    }
}
