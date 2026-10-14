package pd7.service;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pd7.exceptions.ValidationException;
import pd7.enums.PasswordStrength;

import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class InputValidation {
    private static final Pattern USERNAME_PATTERN =
            Pattern.compile("^[A-Za-zĄĆĘŁŃÓŚŹŻąćęłńóśźż \\-]+$");

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[^@]+@[^@]+\\.[^@]+$");

    private static final Pattern UPPER_PATTERN =
            Pattern.compile("[A-Z]");

    private static final Pattern DIGIT_PATTERN =
            Pattern.compile("\\d");

    private static final Pattern SPECIAL_PATTERN =
            Pattern.compile("[!@#$%^&*()_+\\-=\\[\\]{};':\",.<>/?]");

    public static void nameValidate(String name) {

        if (name.startsWith(" ")) {
            throw new ValidationException("Name cannot start with space");
        }
        if (!USERNAME_PATTERN.matcher(name).matches()) {
            throw new ValidationException("Name contains invalid characters");
        }
        if (name.length() < 2 || name.length() > 100) {
            throw new ValidationException("Name must be between 2 and 100 characters long");
        }
    }

    public static void emailValidate(String email) {
        if (email.startsWith(" ")) {
            throw new ValidationException("Email cannot start with space");
        }
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new ValidationException("Email contains invalid characters");
        }
    }

    public static PasswordStrength validatePassword(String password) {

        boolean hasUpper = UPPER_PATTERN.matcher(password).find();
        boolean hasDigit = DIGIT_PATTERN.matcher(password).find();
        long specialCount = SPECIAL_PATTERN.matcher(password).results().count();

        if (password.length() < 8) {
            return PasswordStrength.WEAK;

        } else if (hasUpper && hasDigit && specialCount >= 2) {
            return PasswordStrength.STRONG;

        } else if (hasUpper && hasDigit && specialCount >= 1) {
            return PasswordStrength.OK;

        } else {
            return PasswordStrength.WEAK;
        }
    }
}
