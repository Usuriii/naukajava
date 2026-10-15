package pd7.service;

import pd7.exceptions.DuplicateEmailException;
import pd7.exceptions.WeakPasswordException;
import pd7.enums.PasswordStrength;
import pd7.model.User;

import java.util.HashMap;
import java.util.Map;

public final class UserRegistrationService {
    private static final Map<String, User> emailsContainer = new HashMap<>();

    public static void registerUser(String name, String email, String password) {
        String normalizedEmail = email.trim().toLowerCase();
        String normalizedName = name.trim();

        InputValidation.nameValidate(name);
        InputValidation.emailValidate(email);
        emailDuplicateValid(normalizedEmail);

        if (InputValidation.validatePassword(password) == PasswordStrength.WEAK) {
            throw new WeakPasswordException(PasswordStrength.WEAK.getDescription());
        }

        emailsContainer.put(email, new User(normalizedName, normalizedEmail, password));
    }

    public static void emailDuplicateValid(String email) {
        String normalizedEmail = email.trim().toLowerCase();
        if (emailsContainer.containsKey(normalizedEmail)) {
            throw new DuplicateEmailException();
        }
    }

    public static void printUsers() {
        System.out.println(emailsContainer.values());
    }
}
