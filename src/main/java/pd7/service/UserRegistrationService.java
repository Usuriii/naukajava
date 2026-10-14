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

        String emailToLowerCase = email.toLowerCase();

        InputValidation.nameValidate(name);

        InputValidation.emailValidate(email);

        emailDuplicateValid(emailToLowerCase);

        if (InputValidation.validatePassword(password) == PasswordStrength.WEAK) {
            throw new WeakPasswordException(PasswordStrength.WEAK.getDescription());
        }

        String hashedPassword = "hashed_" + password;

        emailsContainer.put(email, new User(name, emailToLowerCase, hashedPassword));
    }

    public static void emailDuplicateValid(String emailToLowerCase) {
        if (emailsContainer.containsKey(emailToLowerCase)) {
            throw new DuplicateEmailException("This email already exists, please change the email.");
        }
    }

    public static void printUsers() {
        System.out.println(emailsContainer.values());
    }
}
